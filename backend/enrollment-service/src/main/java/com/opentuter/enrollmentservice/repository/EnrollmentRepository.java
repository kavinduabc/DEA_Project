package com.opentuter.enrollmentservice.repository;

import com.opentuter.enrollmentservice.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    boolean existsByStudentIdAndClassroomId(UUID studentId, UUID classroomId);

    List<Enrollment> findByStudentId(UUID studentId);

    List<Enrollment> findByClassroomId(UUID classroomId);
}
