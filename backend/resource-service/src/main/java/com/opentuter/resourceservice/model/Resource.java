package com.opentuter.resourceservice.model;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type; // PDF, VIDEO, LINK

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    // ===== Constructors =====

    public Resource() {
    }

    public Resource(UUID moduleId, String title, String type, String fileUrl) {
        this.moduleId = moduleId;
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
    }

    // ===== Getters & Setters =====

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
