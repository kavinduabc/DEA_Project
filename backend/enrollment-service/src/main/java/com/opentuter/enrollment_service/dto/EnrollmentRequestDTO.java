//package com.opentuter.enrollment_service.dto;
//
//import java.util.UUID;
//
//public class EnrollmentRequestDTO {
//
//    private UUID studentId;
//    private UUID classroomId;
//
//    // Getters and Setters
//    public UUID getStudentId() { return studentId; }
//    public void setStudentId(UUID studentId) { this.studentId = studentId; }
//
//    public UUID getClassroomId() { return classroomId; }
//    public void setClassroomId(UUID classroomId) { this.classroomId = classroomId; }
//}

package com.opentuter.enrollment_service.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class EnrollmentRequestDTO {

    @NotNull(message = "studentId is required")
    private UUID studentId;

    @NotNull(message = "classroomId is required")
    private UUID classroomId;

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
