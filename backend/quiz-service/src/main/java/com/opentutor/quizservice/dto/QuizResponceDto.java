package com.opentutor.quizservice.dto;

import java.util.UUID;

public class QuizResponceDto {
    private UUID id;
    private UUID moduleId;
    private String title;
    private Integer timeLimit;
    private Integer passingScore;

    public QuizResponceDto() {
    }

    public QuizResponceDto(UUID id, UUID moduleId, String title, Integer timeLimit, Integer passingScore) {
        this.id = id;
        this.moduleId = moduleId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.passingScore = passingScore;
    }

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

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(Integer passingScore) {
        this.passingScore = passingScore;
    }
}
