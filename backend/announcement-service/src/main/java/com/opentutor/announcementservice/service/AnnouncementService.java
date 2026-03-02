package com.opentutor.announcementservice.service;

import com.opentutor.announcementservice.dto.AnnouncementRequestDTO;
import com.opentutor.announcementservice.dto.AnnouncementResponseDTO;
import com.opentutor.announcementservice.mapper.AnnouncementMapper;
import com.opentutor.announcementservice.model.Announcement;
import com.opentutor.announcementservice.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AnnouncementService {

    private final AnnouncementRepository repo;
    private final AnnouncementMapper mapper;

    public AnnouncementService(AnnouncementRepository repo, AnnouncementMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public AnnouncementResponseDTO createAnnouncement(AnnouncementRequestDTO requestDTO) {
        Announcement entity = mapper.toEntity(requestDTO);
        Announcement saved = repo.save(entity);
        return mapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<AnnouncementResponseDTO> getByClassroomId(UUID classroomId) {
        return mapper.toResponseDTOList(repo.findAllByClassroomId(classroomId));
    }

    @Transactional(readOnly = true)
    public Optional<AnnouncementResponseDTO> getById(UUID id) {
        return repo.findById(id).map(mapper::toResponseDTO);
    }

    public AnnouncementResponseDTO updateAnnouncement(UUID id, AnnouncementRequestDTO requestDTO) {
        Announcement existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found: " + id));

        mapper.updateEntityFromDTO(existing, requestDTO);
        Announcement updated = repo.save(existing);
        return mapper.toResponseDTO(updated);
    }

    public void deleteAnnouncement(UUID id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Announcement not found: " + id);
        }
        repo.deleteById(id);
    }

    public AnnouncementResponseDTO generateShareToken(UUID id) {
        Announcement a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found: " + id));

        a.setShareToken(UUID.randomUUID().toString());
        Announcement saved = repo.save(a);
        return mapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public AnnouncementResponseDTO getByShareToken(String token) {
        Announcement a = repo.findByShareToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid share token"));
        return mapper.toResponseDTO(a);
    }
}
