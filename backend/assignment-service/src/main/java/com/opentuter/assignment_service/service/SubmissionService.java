package com.opentuter.assignment_service.service;

import com.opentuter.assignment_service.dto.SubmissionRequestDTO;
import com.opentuter.assignment_service.dto.SubmissionResponseDTO;
import com.opentuter.assignment_service.exception.ResourceNotFoundException;
import com.opentuter.assignment_service.mapper.AssignmentMapper;
import com.opentuter.assignment_service.model.AssignmentSubmission;
import com.opentuter.assignment_service.repository.AssignmentSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final AssignmentSubmissionRepository submissionRepository;
    private final AssignmentMapper assignmentMapper;

    public SubmissionResponseDTO submitAssignment(SubmissionRequestDTO request) {
        AssignmentSubmission submission = assignmentMapper.toEntity(request);
        AssignmentSubmission savedSubmission = submissionRepository.save(submission);
        return assignmentMapper.toDTO(savedSubmission);
    }

    public SubmissionResponseDTO gradeSubmission(Long id, Double grade, String feedback) {
        AssignmentSubmission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + id));

        submission.setGrade(grade);
        submission.setFeedback(feedback);

        AssignmentSubmission updatedSubmission = submissionRepository.save(submission);
        return assignmentMapper.toDTO(updatedSubmission);
    }

    public List<SubmissionResponseDTO> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SubmissionResponseDTO> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
