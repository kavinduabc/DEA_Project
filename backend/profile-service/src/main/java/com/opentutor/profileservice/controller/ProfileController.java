package com.opentutor.profileservice.controller;

import com.opentutor.profileservice.dto.ProfileRequestDTO;
import com.opentutor.profileservice.dto.ProfileResponseDTO;
import com.opentutor.profileservice.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Create a new profile
    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO createdProfile = profileService.createProfile(profileRequestDTO);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    // Get all profiles
    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        List<ProfileResponseDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    // Get profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get profile by email
    @GetMapping("/email/{email}")
    public ResponseEntity<ProfileResponseDTO> getProfileByEmail(@PathVariable String email) {
        return profileService.getProfileByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update profile
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        try {
            ProfileResponseDTO updatedProfile = profileService.updateProfile(id, profileRequestDTO);
            return ResponseEntity.ok(updatedProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        try {
            profileService.deleteProfile(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
