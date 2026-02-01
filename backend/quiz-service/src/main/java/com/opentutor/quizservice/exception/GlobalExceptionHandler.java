package com.opentutor.quizservice.exception;


import com.opentutor.quizservice.dto.ErrorRespoceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorRespoceDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.NOT_FOUND);
    }

    // Handle BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorRespoceDto> handleBadRequestException(BadRequestException exception, WebRequest webRequest) {
        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.BAD_REQUEST);
    }

    // Handle Validation Exceptions (MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespoceDto> handleValidationException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        String errorMessage = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                "Validation failed: " + errorMessage,
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.BAD_REQUEST);
    }

    // Handle Malformed JSON or Invalid Request Body Structure
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorRespoceDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest webRequest) {
        String errorMessage = "Invalid request body structure or malformed JSON";
        if (exception.getMessage() != null && exception.getMessage().contains("JSON parse error")) {
            errorMessage = "Malformed JSON request";
        } else if (exception.getMessage() != null && exception.getMessage().contains("Required request body is missing")) {
            errorMessage = "Request body is required";
        }

        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                errorMessage,
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.BAD_REQUEST);
    }

    // Handle DuplicateResourceException
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorRespoceDto> handleDuplicateResourceException(DuplicateResourceException exception, WebRequest webRequest) {
        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.CONFLICT);
    }

    // Handle DeletionNotAllowedException
    @ExceptionHandler(DeletionNotAllowedException.class)
    public ResponseEntity<ErrorRespoceDto> handleDeletionNotAllowedException(DeletionNotAllowedException exception, WebRequest webRequest) {
        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.FORBIDDEN);
    }

    // Handle Global Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespoceDto> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorRespoceDto ErrorRespoceDto = new ErrorRespoceDto(
                LocalDateTime.now(),
                "Internal Server Error",
                exception.getMessage()
        );
        return new ResponseEntity<>(ErrorRespoceDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}