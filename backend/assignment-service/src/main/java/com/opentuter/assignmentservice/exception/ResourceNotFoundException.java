package com.opentuter.assignmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource (e.g. an {@code Assignment} or
 * {@code AssignmentSubmission}) cannot be found in the database.
 *
 * <p>
 * Automatically maps to HTTP {@code 404 Not Found} via {@link ResponseStatus}.
 * </p>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code ResourceNotFoundException} with no detail message.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Constructs a new {@code ResourceNotFoundException} with the specified detail
     * message.
     *
     * @param message a human-readable description of the missing resource
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ResourceNotFoundException} with the specified detail
     * message and cause.
     *
     * @param message a human-readable description of the missing resource
     * @param cause   the underlying cause of this exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
