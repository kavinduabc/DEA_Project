package com.opentuter.assignmentservice.controller;

import com.opentuter.assignmentservice.dto.AssignmentRequestDTO;
import com.opentuter.assignmentservice.dto.AssignmentResponseDTO;
import com.opentuter.assignmentservice.dto.SubmissionRequestDTO;
import com.opentuter.assignmentservice.dto.SubmissionResponseDTO;
import com.opentuter.assignmentservice.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    // --- Assignment Endpoints ---

    @GetMapping("/health")
    public String health() {
        return "Assignment Service is UP";
    }

    @PostMapping
    public ResponseEntity<AssignmentResponseDTO> createAssignment(@RequestBody AssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.createAssignment(request));
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponseDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> getAssignmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<AssignmentResponseDTO>> getAssignmentsByClassroom(@PathVariable UUID classroomId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByClassroom(classroomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> updateAssignment(@PathVariable UUID id,
            @RequestBody AssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable UUID id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

    // --- Submission Endpoints ---

    @PostMapping("/submissions")
    public ResponseEntity<SubmissionResponseDTO> submitAssignment(@RequestBody SubmissionRequestDTO request) {
        return ResponseEntity.ok(assignmentService.submitAssignment(request));
    }

    @PutMapping("/submissions/{id}/grade")
    public ResponseEntity<SubmissionResponseDTO> gradeSubmission(@PathVariable UUID id,
            @RequestParam Double grade,
            @RequestParam String feedback) {
        return ResponseEntity.ok(assignmentService.gradeSubmission(id, grade, feedback));
    }

    @GetMapping("/submissions/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissionsByAssignment(@PathVariable UUID assignmentId) {
        return ResponseEntity.ok(assignmentService.getSubmissionsByAssignment(assignmentId));
    }

    @GetMapping("/submissions/student/{studentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissionsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(assignmentService.getSubmissionsByStudent(studentId));
    }
}
 