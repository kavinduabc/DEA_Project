package com.opentuter.userservice.service;

import com.opentuter.userservice.dto.ProfileDto;
import com.opentuter.userservice.mapper.ProfileMapper;
import com.opentuter.userservice.model.Profile;
import com.opentuter.userservice.model.User;
import com.opentuter.userservice.repository.ProfileRepository;
import com.opentuter.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
@Service
public class ProfileService {

    private final ProfileRepository  profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    //*
    // implement function  create profile */
    public ProfileDto createProfile(Long id, ProfileDto profileDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = ProfileMapper.toProfileModel(profileDto);

        profile.setUser(user);


        Profile saved = profileRepository.save(profile);
        return ProfileMapper.toProfileDto(saved);
    }


    //*
    // implement function for read profile*/
    public ProfileDto getProfile(Long id)
    {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile Not Found"));

        return ProfileMapper.toProfileDto(profile);
    }

    //*
    // implement function for update profile*/
    public ProfileDto updateProfile(Long id, ProfileDto profileDto)
    {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile Not Found"));

        if(profileDto == null)
        {
            return ProfileMapper.toProfileDto(profile);
        }
        if(profileDto.getFullName() != null)
        {
            profile.setFullName(profileDto.getFullName());
        }

        if(profileDto.getBio() != null)
        {
            profile.setBio(profileDto.getBio());
        }

        if(profileDto.getImageUrl() != null)
        {
            profile.setImageUrl(profileDto.getImageUrl());
        }

        if(profileDto.getSocailMediaUrl() != null)
        {
            profile.setSocialMediaUrl(profileDto.getSocailMediaUrl());
        }

        return ProfileMapper.toProfileDto(profileRepository.save(profile));
    }

    //*
    // implements method for delete profile*/
    public void deleteProfile(Long id)
    {
        profileRepository.deleteById(id);
    }

}
