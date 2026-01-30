package com.opentutor.quizservice.exception;

public class QuizDeletionNotAllowedException extends RuntimeException {
    public QuizDeletionNotAllowedException(String message) {
        super(message);
    }
}
