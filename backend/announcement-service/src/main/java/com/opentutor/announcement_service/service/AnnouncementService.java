package com.opentutor.announcement_service.service;

import com.opentutor.announcement_service.dto.AnnouncementRequestDTO;
import com.opentutor.announcement_service.dto.AnnouncementResponseDTO;
import com.opentutor.announcement_service.mapper.AnnouncementMapper;
import com.opentutor.announcement_service.model.Announcement;
import com.opentutor.announcement_service.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    public AnnouncementService(AnnouncementRepository announcementRepository,
                              AnnouncementMapper announcementMapper) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }

    // Create Announcement
    public AnnouncementResponseDTO createAnnouncement(AnnouncementRequestDTO requestDTO) {
        Announcement entity = announcementMapper.toEntity(requestDTO);

        // Ensure createdAt is set even when using mapper
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now());
        }

        Announcement saved = announcementRepository.save(entity);
        return announcementMapper.toResponseDTO(saved);
    }

    // Get Announcements by Classroom ID
    @Transactional(readOnly = true)
    public List<AnnouncementResponseDTO> getByClassroomId(Long classroomId) {
        List<Announcement> list = announcementRepository.findAllByClassroomId(classroomId);
        return announcementMapper.toResponseDTOList(list);
    }

    // Get Announcement by ID
    @Transactional(readOnly = true)
    public Optional<AnnouncementResponseDTO> getById(Long id) {
        return announcementRepository.findById(id)
                .map(announcementMapper::toResponseDTO);
    }

    // Update Announcement
    public AnnouncementResponseDTO updateAnnouncement(Long id, AnnouncementRequestDTO requestDTO) {
        Announcement existing = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with id: " + id));

        // Update allowed fields
        announcementMapper.updateEntityFromDTO(existing, requestDTO);

        // Optional: if you want to allow updating createdBy too, uncomment:
        // existing.setCreatedBy(requestDTO.getCreatedBy());

        Announcement updated = announcementRepository.save(existing);
        return announcementMapper.toResponseDTO(updated);
    }

    // Delete Announcement
    public void deleteAnnouncement(Long id) {
        if (!announcementRepository.existsById(id)) {
            throw new RuntimeException("Announcement not found with id: " + id);
        }
        announcementRepository.deleteById(id);
    }

    /**
     * View Shared Announcements
     *
     * NOTE: Your current model has no "shared" concept (no shared table, no recipients, no join table).
     * So this is a placeholder that returns an empty list.
     *
     * If you explain how "shared" is stored (e.g., shared_with_user_id column or announcement_shares table),
     * I can provide the exact repository query and full working implementation.
     */
    @Transactional(readOnly = true)
    public List<AnnouncementResponseDTO> getSharedAnnouncements(Long userId) {
        return Collections.emptyList();
    }
}
