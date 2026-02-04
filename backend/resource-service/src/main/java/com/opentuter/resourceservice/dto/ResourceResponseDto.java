package com.opentuter.resourceservice.dto;

import java.util.UUID;

public class ResourceResponseDto {

    private UUID id;
    private UUID moduleId;
    private String title;
    private String type;
    private String fileUrl;

    // Constructors
    public ResourceResponseDto() {
    }

    public ResourceResponseDto(UUID id, UUID moduleId, String title, String type, String fileUrl) {
        this.id = id;
        this.moduleId = moduleId;
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

