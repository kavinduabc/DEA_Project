package com.opentutor.announcementservice.service.impl;

import com.opentutor.announcementservice.client.ClassroomSeviceClient;
import com.opentutor.announcementservice.client.UserServiceClient;
import com.opentutor.announcementservice.dto.AnnouncementRequestDTO;
import com.opentutor.announcementservice.dto.AnnouncementResponseDTO;
import com.opentutor.announcementservice.exception.ResourceNotFoundException;
import com.opentutor.announcementservice.mapper.AnnouncementMapper;
import com.opentutor.announcementservice.model.Announcement;
import com.opentutor.announcementservice.repository.AnnouncementRepository;
import com.opentutor.announcementservice.service.AnnouncementService;
import com.opentutor.announcementservice.util.AnnouncementConstants;
import com.opentutor.announcementservice.util.AnnouncementHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository repo;
    private final AnnouncementMapper mapper;
    private final UserServiceClient userServiceClient;
    private final ClassroomSeviceClient classroomServiceClient;

    public AnnouncementServiceImpl(AnnouncementRepository repo, AnnouncementMapper mapper,
                                   UserServiceClient userServiceClient,
                                   ClassroomSeviceClient classroomServiceClient) {
        this.repo = repo;
        this.mapper = mapper;
        this.userServiceClient = userServiceClient;
        this.classroomServiceClient = classroomServiceClient;
    }


    @Override
    public AnnouncementResponseDTO createAnnouncement(AnnouncementRequestDTO requestDTO) {
        // Verify that the teacher (user) exists in the profile-service
        if (!userServiceClient.userExists(requestDTO.getTeacherId())) {
            throw new ResourceNotFoundException(
                    "User",
                    "id",
                    requestDTO.getTeacherId()
            );
        }

        // Verify that the classroom exists in the classroom-service
        if (!classroomServiceClient.classroomExists(requestDTO.getClassroomId())) {
            throw new ResourceNotFoundException(
                    "Classroom",
                    "id",
                    requestDTO.getClassroomId()
            );
        }

        Announcement entity = mapper.toEntity(requestDTO);
        Announcement saved = repo.save(entity);
        return mapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnnouncementResponseDTO> getByClassroomId(UUID classroomId) {
        return mapper.toResponseDTOList(repo.findAllByClassroomId(classroomId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnnouncementResponseDTO> getById(UUID id) {
        return repo.findById(id).map(mapper::toResponseDTO);
    }


    @Override
    public AnnouncementResponseDTO updateAnnouncement(UUID id, AnnouncementRequestDTO requestDTO) {
        Announcement existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AnnouncementConstants.RESOURCE_ANNOUNCEMENT, AnnouncementConstants.FIELD_ID, id));

        mapper.updateEntityFromDTO(existing, requestDTO);
        Announcement updated = repo.save(existing);
        return mapper.toResponseDTO(updated);
    }


    @Override
    public void deleteAnnouncement(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException(
                    AnnouncementConstants.RESOURCE_ANNOUNCEMENT, AnnouncementConstants.FIELD_ID, id);
        }
        repo.deleteById(id);
    }


    @Override
    public AnnouncementResponseDTO generateShareToken(UUID id) {
        Announcement announcement = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AnnouncementConstants.RESOURCE_ANNOUNCEMENT, AnnouncementConstants.FIELD_ID, id));

        announcement.setShareToken(AnnouncementHelper.generateShareToken());
        Announcement saved = repo.save(announcement);
        return mapper.toResponseDTO(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public AnnouncementResponseDTO getByShareToken(String token) {
        Announcement announcement = repo.findByShareToken(token)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AnnouncementConstants.RESOURCE_ANNOUNCEMENT, AnnouncementConstants.FIELD_SHARE_TOKEN, token));
        return mapper.toResponseDTO(announcement);
    }
}
