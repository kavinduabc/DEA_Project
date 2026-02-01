package com.opentutor.quizservice.service;

import com.opentutor.quizservice.dto.AttemptRequestDto;
import com.opentutor.quizservice.dto.AttemptResponseDto;
import com.opentutor.quizservice.dto.QuestionRequestDto;
import com.opentutor.quizservice.dto.QuestionResponseDto;
import com.opentutor.quizservice.dto.QuizRequestDto;
import com.opentutor.quizservice.dto.QuizResponceDto;
import com.opentutor.quizservice.dto.QuizUpdateDto;
import com.opentutor.quizservice.exception.DuplicateResourceException;
import com.opentutor.quizservice.exception.DeletionNotAllowedException;
import com.opentutor.quizservice.exception.ResourceNotFoundException;
import com.opentutor.quizservice.mapper.QuestionMapper;
import com.opentutor.quizservice.mapper.QuizMapper;
import com.opentutor.quizservice.model.Attempt;
import com.opentutor.quizservice.model.Question;
import com.opentutor.quizservice.model.Quiz;
import com.opentutor.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    @Autowired
    private QuestionMapper questionMapper;

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

    public QuestionResponseDto createQuestion(String quizId, QuestionRequestDto requestDto) {
        try {
            Quiz quiz = quizRepository.findById(java.util.UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", quizId));

            Question question = questionMapper.toEntity(requestDto, quiz);

            if (quiz.getQuestions() == null) {
                quiz.setQuestions(new java.util.ArrayList<>());
            }
            quiz.getQuestions().add(question);

            Quiz savedQuiz = quizRepository.save(quiz);

            Question savedQuestion = savedQuiz.getQuestions().getLast();

            return questionMapper.toDto(savedQuestion);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Invalid UUID format") || e.getMessage().contains("Invalid UUID string")) {
                throw new IllegalArgumentException("Invalid UUID format: " + quizId);
            }
            throw e;
        }
    }

    public QuestionResponseDto getQuestionById(String id) {
        try {
            java.util.UUID questionId = java.util.UUID.fromString(id);

            List<Quiz> allQuizzes = quizRepository.findAll();
            for (Quiz quiz : allQuizzes) {
                if (quiz.getQuestions() != null) {
                    for (Question question : quiz.getQuestions()) {
                        if (question.getId().equals(questionId)) {
                            return questionMapper.toDto(question);
                        }
                    }
                }
            }

            throw new ResourceNotFoundException("Question", "id", id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + id);
        }
    }

    public List<QuestionResponseDto> getQuestionsByQuizId(String quizId) {
        try {
            Quiz quiz = quizRepository.findById(java.util.UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", quizId));

            if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
                return new java.util.ArrayList<>();
            }

            return quiz.getQuestions().stream()
                    .map(questionMapper::toDto)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + quizId);
        }
    }

    public void deleteQuestion(String id) {
        try {
            java.util.UUID questionId = java.util.UUID.fromString(id);

            List<Quiz> allQuizzes = quizRepository.findAll();
            for (Quiz quiz : allQuizzes) {
                if (quiz.getQuestions() != null) {
                    Question questionToRemove = null;
                    for (Question question : quiz.getQuestions()) {
                        if (question.getId().equals(questionId)) {
                            questionToRemove = question;
                            break;
                        }
                    }

                    if (questionToRemove != null) {
                        quiz.getQuestions().remove(questionToRemove);
                        quizRepository.save(quiz);
                        return;
                    }
                }
            }

            throw new ResourceNotFoundException("Question", "id", id);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Invalid UUID format") || e.getMessage().contains("Invalid UUID string")) {
                throw new IllegalArgumentException("Invalid UUID format: " + id);
            }
            throw e;
        }
    }

    public AttemptResponseDto createAttempt(String quizId, AttemptRequestDto requestDto) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", quizId));

            Attempt attempt = new Attempt();
            attempt.setQuiz(quiz);
            attempt.setStudentId(UUID.fromString(requestDto.getStudentId()));
            attempt.setScore(requestDto.getScore());

            if (quiz.getAttempts() == null) {
                quiz.setAttempts(new java.util.ArrayList<>());
            }
            quiz.getAttempts().add(attempt);

            Quiz savedQuiz = quizRepository.save(quiz);
            Attempt savedAttempt = savedQuiz.getAttempts().getLast();

            return mapAttemptToDto(savedAttempt);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Invalid UUID format") || e.getMessage().contains("Invalid UUID string")) {
                throw new IllegalArgumentException("Invalid UUID format");
            }
            throw e;
        }
    }

    public List<AttemptResponseDto> getAttemptsByQuizId(String quizId) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", quizId));

            if (quiz.getAttempts() == null || quiz.getAttempts().isEmpty()) {
                return new java.util.ArrayList<>();
            }

            return quiz.getAttempts().stream()
                    .map(this::mapAttemptToDto)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + quizId);
        }
    }

    private AttemptResponseDto mapAttemptToDto(Attempt attempt) {
        AttemptResponseDto dto = new AttemptResponseDto();
        dto.setId(attempt.getId().toString());
        dto.setQuizId(attempt.getQuiz().getId().toString());
        dto.setStudentId(attempt.getStudentId().toString());
        dto.setScore(attempt.getScore());
        dto.setSubmittedAt(attempt.getSubmittedAt());
        return dto;
    }
}
