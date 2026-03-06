package com.opentutor.qaservice.service.impl;

import com.opentutor.qaservice.client.ClassroomServiceClient;
import com.opentutor.qaservice.client.UserServiceClient;
import com.opentutor.qaservice.dto.AnswerRequestDto;
import com.opentutor.qaservice.dto.AnswerResponseDto;
import com.opentutor.qaservice.dto.AnswerUpdateDto;
import com.opentutor.qaservice.dto.QuestionRequestDto;
import com.opentutor.qaservice.dto.QuestionResponseDto;
import com.opentutor.qaservice.dto.QuestionUpdateDto;
import com.opentutor.qaservice.exception.DeletionNotAllowedException;
import com.opentutor.qaservice.exception.ResourceNotFoundException;
import com.opentutor.qaservice.mapper.AnswerMapper;
import com.opentutor.qaservice.mapper.QuestionMapper;
import com.opentutor.qaservice.model.Answer;
import com.opentutor.qaservice.model.Question;
import com.opentutor.qaservice.repository.AnswerRepository;
import com.opentutor.qaservice.repository.QuestionRepository;
import com.opentutor.qaservice.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class QaServiceImpl implements QaService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ClassroomServiceClient classroomServiceClient;

    @Override
    public QuestionResponseDto createQuestion(QuestionRequestDto requestDto) {
        // Validate user exists
        if (!userServiceClient.userExists(requestDto.getUserId())) {
            throw new ResourceNotFoundException("User not found with ID: " + requestDto.getUserId());
        }

        // Validate classroom exists
        if (!classroomServiceClient.classroomExists(requestDto.getClassroomId())) {
            throw new ResourceNotFoundException("Classroom not found with ID: " + requestDto.getClassroomId());
        }

        Question question = questionMapper.toEntity(requestDto);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDtoWithoutAnswers(savedQuestion);
    }

    @Override
    public QuestionResponseDto getQuestionById(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        question.incrementViewCount();
        questionRepository.save(question);

        return questionMapper.toResponseDto(question);
    }

    @Override
    public List<QuestionResponseDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDto> getQuestionsByClassroomId(String classroomId) {
        List<Question> questions = questionRepository.findByClassroomIdOrderByCreatedAtDesc(classroomId);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDto> getQuestionsByUserId(UUID userId) {
        List<Question> questions = questionRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDto> getUnresolvedQuestions() {
        List<Question> questions = questionRepository.findByIsResolvedOrderByCreatedAtDesc(false);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDto> getUnresolvedQuestionsByClassroomId(String classroomId) {
        List<Question> questions = questionRepository.findByClassroomIdAndIsResolvedOrderByCreatedAtDesc(classroomId, false);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDto> searchQuestions(String keyword) {
        List<Question> questions = questionRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return questions.stream()
                .map(questionMapper::toResponseDtoWithoutAnswers)
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
    public QuestionResponseDto markAsResolved(String questionId, String answerId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        question.setIsResolved(true);
        question.setResolvedAnswerId(answerId);

        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDto(updatedQuestion);
    }

    @Override
    public QuestionResponseDto markAsUnresolved(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        question.setIsResolved(false);
        question.setResolvedAnswerId(null);

        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponseDto(updatedQuestion);
    }

    @Override
    public void deleteQuestion(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));

        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            throw new DeletionNotAllowedException(
                    "Cannot delete question with ID " + id + " because it has " +
                    question.getAnswers().size() + " answer(s). Please remove all answers before deleting the question.");
        }

        questionRepository.delete(question);
    }

    @Override
    public void incrementAnswerCount(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));
        question.incrementAnswerCount();
        questionRepository.save(question);
    }

    @Override
    public void decrementAnswerCount(String questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));
        question.decrementAnswerCount();
        questionRepository.save(question);
    }

    @Override
    public AnswerResponseDto createAnswer(AnswerRequestDto requestDto) {
        questionRepository.findById(requestDto.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + requestDto.getQuestionId()));

        Answer answer = answerMapper.toEntity(requestDto);
        Answer savedAnswer = answerRepository.save(answer);

        incrementAnswerCount(requestDto.getQuestionId());

        return answerMapper.toResponseDto(savedAnswer);
    }

    @Override
    public AnswerResponseDto getAnswerById(String id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));
        return answerMapper.toResponseDto(answer);
    }

    @Override
    public List<AnswerResponseDto> getAnswersByQuestionId(String questionId) {
        questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        List<Answer> answers = answerRepository.findByQuestionIdOrderByIsAcceptedDescUpvotesDescCreatedAtDesc(questionId);
        return answers.stream()
                .map(answerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswerResponseDto> getAnswersByUserId(UUID userId) {
        List<Answer> answers = answerRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return answers.stream()
                .map(answerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerResponseDto updateAnswer(String id, AnswerUpdateDto updateDto) {
        Answer existingAnswer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));

        if (existingAnswer.getIsAccepted()) {
            throw new DeletionNotAllowedException("Cannot edit an accepted answer");
        }

        if (updateDto.getContent() != null && !updateDto.getContent().isEmpty()) {
            existingAnswer.setContent(updateDto.getContent());
        }

        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return answerMapper.toResponseDto(updatedAnswer);
    }

    @Override
    public AnswerResponseDto markAsAccepted(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        List<Answer> previouslyAccepted = answerRepository
                .findByQuestionIdOrderByIsAcceptedDescUpvotesDescCreatedAtDesc(answer.getQuestionId())
                .stream()
                .filter(Answer::getIsAccepted)
                .collect(Collectors.toList());

        for (Answer prevAnswer : previouslyAccepted) {
            prevAnswer.setIsAccepted(false);
            answerRepository.save(prevAnswer);
        }

        answer.setIsAccepted(true);
        Answer updatedAnswer = answerRepository.save(answer);

        markAsResolved(answer.getQuestionId(), answerId);

        return answerMapper.toResponseDto(updatedAnswer);
    }

    @Override
    public AnswerResponseDto unmarkAsAccepted(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        answer.setIsAccepted(false);
        Answer updatedAnswer = answerRepository.save(answer);

        markAsUnresolved(answer.getQuestionId());

        return answerMapper.toResponseDto(updatedAnswer);
    }

    @Override
    public AnswerResponseDto upvoteAnswer(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        answer.incrementUpvotes();
        Answer updatedAnswer = answerRepository.save(answer);
        return answerMapper.toResponseDto(updatedAnswer);
    }

    @Override
    public AnswerResponseDto removeUpvote(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + answerId));

        answer.decrementUpvotes();
        Answer updatedAnswer = answerRepository.save(answer);
        return answerMapper.toResponseDto(updatedAnswer);
    }

    @Override
    public void deleteAnswer(String id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID: " + id));

        if (answer.getIsAccepted()) {
            throw new DeletionNotAllowedException("Cannot delete an accepted answer. Please unmark it first.");
        }

        String questionId = answer.getQuestionId();
        answerRepository.delete(answer);

        decrementAnswerCount(questionId);
    }
}
