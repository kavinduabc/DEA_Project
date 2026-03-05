package com.opentuter.assignmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubmissionResponseDTO {
    private UUID id;
    private UUID assignmentId;
    private UUID studentId;
    private String submissionUrl;
    private Double grade;
    private String feedback;
    private LocalDateTime submittedAt;
}
