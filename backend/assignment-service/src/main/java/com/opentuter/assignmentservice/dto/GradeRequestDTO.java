package com.opentuter.assignmentservice.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for grading a submission.
 *
 * <p>
 * Accepted by {@code PUT /api/assignments/submissions/{id}/grade}
 * and {@code PUT /api/submissions/{id}/grade}.
 * </p>
 */
@Data
public class GradeRequestDTO {

    /** The numeric grade to assign to the submission. */
    private Double grade;

    /** The textual feedback for the student. */
    private String feedback;
}

