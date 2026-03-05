package com.opentutor.announcementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create a resource that already exists.
 * Results in a 409 CONFLICT HTTP response.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends AnnouncementServiceException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    /**
     * Constructor for duplicate resource with specific field context.
     *
     * @param resourceName the name of the resource (e.g. "Announcement")
     * @param fieldName    the field causing the conflict (e.g. "shareToken")
     * @param fieldValue   the duplicate value
     */
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s '%s' already exists", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     * Simplified constructor with a custom message.
     *
     * @param message the error message
     */
    public DuplicateResourceException(String message) {
        super(message);
        this.resourceName = null;
        this.fieldName = null;
        this.fieldValue = null;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
