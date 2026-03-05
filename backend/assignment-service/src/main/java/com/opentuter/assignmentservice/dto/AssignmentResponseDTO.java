package com.opentuter.assignmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) returned to the client after creating, updating,
 * or fetching an assignment.
 *
 * <p>
 * Returned by endpoints such as {@code GET /api/assignments/{id}} and
 * {@code POST /api/assignments}.
 * </p>
 */
@Data
public class AssignmentResponseDTO {

    /** The unique identifier of the assignment. */
    private UUID id;

    /** The title of the assignment. */
    private String title;

    /** A detailed description of the assignment. */
    private String description;

    /** The UUID of the classroom this assignment belongs to. */
    private UUID classroomId;

    /** The maximum score a student can receive. */
    private Double maxPoints;

    /** The submission deadline. */
    private LocalDateTime dueDate;
}
