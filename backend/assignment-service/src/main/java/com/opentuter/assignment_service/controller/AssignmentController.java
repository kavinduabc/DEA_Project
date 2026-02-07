package com.opentuter.assignment_service.controller;

import com.opentuter.assignment_service.dto.AssignmentRequestDTO;
import com.opentuter.assignment_service.dto.AssignmentResponseDTO;
import com.opentuter.assignment_service.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponseDTO> createAssignment(@RequestBody AssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.createAssignment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<AssignmentResponseDTO>> getAssignmentsByClassroom(@PathVariable Long classroomId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByClassroom(classroomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> updateAssignment(@PathVariable Long id,
            @RequestBody AssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
