package com.opentutor.quizservice.exception;

public class DeletionNotAllowedException extends RuntimeException {
    public DeletionNotAllowedException(String message) {
        super(message);
    }
}
