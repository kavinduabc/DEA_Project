package com.opentuter.assignment_service.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class SubmissionRequestDTO {
    private UUID assignmentId;
    private UUID studentId;
    private String submissionUrl;
}
