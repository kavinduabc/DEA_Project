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

package com.opentuter.enrollmentservice.dto;

import com.opentuter.enrollmentservice.util.EnrollmentUtil;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class EnrollmentRequestDTO {

    @NotNull(message = EnrollmentUtil.VALIDATION_STUDENT_ID_REQUIRED)
    private UUID studentId;

    @NotNull(message = EnrollmentUtil.VALIDATION_CLASSROOM_ID_REQUIRED)
    private int classroomId;

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }
}
