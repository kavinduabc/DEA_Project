package com.opentuter.resourceservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ResourceRequestDto {

    @NotNull(message = "Module ID is required")
    private UUID moduleId;

    @NotBlank(message = "Resource title is required")
    private String title;

    @NotBlank(message = "Resource type is required")
    private String type; // PDF, VIDEO, LINK

    @NotBlank(message = "File URL is required")
    private String fileUrl;

    // Constructors
    public ResourceRequestDto() {
    }

    public ResourceRequestDto(UUID moduleId, String title, String type, String fileUrl) {
        this.moduleId = moduleId;
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters
    public UUID getModuleId() {
        return moduleId;
    }

    public void setModuleId(UUID moduleId) {
        this.moduleId = moduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
