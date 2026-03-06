package com.opentuter.assignmentservice.exception;


public class SubmissionExpiredException extends RuntimeException {


    public SubmissionExpiredException() {
        super();
    }
    public SubmissionExpiredException(String message) {
        super(message);
    }
    public SubmissionExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
