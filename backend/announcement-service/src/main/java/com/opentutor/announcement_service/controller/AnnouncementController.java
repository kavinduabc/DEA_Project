package com.opentutor.announcement_service.controller;

import com.opentutor.announcement_service.dto.AnnouncementRequestDTO;
import com.opentutor.announcement_service.dto.AnnouncementResponseDTO;
import com.opentutor.announcement_service.service.AnnouncementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService service;

    public AnnouncementController(AnnouncementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnnouncementResponseDTO> create(@Valid @RequestBody AnnouncementRequestDTO dto) {
        return new ResponseEntity<>(service.createAnnouncement(dto), HttpStatus.CREATED);
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getByClassroom(@PathVariable UUID classroomId) {
        return ResponseEntity.ok(service.getByClassroomId(classroomId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> update(@PathVariable UUID id,
                                                         @Valid @RequestBody AnnouncementRequestDTO dto) {
        return ResponseEntity.ok(service.updateAnnouncement(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }

    // generate token
    @PostMapping("/{id}/share")
    public ResponseEntity<AnnouncementResponseDTO> share(@PathVariable UUID id) {
        return ResponseEntity.ok(service.generateShareToken(id));
    }

    // access by token
    @GetMapping("/share/{token}")
    public ResponseEntity<AnnouncementResponseDTO> getShared(@PathVariable String token) {
        return ResponseEntity.ok(service.getByShareToken(token));
    }
}
