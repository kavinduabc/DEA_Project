package com.opentutor.classroomservice.exception;

import com.opentutor.classroomservice.util.ClassroomUtil;
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

        logger.warn("Duplicate resource: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", ClassroomUtil.ERROR_CONFLICT);
        response.put("message", ex.getMessage());
        response.put("path", request.getDescription(false).replace(ClassroomUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handle custom ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {

        logger.warn("Resource not found: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", ClassroomUtil.ERROR_NOT_FOUND);
        response.put("message", ex.getMessage());
        response.put("path", request.getDescription(false).replace(ClassroomUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle custom UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorized(
            UnauthorizedException ex,
            WebRequest request) {

        logger.warn("Unauthorized access: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("error", ClassroomUtil.ERROR_UNAUTHORIZED);
        response.put("message", ex.getMessage());
        response.put("path", request.getDescription(false).replace(ClassroomUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        logger.warn("Validation failed: {} error(s)", ex.getBindingResult().getErrorCount());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", ClassroomUtil.ERROR_VALIDATION_FAILED);
        response.put("message", ClassroomUtil.MSG_INVALID_INPUT);
        response.put("errors", errors);
        response.put("path", request.getDescription(false).replace(ClassroomUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle database constraint violations (fallback for DataIntegrityViolationException)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            WebRequest request) {

        logger.error("Database constraint violation", ex);

        String message = ClassroomUtil.MSG_DATA_INTEGRITY_DEFAULT;
        String detailedMessage = ex.getMessage();

        // Check for specific constraint violations
        if (detailedMessage != null) {
            if (detailedMessage.contains(ClassroomUtil.DUPLICATE_KEY) && detailedMessage.contains(ClassroomUtil.CONSTRAINT_EMAIL)) {
                message = ClassroomUtil.MSG_DUPLICATE_EMAIL;
            } else if (detailedMessage.contains(ClassroomUtil.DUPLICATE_KEY) && detailedMessage.contains(ClassroomUtil.CONSTRAINT_PHONE)) {
                message = ClassroomUtil.MSG_DUPLICATE_PHONE;
            } else if (detailedMessage.contains(ClassroomUtil.DUPLICATE_KEY)) {
                message = ClassroomUtil.MSG_DUPLICATE_GENERIC;
            } else if (detailedMessage.contains(ClassroomUtil.FOREIGN_KEY_CONSTRAINT)) {
                message = ClassroomUtil.MSG_FOREIGN_KEY;
            } else if (detailedMessage.contains(ClassroomUtil.NOT_NULL_CONSTRAINT)) {
                message = ClassroomUtil.MSG_NOT_NULL;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", ClassroomUtil.ERROR_CONFLICT);
        response.put("message", message);
        response.put("path", request.getDescription(false).replace(ClassroomUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception ex,
            WebRequest request) {

        logger.error("Unexpected error occurred", ex);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", ClassroomUtil.ERROR_INTERNAL_SERVER);
        response.put("message", ClassroomUtil.MSG_UNEXPECTED_ERROR);
        response.put("path", request.getDescription(false).replace(ClassroomUtil.URI_PREFIX, ""));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
