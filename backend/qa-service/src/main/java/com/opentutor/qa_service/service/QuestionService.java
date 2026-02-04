package com.opentutor.qa_service.service;

import com.opentutor.qa_service.dto.QuestionRequestDto;
import com.opentutor.qa_service.dto.QuestionResponseDto;
import com.opentutor.qa_service.dto.QuestionUpdateDto;
import com.opentutor.qa_service.exception.DeletionNotAllowedException;
import com.opentutor.qa_service.exception.ResourceNotFoundException;
import com.opentutor.qa_service.mapper.QuestionMapper;
import com.opentutor.qa_service.model.Question;
import com.opentutor.qa_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * Create a new question
     */
    public QuestionResponseDto createQuestion(QuestionRequestDto requestDto) {
        Question question = questionMapper.toEntity(requestDto);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDtoWithoutAnswers(savedQuestion);
    }

    /**
     * Get question by ID with all answers
     */
    public QuestionResponseDto getQuestionById(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
        
        // Increment view count
        question.incrementViewCount();
        questionRepository.save(question);
        
        return questionMapper.toResponseDto(question);
    }

    /**
     * Get all questions
     */
    public List<QuestionResponseDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    /**
     * Get questions by classroom ID
     */
    public List<QuestionResponseDto> getQuestionsByClassroomId(String classroomId) {
        List<Question> questions = questionRepository.findByClassroomIdOrderByCreatedAtDesc(classroomId);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    /**
     * Get questions by user ID
     */
    public List<QuestionResponseDto> getQuestionsByUserId(Long userId) {
        List<Question> questions = questionRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    /**
     * Get unresolved questions
     */
    public List<QuestionResponseDto> getUnresolvedQuestions() {
        List<Question> questions = questionRepository.findByIsResolvedOrderByCreatedAtDesc(false);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    /**
     * Get unresolved questions by classroom ID
     */
    public List<QuestionResponseDto> getUnresolvedQuestionsByClassroomId(String classroomId) {
        List<Question> questions = questionRepository.findByClassroomIdAndIsResolvedOrderByCreatedAtDesc(classroomId, false);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    /**
     * Search questions by keyword
     */
    public List<QuestionResponseDto> searchQuestions(String keyword) {
        List<Question> questions = questionRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    /**
     * Update a question
     */
    public QuestionResponseDto updateQuestion(String id, QuestionUpdateDto updateDto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        // Update fields if provided
        if (updateDto.getTitle() != null && !updateDto.getTitle().isEmpty()) {
            existingQuestion.setTitle(updateDto.getTitle());
        }
        if (updateDto.getContent() != null && !updateDto.getContent().isEmpty()) {
            existingQuestion.setContent(updateDto.getContent());
        }
        if (updateDto.getTags() != null) {
            existingQuestion.setTags(updateDto.getTags());
        }

        Question updatedQuestion = questionRepository.save(existingQuestion);
        return questionMapper.toResponseDto(updatedQuestion);
    }

    /**
     * Mark question as resolved
     */
    public QuestionResponseDto markAsResolved(String questionId, String answerId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        question.setIsResolved(true);
        question.setResolvedAnswerId(answerId);
        
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDto(updatedQuestion);
    }

    /**
     * Mark question as unresolved
     */
    public QuestionResponseDto markAsUnresolved(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        question.setIsResolved(false);
        question.setResolvedAnswerId(null);
        
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDto(updatedQuestion);
    }

    /**
     * Delete a question
     */
    public void deleteQuestion(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        // Check if question has answers
        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            throw new DeletionNotAllowedException(
                    "Cannot delete question with ID " + id + " because it has " +
                    question.getAnswers().size() + " answer(s). Please remove all answers before deleting the question.");
        }

        questionRepository.delete(question);
    }

    /**
     * Internal method to increment answer count
     */
    public void incrementAnswerCount(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));
        question.incrementAnswerCount();
        questionRepository.save(question);
    }

    /**
     * Internal method to decrement answer count
     */
    public void decrementAnswerCount(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));
        question.decrementAnswerCount();
        questionRepository.save(question);
    }
}