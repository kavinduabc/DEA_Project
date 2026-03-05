package com.opentuter.assignmentservice.service;

import com.opentuter.assignmentservice.dto.AssignmentRequestDTO;
import com.opentuter.assignmentservice.dto.AssignmentResponseDTO;
import com.opentuter.assignmentservice.dto.SubmissionRequestDTO;
import com.opentuter.assignmentservice.dto.SubmissionResponseDTO;
import com.opentuter.assignmentservice.exception.ResourceNotFoundException;
import com.opentuter.assignmentservice.exception.SubmissionExpiredException;
import com.opentuter.assignmentservice.mapper.AssignmentMapper;
import com.opentuter.assignmentservice.model.Assignment;
import com.opentuter.assignmentservice.model.AssignmentSubmission;
import com.opentuter.assignmentservice.repository.AssignmentRepository;
import com.opentuter.assignmentservice.repository.AssignmentSubmissionRepository;
import com.opentuter.assignmentservice.util.AppConstants;
import com.opentuter.assignmentservice.util.DateTimeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of {@link IAssignmentService} that handles all business logic
 * for managing assignments and student submissions.
 *
 * <p>
 * This class interacts with the JPA repositories to persist data and uses
 * {@link AssignmentMapper} to convert between entities and DTOs.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements IAssignmentService {

    /**
     * Repository for {@link com.opentuter.assignmentservice.model.Assignment}
     * entities.
     */
    private final AssignmentRepository assignmentRepository;

    /**
     * Repository for
     * {@link com.opentuter.assignmentservice.model.AssignmentSubmission} entities.
     */
    private final AssignmentSubmissionRepository submissionRepository;

    /** Mapper for converting between entity and DTO objects. */
    private final AssignmentMapper assignmentMapper;

    // ── Assignment Operations ─────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     */
    @Override
    public AssignmentResponseDTO createAssignment(AssignmentRequestDTO request) {
        Assignment assignment = assignmentMapper.toEntity(request);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(savedAssignment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AssignmentResponseDTO getAssignmentById(UUID id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.ASSIGNMENT_NOT_FOUND + id));
        return assignmentMapper.toDTO(assignment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AssignmentResponseDTO> getAssignmentsByClassroom(UUID classroomId) {
        return assignmentRepository.findByClassroomId(classroomId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AssignmentResponseDTO> getAllAssignments() {
        return assignmentRepository.findAll().stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AssignmentResponseDTO updateAssignment(UUID id, AssignmentRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.ASSIGNMENT_NOT_FOUND + id));

        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());
        assignment.setMaxPoints(request.getMaxPoints());

        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(updatedAssignment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAssignment(UUID id) {
        if (!assignmentRepository.existsById(id)) {
            throw new ResourceNotFoundException(AppConstants.ASSIGNMENT_NOT_FOUND + id);
        }
        assignmentRepository.deleteById(id);
    }

    // ── Submission Operations ─────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionResponseDTO submitAssignment(SubmissionRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        AppConstants.ASSIGNMENT_NOT_FOUND + request.getAssignmentId()));

        if (DateTimeHelper.isPastDue(assignment.getDueDate())) {
            throw new SubmissionExpiredException(AppConstants.SUBMISSION_PAST_DUE);
        }

        AssignmentSubmission submission = assignmentMapper.toEntity(request);
        AssignmentSubmission savedSubmission = submissionRepository.save(submission);
        return assignmentMapper.toDTO(savedSubmission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionResponseDTO getSubmissionById(UUID id) {
        AssignmentSubmission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.SUBMISSION_NOT_FOUND + id));
        return assignmentMapper.toDTO(submission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionResponseDTO gradeSubmission(UUID id, Double grade, String feedback) {
        AssignmentSubmission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.SUBMISSION_NOT_FOUND + id));

        submission.setGrade(grade);
        submission.setFeedback(feedback);

        AssignmentSubmission updatedSubmission = submissionRepository.save(submission);
        return assignmentMapper.toDTO(updatedSubmission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubmissionResponseDTO> getSubmissionsByAssignment(UUID assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubmissionResponseDTO> getSubmissionsByStudent(UUID studentId) {
        return submissionRepository.findByStudentId(studentId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
