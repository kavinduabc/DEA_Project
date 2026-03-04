package com.opentutor.announcementservice.exception;

/**
 * Base exception class for all custom exceptions in the Announcement Service.
 * All custom exceptions should extend this class to provide a consistent
 * exception hierarchy across the service.
 */
public class AnnouncementServiceException extends RuntimeException {

    public AnnouncementServiceException(String message) {
        super(message);
    }

    public AnnouncementServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
