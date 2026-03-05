package com.opentuter.assignmentservice.exception;

/**
 * Exception thrown when a student attempts to submit an assignment
 * after its due date has passed.
 *
 * <p>
 * HTTP status {@code 422 Unprocessable Entity} is applied by
 * {@link GlobalExceptionHandler}.
 * </p>
 */
public class SubmissionExpiredException extends RuntimeException {

    /**
     * Constructs a new {@code SubmissionExpiredException} with no detail message.
     */
    public SubmissionExpiredException() {
        super();
    }

    /**
     * Constructs a new {@code SubmissionExpiredException} with the specified detail
     * message.
     *
     * @param message the detail message explaining why the submission was rejected
     */
    public SubmissionExpiredException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SubmissionExpiredException} with the specified detail
     * message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of this exception
     */
    public SubmissionExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
