package com.opentutor.qaservice.exception;

public class DeletionNotAllowedException extends RuntimeException {
    
    public DeletionNotAllowedException(String message) {
        super(message);
    }
    
    public DeletionNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
