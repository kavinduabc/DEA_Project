package com.opentutor.quizservice.service;

import com.opentutor.quizservice.dto.QuizRequestDto;
import com.opentutor.quizservice.dto.QuizResponceDto;
import com.opentutor.quizservice.dto.QuizUpdateDto;
import com.opentutor.quizservice.exception.DuplicateResourceException;
import com.opentutor.quizservice.exception.DeletionNotAllowedException;
import com.opentutor.quizservice.exception.ResourceNotFoundException;
import com.opentutor.quizservice.mapper.QuizMapper;
import com.opentutor.quizservice.model.Quiz;
import com.opentutor.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    public QuizResponceDto createQuiz(QuizRequestDto requestDto) {
        if (quizRepository.findByModuleIdAndTitle(requestDto.getModuleId(), requestDto.getTitle()).isPresent()) {
            throw new DuplicateResourceException("Quiz", "title", requestDto.getTitle());
        }

        Quiz quiz = quizMapper.toEntity(requestDto);
        Quiz savedQuiz = quizRepository.save(quiz);
        return quizMapper.toDto(savedQuiz);
    }

    public QuizResponceDto getQuizById(String id) {
        try {
            Quiz quiz = quizRepository.findById(java.util.UUID.fromString(id))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", id));
            return quizMapper.toDto(quiz);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + id);
        }
    }

    public List<QuizResponceDto> getQuizzesByModuleId(String moduleId) {
        try {
            List<Quiz> quizzes = quizRepository.findByModuleId(java.util.UUID.fromString(moduleId));
            return quizzes.stream()
                    .map(quizMapper::toDto)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + moduleId);
        }
    }

    public QuizResponceDto updateQuiz(String id, QuizUpdateDto updateDto) {
        try {
            Quiz existingQuiz = quizRepository.findById(java.util.UUID.fromString(id))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", id));

            if (quizRepository.findByModuleIdAndTitleAndIdNot(
                    existingQuiz.getModuleId(),
                    updateDto.getTitle(),
                    existingQuiz.getId()).isPresent()) {
                throw new DuplicateResourceException("Quiz", "title", updateDto.getTitle());
            }

            existingQuiz.setTitle(updateDto.getTitle());
            existingQuiz.setTimeLimit(updateDto.getTimeLimit());
            existingQuiz.setPassingScore(updateDto.getPassingScore());

            Quiz updatedQuiz = quizRepository.save(existingQuiz);
            return quizMapper.toDto(updatedQuiz);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Invalid UUID format") || e.getMessage().contains("Invalid UUID string")) {
                throw new IllegalArgumentException("Invalid UUID format: " + id);
            }
            throw e;
        }
    }

    public void deleteQuiz(String id) {
        try {
            Quiz quiz = quizRepository.findById(java.util.UUID.fromString(id))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", id));

            // Check if quiz has any questions
            if (quiz.getQuestions() != null && !quiz.getQuestions().isEmpty()) {
                throw new DeletionNotAllowedException(
                        "Cannot delete quiz with ID " + id + " because it contains " +
                        quiz.getQuestions().size() + " question(s). Please remove all questions before deleting the quiz.");
            }

            // Check if quiz has any attempts
            if (quiz.getAttempts() != null && !quiz.getAttempts().isEmpty()) {
                throw new DeletionNotAllowedException(
                        "Cannot delete quiz with ID " + id + " because it has " +
                        quiz.getAttempts().size() + " attempt(s). Quizzes with student attempts cannot be deleted.");
            }

            quizRepository.delete(quiz);
        } catch (IllegalArgumentException e) {
            // Check if it's a UUID parsing error (not thrown from orElseThrow)
            if (e.getMessage().contains("Invalid UUID format") || e.getMessage().contains("Invalid UUID string")) {
                throw new IllegalArgumentException("Invalid UUID format: " + id);
            }
            throw e;
        }
    }
}
