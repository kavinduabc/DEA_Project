package com.opentutor.qaservice.repository;

import com.opentutor.qaservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    
    List<Question> findByClassroomIdOrderByCreatedAtDesc(String classroomId);
    
    List<Question> findByUserIdOrderByCreatedAtDesc(UUID userId);

    List<Question> findByIsResolvedOrderByCreatedAtDesc(Boolean isResolved);
    
    List<Question> findByClassroomIdAndIsResolvedOrderByCreatedAtDesc(String classroomId, Boolean isResolved);
    
    List<Question> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titleKeyword, String contentKeyword);
}