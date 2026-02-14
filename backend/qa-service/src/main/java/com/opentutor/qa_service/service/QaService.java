package com.opentutor.qa_service.service;

import com.opentutor.qa_service.dto.AnswerRequestDto;
import com.opentutor.qa_service.dto.AnswerResponseDto;
import com.opentutor.qa_service.dto.AnswerUpdateDto;
import com.opentutor.qa_service.dto.QuestionRequestDto;
import com.opentutor.qa_service.dto.QuestionResponseDto;
import com.opentutor.qa_service.dto.QuestionUpdateDto;
import com.opentutor.qa_service.exception.DeletionNotAllowedException;
import com.opentutor.qa_service.exception.ResourceNotFoundException;
import com.opentutor.qa_service.mapper.AnswerMapper;
import com.opentutor.qa_service.mapper.QuestionMapper;
import com.opentutor.qa_service.model.Answer;
import com.opentutor.qa_service.model.Question;
import com.opentutor.qa_service.repository.AnswerRepository;
import com.opentutor.qa_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QaService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    
    public QuestionResponseDto createQuestion(QuestionRequestDto requestDto) {
        Question question = questionMapper.toEntity(requestDto);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDtoWithoutAnswers(savedQuestion);
    }


    public QuestionResponseDto getQuestionById(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
        
        question.incrementViewCount();
        questionRepository.save(question);
        
        return questionMapper.toResponseDto(question);
    }

 
    public List<QuestionResponseDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

  
    public List<QuestionResponseDto> getQuestionsByClassroomId(String classroomId) {
        List<Question> questions = questionRepository.findByClassroomIdOrderByCreatedAtDesc(classroomId);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

   
    public List<QuestionResponseDto> getQuestionsByUserId(Long userId) {
        List<Question> questions = questionRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

  
    public List<QuestionResponseDto> getUnresolvedQuestions() {
        List<Question> questions = questionRepository.findByIsResolvedOrderByCreatedAtDesc(false);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    public List<QuestionResponseDto> getUnresolvedQuestionsByClassroomId(String classroomId) {
        List<Question> questions = questionRepository.findByClassroomIdAndIsResolvedOrderByCreatedAtDesc(classroomId, false);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

 
    public List<QuestionResponseDto> searchQuestions(String keyword) {
        List<Question> questions = questionRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }


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


    public QuestionResponseDto markAsResolved(String questionId, String answerId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        question.setIsResolved(true);
        question.setResolvedAnswerId(answerId);
        
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDto(updatedQuestion);
    }

  
    public QuestionResponseDto markAsUnresolved(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        question.setIsResolved(false);
        question.setResolvedAnswerId(null);
        
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDto(updatedQuestion);
    }

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

 
    public void incrementAnswerCount(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));
        question.incrementAnswerCount();
        questionRepository.save(question);
    }


    public void decrementAnswerCount(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));
        question.decrementAnswerCount();
        questionRepository.save(question);
    }

  
    public AnswerResponseDto createAnswer(AnswerRequestDto requestDto) {
        // Verify that the question exists
        questionRepository.findById(requestDto.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + requestDto.getQuestionId()));

        Answer answer = answerMapper.toEntity(requestDto);
        Answer savedAnswer = answerRepository.save(answer);
        
        // Increment answer count on the question
        incrementAnswerCount(requestDto.getQuestionId());
        
        return answerMapper.toResponseDto(savedAnswer);
    }

 
    public AnswerResponseDto getAnswerById(String id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));
        return answerMapper.toResponseDto(answer);
    }


    public List<AnswerResponseDto> getAnswersByQuestionId(String questionId) {
        // Verify that the question exists
        questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        List<Answer> answers = answerRepository.findByQuestionIdOrderByIsAcceptedDescUpvotesDescCreatedAtDesc(questionId);
        return answers.stream()
                .map(answerMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    public List<AnswerResponseDto> getAnswersByUserId(Long userId) {
        List<Answer> answers = answerRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return answers.stream()
                .map(answerMapper::toResponseDto)
                .collect(Collectors.toList());
    }


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
        markAsResolved(answer.getQuestionId(), answerId);

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
        markAsUnresolved(answer.getQuestionId());

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
        decrementAnswerCount(questionId);
    }
}