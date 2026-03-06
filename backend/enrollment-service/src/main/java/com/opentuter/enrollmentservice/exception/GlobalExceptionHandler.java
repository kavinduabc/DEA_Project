package com.opentuter.enrollmentservice.exception;
import com.opentuter.enrollmentservice.util.EnrollmentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle custom DuplicateResourceException
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(
            DuplicateResourceException ex,
            WebRequest request) {

        logger.warn(EnrollmentUtil.LOG_DUPLICATE_RESOURCE, ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", EnrollmentUtil.ERROR_CONFLICT);
        response.put("message", ex.getMessage());
        response.put("path", request.getDescription(false).replace(EnrollmentUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handle custom ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {

        logger.warn(EnrollmentUtil.LOG_RESOURCE_NOT_FOUND, ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", EnrollmentUtil.ERROR_NOT_FOUND);
        response.put("message", ex.getMessage());
        response.put("path", request.getDescription(false).replace(EnrollmentUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        logger.warn(EnrollmentUtil.LOG_VALIDATION_FAILED, ex.getBindingResult().getErrorCount());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", EnrollmentUtil.ERROR_VALIDATION_FAILED);
        response.put("message", EnrollmentUtil.ERROR_INVALID_INPUT);
        response.put("errors", errors);
        response.put("path", request.getDescription(false).replace(EnrollmentUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle database constraint violations (fallback for DataIntegrityViolationException)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            WebRequest request) {

        logger.error(EnrollmentUtil.LOG_DB_CONSTRAINT_VIOLATION, ex);

        String message = EnrollmentUtil.ERROR_DATA_INTEGRITY;
        String detailedMessage = ex.getMessage();

        // Check for specific constraint violations
        if (detailedMessage != null) {
            if (detailedMessage.contains(EnrollmentUtil.CONSTRAINT_DUPLICATE_KEY) && detailedMessage.contains(EnrollmentUtil.CONSTRAINT_EMAIL)) {
                message = EnrollmentUtil.ERROR_DUPLICATE_KEY_EMAIL;
            } else if (detailedMessage.contains(EnrollmentUtil.CONSTRAINT_DUPLICATE_KEY) && detailedMessage.contains(EnrollmentUtil.CONSTRAINT_PHONE)) {
                message = EnrollmentUtil.ERROR_DUPLICATE_KEY_PHONE;
            } else if (detailedMessage.contains(EnrollmentUtil.CONSTRAINT_DUPLICATE_KEY)) {
                message = EnrollmentUtil.ERROR_DUPLICATE_KEY;
            } else if (detailedMessage.contains(EnrollmentUtil.CONSTRAINT_FOREIGN_KEY)) {
                message = EnrollmentUtil.ERROR_FOREIGN_KEY;
            } else if (detailedMessage.contains(EnrollmentUtil.CONSTRAINT_NOT_NULL)) {
                message = EnrollmentUtil.ERROR_NOT_NULL;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", EnrollmentUtil.ERROR_CONFLICT);
        response.put("message", message);
        response.put("path", request.getDescription(false).replace(EnrollmentUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception ex,
            WebRequest request) {

        logger.error(EnrollmentUtil.LOG_UNEXPECTED_ERROR, ex);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", EnrollmentUtil.ERROR_INTERNAL_SERVER);
        response.put("message", EnrollmentUtil.ERROR_UNEXPECTED);
        response.put("path", request.getDescription(false).replace(EnrollmentUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
