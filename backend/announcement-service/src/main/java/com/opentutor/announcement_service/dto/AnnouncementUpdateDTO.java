package com.opentutor.announcement_service.dto;

import org.hibernate.validator.constraints.Length;

public class AnnouncementUpdateDTO {

    @Length(min = 3, max = 100, message = "Title should be between 3 and 100 characters")
    private String title;

    @Length(min = 5, max = 1000, message = "Message should be between 5 and 1000 characters")
    private String message;

    // Optional: allow moving announcement to another classroom
    private Long classroomId;

    // Optional: allow changing createdBy (usually NOT recommended)
    private Long createdBy;

    public AnnouncementUpdateDTO() {}

    public AnnouncementUpdateDTO(String title, String message, Long classroomId, Long createdBy) {
        this.title = title;
        this.message = message;
        this.classroomId = classroomId;
        this.createdBy = createdBy;
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
