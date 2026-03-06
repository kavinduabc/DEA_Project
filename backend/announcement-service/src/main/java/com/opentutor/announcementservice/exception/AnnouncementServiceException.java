package com.opentutor.announcementservice.exception;

public class AnnouncementServiceException extends RuntimeException {

    public AnnouncementServiceException(String message) {
        super(message);
    }

    public AnnouncementServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
