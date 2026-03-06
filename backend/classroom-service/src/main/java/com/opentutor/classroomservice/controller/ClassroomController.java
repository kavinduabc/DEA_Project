package com.opentutor.classroomservice.controller;

import com.opentutor.classroomservice.dto.ClassroomRequestDTO;
import com.opentutor.classroomservice.dto.ClassroomResponseDTO;
import com.opentutor.classroomservice.service.ClassroomService;
import com.opentutor.classroomservice.util.ClassroomUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ClassroomUtil.API_BASE_PATH)
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<ClassroomResponseDTO> createClassroom(
            @Valid @RequestBody ClassroomRequestDTO classroomRequestDTO){
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

        ClassroomResponseDTO classroom = classroomService.getClassroomById(id);
        return ResponseEntity.ok(classroom);
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

        ClassroomResponseDTO updatedClassroom =
                classroomService.updateClassroom(id, classroomRequestDTO);

            return ResponseEntity.ok(updatedClassroom);
        }

    // Delete classroom
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {

        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}
