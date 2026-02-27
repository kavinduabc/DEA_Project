package com.opentutor.classroomservice.repository;

import com.opentutor.classroomservice.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByInviteCode(String inviteCode);
    List<Classroom> findByTeacherId(UUID teacherId);
}
