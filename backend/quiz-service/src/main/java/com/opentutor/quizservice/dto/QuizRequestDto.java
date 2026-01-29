package com.opentutor.quizservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class QuizRequestDto {
    @NotNull(message = "Module ID is required")
    private UUID moduleId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Time limit is required")
    @Min(value = 1, message = "Time limit must be at least 1 minute")
    private Integer timeLimit;

    @NotNull(message = "Passing score is required")
    @Min(value = 0, message = "Passing score must be at least 0")
    private Integer passingScore;

    public QuizRequestDto() {
    }

    public QuizRequestDto(UUID moduleId, String title, Integer timeLimit, Integer passingScore) {
        this.moduleId = moduleId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.passingScore = passingScore;
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
