package com.opentutor.announcement_service.repository;

import com.opentutor.announcement_service.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Optional<Announcement> findByClassroomId(Long classroomId);
}
