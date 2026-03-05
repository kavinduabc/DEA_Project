package com.opentuter.assignmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) returned to the client after a submission has been
 * created or graded.
 *
 * <p>
 * Returned by endpoints such as {@code POST /api/assignments/submissions}
 * and {@code PUT /api/assignments/submissions/{id}/grade}.
 * </p>
 */
@Data
public class SubmissionResponseDTO {

    /** The unique identifier of the submission. */
    private UUID id;

    /** The UUID of the assignment this submission belongs to. */
    private UUID assignmentId;

    /** The UUID of the student who made this submission. */
    private UUID studentId;

    /** The publicly accessible URL pointing to the submitted file. */
    private String submissionUrl;

    /**
     * The grade awarded to this submission by the teacher (may be {@code null} if
     * not yet graded).
     */
    private Double grade;

    /**
     * The teacher's textual feedback for the student (may be {@code null} if not
     * yet graded).
     */
    private String feedback;

    /** The date and time at which the submission was recorded. */
    private LocalDateTime submittedAt;
}
