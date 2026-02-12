package com.opentuter.assignment_service.service;

import com.opentuter.assignment_service.dto.AssignmentRequestDTO;
import com.opentuter.assignment_service.dto.AssignmentResponseDTO;
import com.opentuter.assignment_service.dto.SubmissionRequestDTO;
import com.opentuter.assignment_service.dto.SubmissionResponseDTO;
import com.opentuter.assignment_service.exception.ResourceNotFoundException;
import com.opentuter.assignment_service.mapper.AssignmentMapper;
import com.opentuter.assignment_service.model.Assignment;
import com.opentuter.assignment_service.model.AssignmentSubmission;
import com.opentuter.assignment_service.repository.AssignmentRepository;
import com.opentuter.assignment_service.repository.AssignmentSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmissionRepository submissionRepository;
    private final AssignmentMapper assignmentMapper;

    // --- Assignment Operations ---

    public AssignmentResponseDTO createAssignment(AssignmentRequestDTO request) {
        Assignment assignment = assignmentMapper.toEntity(request);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(savedAssignment);
    }

    public AssignmentResponseDTO getAssignmentById(UUID id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));
        return assignmentMapper.toDTO(assignment);
    }

    public List<AssignmentResponseDTO> getAssignmentsByClassroom(UUID classroomId) {
        return assignmentRepository.findByClassroomId(classroomId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AssignmentResponseDTO> getAllAssignments() {
        return assignmentRepository.findAll().stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AssignmentResponseDTO updateAssignment(UUID id, AssignmentRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));

        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());
        assignment.setMaxPoints(request.getMaxPoints());

        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(updatedAssignment);
    }

    public void deleteAssignment(UUID id) {
        if (!assignmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Assignment not found with id: " + id);
        }
        assignmentRepository.deleteById(id);
    }

    // --- Submission Operations ---

    public SubmissionResponseDTO submitAssignment(SubmissionRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Assignment not found with id: " + request.getAssignmentId()));

        if (assignment.getDueDate() != null && assignment.getDueDate().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("Submission rejected: Assignment due date has passed.");
        }

        AssignmentSubmission submission = assignmentMapper.toEntity(request);
        AssignmentSubmission savedSubmission = submissionRepository.save(submission);
        return assignmentMapper.toDTO(savedSubmission);
    }

    public SubmissionResponseDTO gradeSubmission(UUID id, Double grade, String feedback) {
        AssignmentSubmission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + id));

        submission.setGrade(grade);
        submission.setFeedback(feedback);

        AssignmentSubmission updatedSubmission = submissionRepository.save(submission);
        return assignmentMapper.toDTO(updatedSubmission);
    }

    public List<SubmissionResponseDTO> getSubmissionsByAssignment(UUID assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SubmissionResponseDTO> getSubmissionsByStudent(UUID studentId) {
        return submissionRepository.findByStudentId(studentId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
