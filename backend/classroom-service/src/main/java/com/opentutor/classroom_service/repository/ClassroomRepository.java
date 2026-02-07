package com.opentutor.classroom_service.repository;

import com.opentutor.classroom_service.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByInviteCode(String inviteCode);
    List<Classroom> findByTeacherId(UUID teacherId);
}
