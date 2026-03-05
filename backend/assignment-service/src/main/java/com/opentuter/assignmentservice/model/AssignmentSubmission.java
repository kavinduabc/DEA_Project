package com.opentuter.assignmentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity representing a student's submission for a specific assignment.
 *
 * <p>
 * Mapped to the {@code submissions} table in the database.
 * </p>
 */
@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentSubmission {

    /** The unique identifier for this submission, auto-generated as a UUID. */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /** The UUID of the assignment this submission is for. */
    @Column(name = "assignment_id")
    private UUID assignmentId;

    /** The UUID of the student who made this submission. */
    @Column(name = "student_id")
    private UUID studentId;

    /**
     * The publicly accessible URL to the submitted file (e.g., hosted in cloud
     * storage).
     */
    @Column(name = "file_url")
    private String submissionUrl;

    /**
     * The date and time the submission was recorded, automatically set on creation.
     */
    @CreationTimestamp
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    /**
     * The numeric grade awarded by the teacher, or {@code null} if not yet graded.
     */
    private Double grade;

    /**
     * The teacher's textual feedback for the student, or {@code null} if not yet
     * graded.
     */
    private String feedback;
}
