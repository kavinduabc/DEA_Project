package com.opentuter.assignmentservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for creating or updating an assignment.
 *
 * <p>
 * Sent by the client in the request body for {@code POST /api/assignments}
 * and {@code PUT /api/assignments/{id}}.
 * </p>
 */
@Data
public class AssignmentRequestDTO {

    /** The title of the assignment (e.g., "Homework 1"). */
    private String title;

    /** A detailed description of what the assignment requires. */
    private String description;

    /** The UUID of the classroom this assignment belongs to. */
    private UUID classroomId;

    /** The maximum score a student can receive for this assignment. */
    private Double maxPoints;

    /** The deadline by which students must submit their work. */
    private LocalDateTime dueDate;
}
