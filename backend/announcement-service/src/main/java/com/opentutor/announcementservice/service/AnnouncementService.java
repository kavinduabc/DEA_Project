package com.opentutor.announcementservice.service;

import com.opentutor.announcementservice.dto.AnnouncementRequestDTO;
import com.opentutor.announcementservice.dto.AnnouncementResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for Announcement operations.
 * Defines the contract for all announcement-related business logic.
 */
public interface AnnouncementService {

    /**
     * Creates a new announcement.
     *
     * @param requestDTO the data for the new announcement
     * @return the created announcement as a response DTO
     */
    AnnouncementResponseDTO createAnnouncement(AnnouncementRequestDTO requestDTO);

    /**
     * Retrieves all announcements belonging to a specific classroom.
     *
     * @param classroomId the UUID of the classroom
     * @return a list of announcements for the given classroom
     */
    List<AnnouncementResponseDTO> getByClassroomId(UUID classroomId);

    /**
     * Retrieves a single announcement by its ID.
     *
     * @param id the UUID of the announcement
     * @return an Optional containing the announcement if found, or empty if not
     */
    Optional<AnnouncementResponseDTO> getById(UUID id);

    /**
     * Updates an existing announcement.
     *
     * @param id         the UUID of the announcement to update
     * @param requestDTO the updated data
     * @return the updated announcement as a response DTO
     */
    AnnouncementResponseDTO updateAnnouncement(UUID id, AnnouncementRequestDTO requestDTO);

    /**
     * Deletes an announcement by its ID.
     *
     * @param id the UUID of the announcement to delete
     */
    void deleteAnnouncement(UUID id);

    /**
     * Generates a unique public share token for an announcement.
     *
     * @param id the UUID of the announcement
     * @return the updated announcement with the new share token
     */
    AnnouncementResponseDTO generateShareToken(UUID id);

    /**
     * Retrieves a publicly shared announcement by its share token.
     *
     * @param token the unique share token
     * @return the announcement associated with the given token
     */
    AnnouncementResponseDTO getByShareToken(String token);
}
