package com.opentuter.assignment_service.mapper;

import com.opentuter.assignment_service.dto.AssignmentRequestDTO;
import com.opentuter.assignment_service.dto.AssignmentResponseDTO;
import com.opentuter.assignment_service.dto.SubmissionRequestDTO;
import com.opentuter.assignment_service.dto.SubmissionResponseDTO;
import com.opentuter.assignment_service.model.Assignment;
import com.opentuter.assignment_service.model.AssignmentSubmission;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public Assignment toEntity(AssignmentRequestDTO dto) {
        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setClassroomId(dto.getClassroomId());
        assignment.setMaxPoints(dto.getMaxPoints());
        assignment.setDueDate(dto.getDueDate());
        return assignment;
    }

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

    public AssignmentSubmission toEntity(SubmissionRequestDTO dto) {
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setAssignmentId(dto.getAssignmentId());
        submission.setStudentId(dto.getStudentId());
        submission.setSubmissionUrl(dto.getSubmissionUrl());
        return submission;
    }

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
