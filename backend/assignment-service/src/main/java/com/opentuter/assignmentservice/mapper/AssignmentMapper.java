package com.opentuter.assignmentservice.mapper;

import com.opentuter.assignmentservice.dto.AssignmentRequestDTO;
import com.opentuter.assignmentservice.dto.AssignmentResponseDTO;
import com.opentuter.assignmentservice.dto.SubmissionRequestDTO;
import com.opentuter.assignmentservice.dto.SubmissionResponseDTO;
import com.opentuter.assignmentservice.model.Assignment;
import com.opentuter.assignmentservice.model.AssignmentSubmission;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between JPA entities and DTOs.
 *
 * <p>
 * Used by the service layer to transform data before passing it to the
 * controller
 * (entity → DTO) or before persisting it to the database (DTO → entity).
 * </p>
 */
@Component
public class AssignmentMapper {

    /**
     * Converts an {@link AssignmentRequestDTO} to an {@link Assignment} entity.
     *
     * @param dto the incoming request DTO from the client
     * @return a new {@link Assignment} entity populated with the DTO's fields
     */
    public Assignment toEntity(AssignmentRequestDTO dto) {
        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setClassroomId(dto.getClassroomId());
        assignment.setMaxPoints(dto.getMaxPoints());
        assignment.setDueDate(dto.getDueDate());
        return assignment;
    }

    /**
     * Converts an {@link Assignment} entity to an {@link AssignmentResponseDTO}.
     *
     * @param assignment the entity retrieved from the database
     * @return a new {@link AssignmentResponseDTO} populated with the entity's
     *         fields
     */
    public AssignmentResponseDTO toDTO(Assignment assignment) {
        AssignmentResponseDTO dto = new AssignmentResponseDTO();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setClassroomId(assignment.getClassroomId());
        dto.setMaxPoints(assignment.getMaxPoints());
        dto.setDueDate(assignment.getDueDate());
        return dto;
    }

    /**
     * Converts a {@link SubmissionRequestDTO} to an {@link AssignmentSubmission}
     * entity.
     *
     * @param dto the incoming submission request DTO from the client
     * @return a new {@link AssignmentSubmission} entity populated with the DTO's
     *         fields
     */
    public AssignmentSubmission toEntity(SubmissionRequestDTO dto) {
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setAssignmentId(dto.getAssignmentId());
        submission.setStudentId(dto.getStudentId());
        submission.setSubmissionUrl(dto.getSubmissionUrl());
        return submission;
    }

    /**
     * Converts an {@link AssignmentSubmission} entity to a
     * {@link SubmissionResponseDTO}.
     *
     * @param submission the entity retrieved from the database
     * @return a new {@link SubmissionResponseDTO} populated with the entity's
     *         fields
     */
    public SubmissionResponseDTO toDTO(AssignmentSubmission submission) {
        SubmissionResponseDTO dto = new SubmissionResponseDTO();
        dto.setId(submission.getId());
        dto.setAssignmentId(submission.getAssignmentId());
        dto.setStudentId(submission.getStudentId());
        dto.setSubmissionUrl(submission.getSubmissionUrl());
        dto.setGrade(submission.getGrade());
        dto.setFeedback(submission.getFeedback());
        dto.setSubmittedAt(submission.getSubmittedAt());
        return dto;
    }
}
