package com.opentuter.assignmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity representing an assignment created by a teacher within a
 * classroom.
 *
 * <p>
 * Mapped to the {@code assignments} table in the database.
 * </p>
 */
@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    /** The unique identifier for this assignment, auto-generated as a UUID. */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /** The title of the assignment (e.g., "Lab Report 2"). */
    private String title;

    /** A detailed description of the assignment requirements. */
    private String description;

    /** The maximum score a student can earn for this assignment. */
    @Column(name = "max_points")
    private Double maxPoints;

    /** The deadline by which students must submit their work. */
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    /** The UUID of the classroom this assignment was created in. */
    @Column(name = "classroom_id")
    private UUID classroomId;
}
