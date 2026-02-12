package com.opentutor.announcement_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class AnnouncementRequestDTO {

    @NotBlank(message = "Title is required")
    @Length(min = 3, max = 100, message = "Title should be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Message is required")
    @Length(min = 5, max = 1000, message = "Message should be between 5 and 1000 characters")
    private String message;

    @NotNull(message = "Classroom ID is required")
    private UUID classroomId;

    @NotNull(message = "Teacher ID is required")
    private UUID teacherId;

    public AnnouncementRequestDTO() {
    }

    public AnnouncementRequestDTO(String title, String message, UUID classroomId, UUID teacherId) {
        this.title = title;
        this.message = message;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
    }

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

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }
}
