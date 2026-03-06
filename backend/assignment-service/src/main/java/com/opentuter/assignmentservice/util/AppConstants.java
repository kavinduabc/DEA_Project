package com.opentuter.assignmentservice.util;


public final class AppConstants {

    // ── Error messages ────────────────────────────────────────────────────────

    /** Error message prefix when an {@code Assignment} entity cannot be located. */
    public static final String ASSIGNMENT_NOT_FOUND = "Assignment not found with id: ";

    /**
     * Error message prefix when an {@code AssignmentSubmission} entity cannot be
     * located.
     */
    public static final String SUBMISSION_NOT_FOUND = "Submission not found with id: ";

    /** Error message returned when a student tries to submit after the due date. */
    public static final String SUBMISSION_PAST_DUE = "Submission rejected: Assignment due date has passed.";

    // Private constructor prevents instantiation of this utility class.
    private AppConstants() {
        throw new UnsupportedOperationException("AppConstants is a utility class and cannot be instantiated.");
    }
}
