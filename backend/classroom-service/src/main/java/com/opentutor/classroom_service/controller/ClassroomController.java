package com.opentutor.classroom_service.controller;

import com.opentutor.classroom_service.dto.ClassroomRequestDTO;
import com.opentutor.classroom_service.dto.ClassroomResponseDTO;
import com.opentutor.classroom_service.service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<ClassroomResponseDTO> createClassroom(@Valid @RequestBody ClassroomRequestDTO classroomRequestDTO){
        ClassroomResponseDTO createdClassroom = classroomService.createClassroom(classroomRequestDTO);
        return new ResponseEntity<>(createdClassroom, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassroomResponseDTO>> getAllClassrooms() {
        List<ClassroomResponseDTO> classrooms = classroomService.getAllClassrooms();
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponseDTO> getClassroomById(
            @PathVariable Long id) {

        return classroomService.getClassroomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/verify/{inviteCode}")
    public ResponseEntity<List<ClassroomResponseDTO>> getClassroomsByInviteCode(
            @PathVariable String inviteCode) {

        List<ClassroomResponseDTO> classrooms =
                classroomService.getClassroomsByInviteCode(inviteCode);

        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ClassroomResponseDTO>> getClassroomsByTeacherId(
            @PathVariable UUID teacherId) {

        List<ClassroomResponseDTO> classrooms =
                classroomService.getClassroomsByTeacherId(teacherId);

        return ResponseEntity.ok(classrooms);
    }

    // Update classroom
    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResponseDTO> updateClassroom(
            @PathVariable Long id,
            @Valid @RequestBody ClassroomRequestDTO classroomRequestDTO) {

        try {
            ClassroomResponseDTO updatedClassroom =
                    classroomService.updateClassroom(id, classroomRequestDTO);

            return ResponseEntity.ok(updatedClassroom);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete classroom
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {

        try {
            classroomService.deleteClassroom(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
