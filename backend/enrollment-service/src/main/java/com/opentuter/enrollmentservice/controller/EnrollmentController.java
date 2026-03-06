//package com.opentuter.enrollment_service.controller;
//
//import com.opentuter.enrollment_service.dto.EnrollmentRequestDTO;
//import com.opentuter.enrollment_service.dto.EnrollmentResponseDTO;
//import com.opentuter.enrollment_service.service.EnrollmentService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/enrollments")
//public class EnrollmentController {
//
//    private final EnrollmentService enrollmentService;
//
//    public EnrollmentController(EnrollmentService enrollmentService) {
//        this.enrollmentService = enrollmentService;
//    }
//
//    // Enroll a student in a classroom
//    @PostMapping
//    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(
//            @Valid @RequestBody EnrollmentRequestDTO requestDTO) {
//
//        EnrollmentResponseDTO createdEnrollment =
//                enrollmentService.createEnrollment(requestDTO);
//
//        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
//    }
//
//    // Get all enrollments
//    @GetMapping
//    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
//        List<EnrollmentResponseDTO> enrollments =
//                enrollmentService.getAllEnrollments();
//        return ResponseEntity.ok(enrollments);
//    }
//
//    // Get enrollment by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(
//            @PathVariable UUID id) {
//
//        return enrollmentService.getEnrollmentById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get enrollments by student ID
//    @GetMapping("/student/{studentId}")
//    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByStudentId(
//            @PathVariable UUID studentId) {
//
//        List<EnrollmentResponseDTO> enrollments =
//                enrollmentService.getEnrollmentsByStudentId(studentId);
//
//        return ResponseEntity.ok(enrollments);
//    }
//
//    // Get enrollments by classroom ID
//    @GetMapping("/classroom/{classroomId}")
//    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByClassroomId(
//            @PathVariable UUID classroomId) {
//
//        List<EnrollmentResponseDTO> enrollments =
//                enrollmentService.getEnrollmentsByClassroomId(classroomId);
//
//        return ResponseEntity.ok(enrollments);
//    }
//
//    // Delete enrollment
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
//        try {
//            enrollmentService.deleteEnrollment(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
//
package com.opentuter.enrollmentservice.controller;

import com.opentuter.enrollmentservice.dto.EnrollmentRequestDTO;
import com.opentuter.enrollmentservice.dto.EnrollmentResponseDTO;
import com.opentuter.enrollmentservice.service.EnrollmentService;
import com.opentuter.enrollmentservice.util.EnrollmentUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(EnrollmentUtil.BASE_URL)
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(
            @Valid @RequestBody EnrollmentRequestDTO requestDTO) {

        EnrollmentResponseDTO response =
                enrollmentService.createEnrollment(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping(EnrollmentUtil.ID_ENDPOINT)
    public ResponseEntity<EnrollmentResponseDTO> getById(@PathVariable UUID id) {
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(EnrollmentUtil.STUDENT_ENDPOINT)
    public ResponseEntity<List<EnrollmentResponseDTO>> getByStudent(
            @PathVariable UUID studentId) {
        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByStudentId(studentId)
        );
    }

    @GetMapping(EnrollmentUtil.CLASSROOM_ENDPOINT)
    public ResponseEntity<List<EnrollmentResponseDTO>> getByClassroom(
            @PathVariable int classroomId) {
        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByClassroomId(classroomId)
        );
    }

    @DeleteMapping(EnrollmentUtil.ID_ENDPOINT)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
