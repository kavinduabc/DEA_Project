package com.opentutor.announcement_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AnnouncementRequestDTO {

    @NotBlank(message = "Title is required")
    @Length(min = 3, max = 100, message = "Title should be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Message is required")
    @Length(min = 5, max = 1000, message = "Message should be between 5 and 1000 characters")
    private String message;

    @NotNull(message = "Classroom ID is required")
    private Long classroomId;

    @NotNull(message = "CreatedBy is required")
    private Long createdBy;

    // Constructors
    public AnnouncementRequestDTO() {
    }

    public AnnouncementRequestDTO(String title, String message,
                                  Long classroomId, Long createdBy) {
        this.title = title;
        this.message = message;
        this.classroomId = classroomId;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
