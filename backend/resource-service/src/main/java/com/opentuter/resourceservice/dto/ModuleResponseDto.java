package com.opentuter.resourceservice.dto;
import java.util.UUID;

public class ModuleResponseDto {

    private UUID id;
    private String title;
    private UUID classroomId;

    public ModuleResponseDto() {}

    public ModuleResponseDto(UUID id, String title, UUID classroomId) {
        this.id = id;
        this.title = title;
        this.classroomId = classroomId;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }
}
