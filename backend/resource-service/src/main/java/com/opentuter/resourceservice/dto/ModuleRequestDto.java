package com.opentuter.resourceservice.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ModuleRequestDto {

    @NotBlank(message = "Module title is required")
    private String title;

    @NotNull(message = "Classroom ID is required")
    private UUID classroomId;

    public ModuleRequestDto() {}

    public ModuleRequestDto(String title, UUID classroomId) {
        this.title = title;
        this.classroomId = classroomId;
    }

    public String getTitle() {
        return title;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }
}
