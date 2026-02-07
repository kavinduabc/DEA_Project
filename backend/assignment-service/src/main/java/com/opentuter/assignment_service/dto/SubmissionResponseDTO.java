package com.opentuter.assignment_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubmissionResponseDTO {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private String submissionUrl;
    private Double grade;
    private String feedback;
    private LocalDateTime submittedAt;
}
