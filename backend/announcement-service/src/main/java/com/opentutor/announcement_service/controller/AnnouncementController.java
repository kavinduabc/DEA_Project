package com.opentutor.announcement_service.controller;

import com.opentutor.announcement_service.dto.AnnouncementRequestDTO;
import com.opentutor.announcement_service.dto.AnnouncementResponseDTO;
import com.opentutor.announcement_service.service.AnnouncementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    //Post Announcement
    @PostMapping
    public ResponseEntity<AnnouncementResponseDTO> createAnnouncement(
            @Valid @RequestBody AnnouncementRequestDTO requestDTO) {

        AnnouncementResponseDTO created = announcementService.createAnnouncement(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //Get Classroom Announcements
    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getClassroomAnnouncements(
            @PathVariable Long classroomId) {

        List<AnnouncementResponseDTO> list = announcementService.getByClassroomId(classroomId);
        return ResponseEntity.ok(list);
    }

    //Get Announcement Details
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> getAnnouncementById(@PathVariable Long id) {
        return announcementService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Announcement
    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> updateAnnouncement(
            @PathVariable Long id,
            @Valid @RequestBody AnnouncementRequestDTO requestDTO) {

        try {
            AnnouncementResponseDTO updated =
                    announcementService.updateAnnouncement(id, requestDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete Announcement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        try {
            announcementService.deleteAnnouncement(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //View Shared Announcements
    @GetMapping("/shared/{userId}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getSharedAnnouncements(
            @PathVariable Long userId) {

        List<AnnouncementResponseDTO> list = announcementService.getSharedAnnouncements(userId);
        return ResponseEntity.ok(list);
    }
}
