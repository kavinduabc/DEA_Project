package com.opentutor.announcementservice.exception;

import com.opentutor.announcementservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles 404 - thrown when a requested announcement or related resource does not exist.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles 409 - thrown when trying to create a resource that already exists.
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateResourceException(
            DuplicateResourceException exception,
            WebRequest webRequest
    ) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * Handles 400 - base handler for any custom AnnouncementServiceException
     * not caught by a more specific handler above.
     * Ensures future custom exceptions extending the base class are handled gracefully.
     */
    @ExceptionHandler(AnnouncementServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleAnnouncementServiceException(
            AnnouncementServiceException exception,
            WebRequest webRequest
    ) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles 400 - triggered when @Valid annotated request body fields fail validation.
     * Aggregates all field-level errors into a single readable message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(
            MethodArgumentNotValidException exception,
            WebRequest webRequest
    ) {
        String errorMessage = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                "Validation failed: " + errorMessage,
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles 400 - triggered when the request body contains malformed or unreadable JSON.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            WebRequest webRequest
    ) {
        String message = "Invalid request body structure or malformed JSON";

        if (exception.getMessage() != null && exception.getMessage().contains("JSON parse error")) {
            message = "Malformed JSON request";
        } else if (exception.getMessage() != null && exception.getMessage().contains("Required request body is missing")) {
            message = "Request body is required";
        }

        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                message,
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles 500 - catch-all for any unhandled runtime exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                LocalDateTime.now(),
                "Internal Server Error",
                exception.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
