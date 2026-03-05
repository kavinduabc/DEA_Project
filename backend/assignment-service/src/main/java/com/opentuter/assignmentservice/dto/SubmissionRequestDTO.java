package com.opentuter.assignmentservice.dto;

import lombok.Data;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for submitting a student's work for an assignment.
 *
 * <p>
 * Sent by the client in the request body for
 * {@code POST /api/assignments/submissions}.
 * </p>
 */
@Data
public class SubmissionRequestDTO {

    /** The UUID of the assignment being submitted. */
    private UUID assignmentId;

    /** The UUID of the student making the submission. */
    private UUID studentId;

    /**
     * The publicly accessible URL pointing to the submitted file (e.g., in cloud
     * storage).
     */
    private String submissionUrl;
}
