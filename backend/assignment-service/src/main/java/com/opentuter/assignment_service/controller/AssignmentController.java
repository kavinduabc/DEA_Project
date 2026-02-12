package com.opentuter.assignment_service.controller;

import com.opentuter.assignment_service.dto.AssignmentRequestDTO;
import com.opentuter.assignment_service.dto.AssignmentResponseDTO;
import com.opentuter.assignment_service.service.AssignmentService;
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
}
