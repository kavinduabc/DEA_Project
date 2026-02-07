package com.opentuter.assignment_service.dto;

import lombok.Data;

@Data
public class SubmissionRequestDTO {
    private Long assignmentId;
    private Long studentId;
    private String submissionUrl;
}
