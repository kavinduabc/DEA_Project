package com.opentutor.quizservice.dto;

import java.time.LocalDateTime;

public class AttemptResponseDto {
    private String id;
    private String quizId;
    private String studentId;
    private Double score;
    private LocalDateTime submittedAt;

    public AttemptResponseDto() {
    }

    public AttemptResponseDto(String id, String quizId, String studentId, Double score, LocalDateTime submittedAt) {
        this.id = id;
        this.quizId = quizId;
        this.studentId = studentId;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
