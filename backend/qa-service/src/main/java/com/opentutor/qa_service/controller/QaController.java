package com.opentutor.qa_service.controller;

import com.opentutor.qa_service.dto.AnswerRequestDto;
import com.opentutor.qa_service.dto.AnswerResponseDto;
import com.opentutor.qa_service.dto.AnswerUpdateDto;
import com.opentutor.qa_service.dto.QuestionRequestDto;
import com.opentutor.qa_service.dto.QuestionResponseDto;
import com.opentutor.qa_service.dto.QuestionUpdateDto;
import com.opentutor.qa_service.service.QaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa")
@CrossOrigin(origins = "*")
public class QaController {

    @Autowired
    private QaService qaService;


    @PostMapping("/questions")
    public ResponseEntity<QuestionResponseDto> createQuestion(@Valid @RequestBody QuestionRequestDto requestDto) {
        QuestionResponseDto response = qaService.createQuestion(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

  
    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable String id) {
        QuestionResponseDto response = qaService.getQuestionById(id);
        return ResponseEntity.ok(response);
    }

 
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {
        List<QuestionResponseDto> questions = qaService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

   
    @GetMapping("/questions/classroom/{classroomId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByClassroomId(@PathVariable String classroomId) {
        List<QuestionResponseDto> questions = qaService.getQuestionsByClassroomId(classroomId);
        return ResponseEntity.ok(questions);
    }

  
    @GetMapping("/questions/user/{userId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByUserId(@PathVariable Long userId) {
        List<QuestionResponseDto> questions = qaService.getQuestionsByUserId(userId);
        return ResponseEntity.ok(questions);
    }

 
    @GetMapping("/questions/unresolved")
    public ResponseEntity<List<QuestionResponseDto>> getUnresolvedQuestions() {
        List<QuestionResponseDto> questions = qaService.getUnresolvedQuestions();
        return ResponseEntity.ok(questions);
    }


    @GetMapping("/questions/classroom/{classroomId}/unresolved")
    public ResponseEntity<List<QuestionResponseDto>> getUnresolvedQuestionsByClassroomId(@PathVariable String classroomId) {
        List<QuestionResponseDto> questions = qaService.getUnresolvedQuestionsByClassroomId(classroomId);
        return ResponseEntity.ok(questions);
    }


    @GetMapping("/questions/search")
    public ResponseEntity<List<QuestionResponseDto>> searchQuestions(@RequestParam String keyword) {
        List<QuestionResponseDto> questions = qaService.searchQuestions(keyword);
        return ResponseEntity.ok(questions);
    }


    @PutMapping("/questions/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(
            @PathVariable String id,
            @RequestBody QuestionUpdateDto updateDto) {
        QuestionResponseDto response = qaService.updateQuestion(id, updateDto);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/questions/{questionId}/resolve/{answerId}")
    public ResponseEntity<QuestionResponseDto> markAsResolved(
            @PathVariable String questionId,
            @PathVariable String answerId) {
        QuestionResponseDto response = qaService.markAsResolved(questionId, answerId);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/questions/{questionId}/unresolve")
    public ResponseEntity<QuestionResponseDto> markAsUnresolved(@PathVariable String questionId) {
        QuestionResponseDto response = qaService.markAsUnresolved(questionId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
        qaService.deleteQuestion(id);
        return ResponseEntity.ok("Question with ID " + id + " has been deleted successfully.");
    }


    @PostMapping("/answers")
    public ResponseEntity<AnswerResponseDto> createAnswer(@Valid @RequestBody AnswerRequestDto requestDto) {
        AnswerResponseDto response = qaService.createAnswer(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/answers/{id}")
    public ResponseEntity<AnswerResponseDto> getAnswerById(@PathVariable String id) {
        AnswerResponseDto response = qaService.getAnswerById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/answers/question/{questionId}")
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByQuestionId(@PathVariable String questionId) {
        List<AnswerResponseDto> answers = qaService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

 
    @GetMapping("/answers/user/{userId}")
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByUserId(@PathVariable Long userId) {
        List<AnswerResponseDto> answers = qaService.getAnswersByUserId(userId);
        return ResponseEntity.ok(answers);
    }

    
    @PutMapping("/answers/{id}")
    public ResponseEntity<AnswerResponseDto> updateAnswer(
            @PathVariable String id,
            @RequestBody AnswerUpdateDto updateDto) {
        AnswerResponseDto response = qaService.updateAnswer(id, updateDto);
        return ResponseEntity.ok(response);
    }

  
    @PatchMapping("/answers/{id}/accept")
    public ResponseEntity<AnswerResponseDto> markAsAccepted(@PathVariable String id) {
        AnswerResponseDto response = qaService.markAsAccepted(id);
        return ResponseEntity.ok(response);
    }

    
    @PatchMapping("/answers/{id}/unaccept")
    public ResponseEntity<AnswerResponseDto> unmarkAsAccepted(@PathVariable String id) {
        AnswerResponseDto response = qaService.unmarkAsAccepted(id);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/answers/{id}/upvote")
    public ResponseEntity<AnswerResponseDto> upvoteAnswer(@PathVariable String id) {
        AnswerResponseDto response = qaService.upvoteAnswer(id);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/answers/{id}/remove-upvote")
    public ResponseEntity<AnswerResponseDto> removeUpvote(@PathVariable String id) {
        AnswerResponseDto response = qaService.removeUpvote(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/answers/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable String id) {
        qaService.deleteAnswer(id);
        return ResponseEntity.ok("Answer with ID " + id + " has been deleted successfully.");
    }
}