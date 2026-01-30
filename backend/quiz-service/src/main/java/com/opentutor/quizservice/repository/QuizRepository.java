package com.opentutor.quizservice.repository;

import com.opentutor.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findByModuleId(UUID moduleId);
    Optional<Quiz> findByModuleIdAndTitle(UUID moduleId, String title);
    Optional<Quiz> findByModuleIdAndTitleAndIdNot(UUID moduleId, String title, UUID id);
}
