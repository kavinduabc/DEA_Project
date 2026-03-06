package com.opentutor.quizservice.service.impl;

import com.opentutor.quizservice.client.ResourceServiceClient;
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
import com.opentutor.quizservice.service.QuizService;
import com.opentutor.quizservice.util.QuizUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private ResourceServiceClient resourceServiceClient;

    @Override
    public QuizResponceDto createQuiz(QuizRequestDto requestDto) {
        if (!resourceServiceClient.isModuleValid(requestDto.getModuleId().toString())) {
            throw new ResourceNotFoundException(QuizUtil.ENTITY_MODULE, QuizUtil.FIELD_ID, requestDto.getModuleId());
        }

        if (quizRepository.findByModuleIdAndTitle(requestDto.getModuleId(), requestDto.getTitle()).isPresent()) {
            throw new DuplicateResourceException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_TITLE, requestDto.getTitle());
        }

        Quiz quiz = quizMapper.toEntity(requestDto);
        Quiz savedQuiz = quizRepository.save(quiz);
        return quizMapper.toDto(savedQuiz);
    }

    @Override
    public QuizResponceDto getQuizById(String id) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, id));
            return quizMapper.toDto(quiz);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + id);
        }
    }

    @Override
    public List<QuizResponceDto> getQuizzesByModuleId(String moduleId) {
        try {
            List<Quiz> quizzes = quizRepository.findByModuleId(UUID.fromString(moduleId));
            return quizzes.stream()
                    .map(quizMapper::toDto)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + moduleId);
        }
    }

    @Override
    public QuizResponceDto updateQuiz(String id, QuizUpdateDto updateDto) {
        try {
            Quiz existingQuiz = quizRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, id));

            if (quizRepository.findByModuleIdAndTitleAndIdNot(
                    existingQuiz.getModuleId(),
                    updateDto.getTitle(),
                    existingQuiz.getId()).isPresent()) {
                throw new DuplicateResourceException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_TITLE, updateDto.getTitle());
            }

            existingQuiz.setTitle(updateDto.getTitle());
            existingQuiz.setTimeLimit(updateDto.getTimeLimit());
            existingQuiz.setPassingScore(updateDto.getPassingScore());

            Quiz updatedQuiz = quizRepository.save(existingQuiz);
            return quizMapper.toDto(updatedQuiz);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains(QuizUtil.INVALID_UUID_FORMAT) || e.getMessage().contains(QuizUtil.INVALID_UUID_STRING)) {
                throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + id);
            }
            throw e;
        }
    }

    @Override
    public void deleteQuiz(String id) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, id));

            if (quiz.getQuestions() != null && !quiz.getQuestions().isEmpty()) {
                throw new DeletionNotAllowedException(
                        QuizUtil.DELETE_QUIZ_HAS_QUESTIONS_PREFIX + id +
                        QuizUtil.DELETE_QUIZ_HAS_QUESTIONS_MID +
                        quiz.getQuestions().size() +
                        QuizUtil.DELETE_QUIZ_HAS_QUESTIONS_SUFFIX);
            }

            if (quiz.getAttempts() != null && !quiz.getAttempts().isEmpty()) {
                throw new DeletionNotAllowedException(
                        QuizUtil.DELETE_QUIZ_HAS_QUESTIONS_PREFIX + id +
                        QuizUtil.DELETE_QUIZ_HAS_ATTEMPTS_MID +
                        quiz.getAttempts().size() +
                        QuizUtil.DELETE_QUIZ_HAS_ATTEMPTS_SUFFIX);
            }

            quizRepository.delete(quiz);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains(QuizUtil.INVALID_UUID_FORMAT) || e.getMessage().contains(QuizUtil.INVALID_UUID_STRING)) {
                throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + id);
            }
            throw e;
        }
    }

    @Override
    public QuestionResponseDto createQuestion(String quizId, QuestionRequestDto requestDto) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, quizId));

            Question question = questionMapper.toEntity(requestDto, quiz);

            if (quiz.getQuestions() == null) {
                quiz.setQuestions(new java.util.ArrayList<>());
            }
            quiz.getQuestions().add(question);

            Quiz savedQuiz = quizRepository.save(quiz);

            Question savedQuestion = savedQuiz.getQuestions().getLast();

            return questionMapper.toDto(savedQuestion);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains(QuizUtil.INVALID_UUID_FORMAT) || e.getMessage().contains(QuizUtil.INVALID_UUID_STRING)) {
                throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + quizId);
            }
            throw e;
        }
    }

    @Override
    public QuestionResponseDto getQuestionById(String id) {
        try {
            UUID questionId = UUID.fromString(id);

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

            throw new ResourceNotFoundException(QuizUtil.ENTITY_QUESTION, QuizUtil.FIELD_ID, id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + id);
        }
    }

    @Override
    public List<QuestionResponseDto> getQuestionsByQuizId(String quizId) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, quizId));

            if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
                return new java.util.ArrayList<>();
            }

            return quiz.getQuestions().stream()
                    .map(questionMapper::toDto)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + quizId);
        }
    }

    @Override
    public void deleteQuestion(String id) {
        try {
            UUID questionId = UUID.fromString(id);

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

            throw new ResourceNotFoundException(QuizUtil.ENTITY_QUESTION, QuizUtil.FIELD_ID, id);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains(QuizUtil.INVALID_UUID_FORMAT) || e.getMessage().contains(QuizUtil.INVALID_UUID_STRING)) {
                throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + id);
            }
            throw e;
        }
    }

    @Override
    public AttemptResponseDto createAttempt(String quizId, AttemptRequestDto requestDto) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, quizId));

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
            if (e.getMessage().contains(QuizUtil.INVALID_UUID_FORMAT) || e.getMessage().contains(QuizUtil.INVALID_UUID_STRING)) {
                throw new IllegalArgumentException(QuizUtil.INVALID_UUID_GENERIC);
            }
            throw e;
        }
    }

    @Override
    public List<AttemptResponseDto> getAttemptsByQuizId(String quizId) {
        try {
            Quiz quiz = quizRepository.findById(UUID.fromString(quizId))
                    .orElseThrow(() -> new ResourceNotFoundException(QuizUtil.ENTITY_QUIZ, QuizUtil.FIELD_ID, quizId));

            if (quiz.getAttempts() == null || quiz.getAttempts().isEmpty()) {
                return new java.util.ArrayList<>();
            }

            return quiz.getAttempts().stream()
                    .map(this::mapAttemptToDto)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(QuizUtil.INVALID_UUID_FORMAT + quizId);
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
