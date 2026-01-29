package com.opentutor.quizservice.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @Column(nullable = false)
    private String title;

    @Column(name = "time", nullable = false)
    private Integer timeLimit;

    @Column(name = "passing_score", nullable = false)
    private Integer passingScore;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Attempt> attempts;

    public Quiz(UUID id, UUID moduleId, String title, Integer timeLimit, Integer passingScore) {
        this.id = id;
        this.moduleId = moduleId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.passingScore = passingScore;
    }

    public Quiz() {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", moduleId=" + moduleId +
                ", title='" + title + '\'' +
                ", timeLimit=" + timeLimit +
                ", passingScore=" + passingScore +
                '}';
    }
}

