package com.opentutor.profileservice.service;

import com.opentutor.profileservice.dto.ProfileRequestDTO;
import com.opentutor.profileservice.dto.ProfileResponseDTO;
import com.opentutor.profileservice.exception.DuplicateResourceException;
import com.opentutor.profileservice.exception.ResourceNotFoundException;
import com.opentutor.profileservice.mapper.ProfileMapper;
import com.opentutor.profileservice.model.Profile;
import com.opentutor.profileservice.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    // Create
    public ProfileResponseDTO createProfile(ProfileRequestDTO profileRequestDTO) {
        // Check if email already exists
        if (profileRepository.findByEmail(profileRequestDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Profile", "email", profileRequestDTO.getEmail());
        }

        Profile entity = profileMapper.toEntity(profileRequestDTO);
        Profile savedEntity = profileRepository.save(entity);
        return profileMapper.toResponseDTO(savedEntity);
    }

    // Read all
    public List<ProfileResponseDTO> getAllProfiles() {
        List<Profile> entities = profileRepository.findAll();
        return profileMapper.toResponseDTOList(entities);
    }

    // Read by ID
    public Optional<ProfileResponseDTO> getProfileById(Long id) {
        return profileRepository.findById(id)
                .map(profileMapper::toResponseDTO);
    }

    // Read by email
    public Optional<ProfileResponseDTO> getProfileByEmail(String email) {
        return profileRepository.findByEmail(email)
                .map(profileMapper::toResponseDTO);
    }

    // Update
    public ProfileResponseDTO updateProfile(Long id, ProfileRequestDTO profileRequestDTO) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));

        // Check if email is being changed to an existing email
        if (!profile.getEmail().equals(profileRequestDTO.getEmail())) {
            if (profileRepository.findByEmail(profileRequestDTO.getEmail()).isPresent()) {
                throw new DuplicateResourceException("Profile", "email", profileRequestDTO.getEmail());
            }
        }

        profileMapper.updateEntityFromDTO(profile, profileRequestDTO);
        Profile updatedEntity = profileRepository.save(profile);

        return profileMapper.toResponseDTO(updatedEntity);
    }

    // Delete
    public void deleteProfile(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
        profileRepository.delete(profile);
    }
}
