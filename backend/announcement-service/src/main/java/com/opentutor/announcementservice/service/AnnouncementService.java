package com.opentutor.announcementservice.service;

import com.opentutor.announcementservice.dto.AnnouncementRequestDTO;
import com.opentutor.announcementservice.dto.AnnouncementResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnnouncementService {
    AnnouncementResponseDTO createAnnouncement(AnnouncementRequestDTO requestDTO);
    List<AnnouncementResponseDTO> getByClassroomId(UUID classroomId);
    Optional<AnnouncementResponseDTO> getById(UUID id);
    AnnouncementResponseDTO updateAnnouncement(UUID id, AnnouncementRequestDTO requestDTO);
    void deleteAnnouncement(UUID id);
    AnnouncementResponseDTO generateShareToken(UUID id);
    AnnouncementResponseDTO getByShareToken(String token);
}
