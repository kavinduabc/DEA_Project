package com.opentuter.enrollmentservice.model;

import com.opentuter.enrollmentservice.util.EnrollmentUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = EnrollmentUtil.TABLE_ENROLLMENTS)
public class Enrollment {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = EnrollmentUtil.COLUMN_ID, updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = EnrollmentUtil.COLUMN_STUDENT_ID, nullable = false)
    private UUID studentId;

    @NotNull
    @Column(name = EnrollmentUtil.COLUMN_CLASSROOM_ID, nullable = false)
    private UUID classroomId;

    @NotNull
    @Column(name = EnrollmentUtil.COLUMN_ENROLLED_AT, nullable = false)
    private LocalDateTime enrolledAt;

    public Enrollment() {
        this.enrolledAt = LocalDateTime.now();
    }

    public Enrollment(UUID studentId, UUID classroomId) {
        this.studentId = studentId;
        this.classroomId = classroomId;
        this.enrolledAt = LocalDateTime.now();
    }

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

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", classroomId=" + classroomId +
                ", enrolledAt=" + enrolledAt +
                '}';
    }
}

