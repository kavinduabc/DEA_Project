package com.opentuter.assignmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler that intercepts exceptions thrown anywhere in the
 * application
 * and translates them into structured JSON HTTP responses.
 *
 * <p>
 * Uses Spring's {@link ControllerAdvice} mechanism so that it applies to all
 * controllers.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} and returns a {@code 404 Not Found}
     * response.
     *
     * @param ex      the exception containing the missing-resource message
     * @param request the current web request (unused but kept for Spring
     *                compatibility)
     * @return a {@link ResponseEntity} with a JSON body containing the timestamp
     *         and error message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link SubmissionExpiredException} and returns a
     * {@code 422 Unprocessable Entity} response.
     * This is thrown when a student attempts to submit an assignment after its due
     * date.
     *
     * @param ex      the exception containing the rejection reason
     * @param request the current web request
     * @return a {@link ResponseEntity} with a JSON body containing the timestamp
     *         and error message
     */
    @ExceptionHandler(SubmissionExpiredException.class)
    public ResponseEntity<?> submissionExpiredException(SubmissionExpiredException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.valueOf(422));
    }

    /**
     * Catch-all handler for any unhandled {@link Exception} and returns a
     * {@code 500 Internal Server Error} response.
     *
     * @param ex      the unhandled exception
     * @param request the current web request
     * @return a {@link ResponseEntity} with a JSON body containing the timestamp
     *         and error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
