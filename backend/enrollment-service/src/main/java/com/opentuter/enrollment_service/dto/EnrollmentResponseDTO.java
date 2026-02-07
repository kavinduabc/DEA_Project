//package com.opentuter.enrollment_service.dto;
//
//import java.util.UUID;
//
//public class EnrollmentResponseDTO {
//
//    private UUID id;
//    private UUID studentId;
//    private UUID classroomId;
//
//    // Getters and Setters
//    public UUID getId() { return id; }
//    public void setId(UUID id) { this.id = id; }
//
//    public UUID getStudentId() { return studentId; }
//    public void setStudentId(UUID studentId) { this.studentId = studentId; }
//
//    public UUID getClassroomId() { return classroomId; }
//    public void setClassroomId(UUID classroomId) { this.classroomId = classroomId; }
//}

package com.opentuter.enrollment_service.dto;

import java.util.UUID;

public class EnrollmentResponseDTO {

    private UUID id;
    private UUID studentId;
    private UUID classroomId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }
}
