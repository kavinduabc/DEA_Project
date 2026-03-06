package com.opentutor.qaservice.repository;

import com.opentutor.qaservice.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
    
    List<Answer> findByQuestionIdOrderByIsAcceptedDescUpvotesDescCreatedAtDesc(String questionId);
    
    List<Answer> findByUserIdOrderByCreatedAtDesc(UUID userId);

    Long countByQuestionId(String questionId);
}