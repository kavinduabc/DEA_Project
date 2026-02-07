//package com.opentuter.enrollment_service.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//
//import java.util.UUID;
//
//@Entity
//@Table(name = "enrollments")
//public class Enrollment {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @Column(nullable = false)
//    @NotNull
//    private UUID studentId;
//
//    @Column(nullable = false)
//    @NotNull
//    private UUID classroomId;
//
//    // Constructors
//    public Enrollment() {}
//
//    public Enrollment(UUID studentId, UUID classroomId) {
//        this.studentId = studentId;
//        this.classroomId = classroomId;
//    }
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

package com.opentuter.enrollment_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_student_classroom",
                columnNames = {"student_id", "classroom_id"}
        )
)
public class Enrollment {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @NotNull
    @Column(name = "classroom_id", nullable = false)
    private UUID classroomId;

    // Constructors
    public Enrollment() {}

    public Enrollment(UUID studentId, UUID classroomId) {
        this.studentId = studentId;
        this.classroomId = classroomId;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }
}



