package com.opentutor.qaservice.controller;

import com.opentutor.qaservice.dto.AnswerRequestDto;
import com.opentutor.qaservice.dto.AnswerResponseDto;
import com.opentutor.qaservice.dto.AnswerUpdateDto;
import com.opentutor.qaservice.dto.QuestionRequestDto;
import com.opentutor.qaservice.dto.QuestionResponseDto;
import com.opentutor.qaservice.dto.QuestionUpdateDto;
import com.opentutor.qaservice.service.QaService;
import com.opentutor.qaservice.util.QaUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(QaUtil.QA_BASE_PATH)
@CrossOrigin(origins = "*")
public class QaController {

    @Autowired
    private QaService qaService;


    @PostMapping(QaUtil.QUESTIONS_PATH)
    public ResponseEntity<QuestionResponseDto> createQuestion(@Valid @RequestBody QuestionRequestDto requestDto) {
        QuestionResponseDto response = qaService.createQuestion(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

  
    @GetMapping(QaUtil.QUESTIONS_BY_ID_PATH)
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable String id) {
        QuestionResponseDto response = qaService.getQuestionById(id);
        return ResponseEntity.ok(response);
    }

 
    @GetMapping(QaUtil.QUESTIONS_PATH)
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {
        List<QuestionResponseDto> questions = qaService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

   
    @GetMapping(QaUtil.QUESTIONS_BY_CLASSROOM_PATH)
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByClassroomId(@PathVariable String classroomId) {
        List<QuestionResponseDto> questions = qaService.getQuestionsByClassroomId(classroomId);
        return ResponseEntity.ok(questions);
    }

  
    @GetMapping(QaUtil.QUESTIONS_BY_USER_PATH)
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByUserId(@PathVariable UUID userId) {
        List<QuestionResponseDto> questions = qaService.getQuestionsByUserId(userId);
        return ResponseEntity.ok(questions);
    }

 
    @GetMapping(QaUtil.QUESTIONS_UNRESOLVED_PATH)
    public ResponseEntity<List<QuestionResponseDto>> getUnresolvedQuestions() {
        List<QuestionResponseDto> questions = qaService.getUnresolvedQuestions();
        return ResponseEntity.ok(questions);
    }


    @GetMapping(QaUtil.QUESTIONS_UNRESOLVED_BY_CLASSROOM_PATH)
    public ResponseEntity<List<QuestionResponseDto>> getUnresolvedQuestionsByClassroomId(@PathVariable String classroomId) {
        List<QuestionResponseDto> questions = qaService.getUnresolvedQuestionsByClassroomId(classroomId);
        return ResponseEntity.ok(questions);
    }


    @GetMapping(QaUtil.QUESTIONS_SEARCH_PATH)
    public ResponseEntity<List<QuestionResponseDto>> searchQuestions(@RequestParam String keyword) {
        List<QuestionResponseDto> questions = qaService.searchQuestions(keyword);
        return ResponseEntity.ok(questions);
    }


    @PutMapping(QaUtil.QUESTIONS_BY_ID_PATH)
    public ResponseEntity<QuestionResponseDto> updateQuestion(
            @PathVariable String id,
            @RequestBody QuestionUpdateDto updateDto) {
        QuestionResponseDto response = qaService.updateQuestion(id, updateDto);
        return ResponseEntity.ok(response);
    }


    @PatchMapping(QaUtil.QUESTIONS_RESOLVE_PATH)
    public ResponseEntity<QuestionResponseDto> markAsResolved(
            @PathVariable String questionId,
            @PathVariable String answerId) {
        QuestionResponseDto response = qaService.markAsResolved(questionId, answerId);
        return ResponseEntity.ok(response);
    }


    @PatchMapping(QaUtil.QUESTIONS_UNRESOLVE_PATH)
    public ResponseEntity<QuestionResponseDto> markAsUnresolved(@PathVariable String questionId) {
        QuestionResponseDto response = qaService.markAsUnresolved(questionId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(QaUtil.QUESTIONS_BY_ID_PATH)
    public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
        qaService.deleteQuestion(id);
        return ResponseEntity.ok("Question with ID " + id + " has been deleted successfully.");
    }


        @PostMapping(QaUtil.ANSWERS_PATH)
    public ResponseEntity<AnswerResponseDto> createAnswer(@Valid @RequestBody AnswerRequestDto requestDto) {
        AnswerResponseDto response = qaService.createAnswer(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(QaUtil.ANSWERS_BY_ID_PATH)
    public ResponseEntity<AnswerResponseDto> getAnswerById(@PathVariable String id) {
        AnswerResponseDto response = qaService.getAnswerById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping(QaUtil.ANSWERS_BY_QUESTION_PATH)
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByQuestionId(@PathVariable String questionId) {
        List<AnswerResponseDto> answers = qaService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

 
    @GetMapping(QaUtil.ANSWERS_BY_USER_PATH)
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByUserId(@PathVariable UUID userId) {
        List<AnswerResponseDto> answers = qaService.getAnswersByUserId(userId);
        return ResponseEntity.ok(answers);
    }

    
    @PutMapping(QaUtil.ANSWERS_BY_ID_PATH)
    public ResponseEntity<AnswerResponseDto> updateAnswer(
            @PathVariable String id,
            @RequestBody AnswerUpdateDto updateDto) {
        AnswerResponseDto response = qaService.updateAnswer(id, updateDto);
        return ResponseEntity.ok(response);
    }

  
    @PatchMapping(QaUtil.ANSWERS_ACCEPT_PATH)
    public ResponseEntity<AnswerResponseDto> markAsAccepted(@PathVariable String id) {
        AnswerResponseDto response = qaService.markAsAccepted(id);
        return ResponseEntity.ok(response);
    }

    
    @PatchMapping(QaUtil.ANSWERS_UNACCEPT_PATH)
    public ResponseEntity<AnswerResponseDto> unmarkAsAccepted(@PathVariable String id) {
        AnswerResponseDto response = qaService.unmarkAsAccepted(id);
        return ResponseEntity.ok(response);
    }


    @PatchMapping(QaUtil.ANSWERS_UPVOTE_PATH)
    public ResponseEntity<AnswerResponseDto> upvoteAnswer(@PathVariable String id) {
        AnswerResponseDto response = qaService.upvoteAnswer(id);
        return ResponseEntity.ok(response);
    }


    @PatchMapping(QaUtil.ANSWERS_REMOVE_UPVOTE_PATH)
    public ResponseEntity<AnswerResponseDto> removeUpvote(@PathVariable String id) {
        AnswerResponseDto response = qaService.removeUpvote(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(QaUtil.ANSWERS_BY_ID_PATH)
    public ResponseEntity<String> deleteAnswer(@PathVariable String id) {
        qaService.deleteAnswer(id);
        return ResponseEntity.ok("Answer with ID " + id + " has been deleted successfully.");
    }
}

