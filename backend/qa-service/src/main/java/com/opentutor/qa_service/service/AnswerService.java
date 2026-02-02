package com.opentutor.qa_service.service;

import com.opentutor.qa_service.dto.AnswerRequestDto;
import com.opentutor.qa_service.dto.AnswerResponseDto;
import com.opentutor.qa_service.dto.AnswerUpdateDto;
import com.opentutor.qa_service.exception.DeletionNotAllowedException;
import com.opentutor.qa_service.exception.ResourceNotFoundException;
import com.opentutor.qa_service.mapper.AnswerMapper;
import com.opentutor.qa_service.model.Answer;
import com.opentutor.qa_service.repository.AnswerRepository;
import com.opentutor.qa_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionService questionService;

    /**
     * Create a new answer
     */
    public AnswerResponseDto createAnswer(AnswerRequestDto requestDto) {
        // Verify that the question exists
        questionRepository.findById(requestDto.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + requestDto.getQuestionId()));

        Answer answer = answerMapper.toEntity(requestDto);
        Answer savedAnswer = answerRepository.save(answer);
        
        // Increment answer count on the question
        questionService.incrementAnswerCount(requestDto.getQuestionId());
        
        return answerMapper.toResponseDto(savedAnswer);
    }

    /**
     * Get answer by ID
     */
    public AnswerResponseDto getAnswerById(String id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));
        return answerMapper.toResponseDto(answer);
    }

    /**
     * Get all answers for a question
     */
    public List<AnswerResponseDto> getAnswersByQuestionId(String questionId) {
        // Verify that the question exists
        questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        List<Answer> answers = answerRepository.findByQuestionIdOrderByIsAcceptedDescUpvotesDescCreatedAtDesc(questionId);
        return answers.stream()
                .map(answerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Get answers by user ID
     */

    public List<AnswerResponseDto> getAnswersByUserId(Long userId) {
        List<Answer> answers = answerRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return answers.stream()
                .map(answerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Update an answer
     */
    public AnswerResponseDto updateAnswer(String id, AnswerUpdateDto updateDto) {
        Answer existingAnswer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));

        // Check if answer is already accepted (prevent editing accepted answers)
        if (existingAnswer.getIsAccepted()) {
            throw new DeletionNotAllowedException("Cannot edit an accepted answer");
        }

        if (updateDto.getContent() != null && !updateDto.getContent().isEmpty()) {
            existingAnswer.setContent(updateDto.getContent());
        }

        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return answerMapper.toResponseDto(updatedAnswer);
    }

    /**
     * Mark answer as accepted
     */
    public AnswerResponseDto markAsAccepted(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        // Unmark any previously accepted answer for this question
        List<Answer> previouslyAccepted = answerRepository
                .findByQuestionIdOrderByIsAcceptedDescUpvotesDescCreatedAtDesc(answer.getQuestionId())
                .stream()
                .filter(Answer::getIsAccepted)
                .collect(Collectors.toList());

        for (Answer prevAnswer : previouslyAccepted) {
            prevAnswer.setIsAccepted(false);
            answerRepository.save(prevAnswer);
        }

        // Mark this answer as accepted
        answer.setIsAccepted(true);
        Answer updatedAnswer = answerRepository.save(answer);

        // Update the question to mark it as resolved
        questionService.markAsResolved(answer.getQuestionId(), answerId);

        return answerMapper.toResponseDto(updatedAnswer);
    }

    /**
     * Unmark answer as accepted
     */
    public AnswerResponseDto unmarkAsAccepted(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        answer.setIsAccepted(false);
        Answer updatedAnswer = answerRepository.save(answer);

        // Update the question to mark it as unresolved
        questionService.markAsUnresolved(answer.getQuestionId());

        return answerMapper.toResponseDto(updatedAnswer);
    }

    /**
     * Upvote an answer
     */
    public AnswerResponseDto upvoteAnswer(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        answer.incrementUpvotes();
        Answer updatedAnswer = answerRepository.save(answer);
        return answerMapper.toResponseDto(updatedAnswer);
    }

    /**
     * Remove upvote from an answer
     */
    public AnswerResponseDto removeUpvote(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        answer.decrementUpvotes();
        Answer updatedAnswer = answerRepository.save(answer);
        return answerMapper.toResponseDto(updatedAnswer);
    }

    /**
     * Delete an answer
     */
    public void deleteAnswer(String id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));

        // Prevent deletion of accepted answers
        if (answer.getIsAccepted()) {
            throw new DeletionNotAllowedException("Cannot delete an accepted answer. Please unmark it first.");
        }

        String questionId = answer.getQuestionId();
        answerRepository.delete(answer);
        
        // Decrement answer count on the question
        questionService.decrementAnswerCount(questionId);
    }
}