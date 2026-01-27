package com.opentutor.profileservice.mapper;

import com.opentutor.profileservice.dto.ProfileRequestDTO;
import com.opentutor.profileservice.dto.ProfileResponseDTO;
import com.opentutor.profileservice.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {
    // Convert ProfileRequestDTO to ProfileEntity
    public Profile toEntity(ProfileRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Profile entity = new Profile();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());

        return entity;
    }

    // Convert ProfileEntity to ProfileResponseDTO
    public ProfileResponseDTO toResponseDTO(Profile entity) {
        if (entity == null) {
            return null;
        }

        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());

        return dto;
    }

    // Convert List of ProfileEntity to List of ProfileResponseDTO
    public List<ProfileResponseDTO> toResponseDTOList(List<Profile> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update entity with DTO data
    public void updateEntityFromDTO(Profile entity, ProfileRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
    }
}
