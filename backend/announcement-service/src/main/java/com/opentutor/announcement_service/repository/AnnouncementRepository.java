package com.opentutor.announcement_service.repository;

import com.opentutor.announcement_service.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, UUID> {

    List<Announcement> findAllByClassroomId(UUID classroomId);

    Optional<Announcement> findByShareToken(String shareToken);
}
