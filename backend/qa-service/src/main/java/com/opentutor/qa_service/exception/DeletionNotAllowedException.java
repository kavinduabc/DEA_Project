package com.opentutor.qa_service.exception;

public class DeletionNotAllowedException extends RuntimeException {
    
    public DeletionNotAllowedException(String message) {
        super(message);
    }
    
    public DeletionNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
