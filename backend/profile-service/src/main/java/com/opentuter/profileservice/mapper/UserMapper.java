package com.opentuter.profileservice.mapper;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.model.Profile;
import com.opentuter.profileservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //convert entity into response DTO
    public UserProfileResponseDto toReseponseDTO(User user)
    {
        UserProfileResponseDto response = new UserProfileResponseDto();

        response.uuid = user.getUuid();
        response.email = user.getEmail();
        response.role = user.getRole();

        Profile profile = user.getProfile();
        if(profile != null){
            response.fullName = profile.getFullName();;
            response.bio = profile.getBio();
            response.imageUrl = profile.getImageUrl();
            response.socialMediaUrls = profile.getSocialMediaUrls();
        }

        return response;
    }

    //conver request dto into Entity
    public static User toUserModel(UserProfileRequestDto registrationDTO)
    {
        if(registrationDTO == null)
        {
            return null;
        }
        //*
        // form data mapping into user model using
        // userRegistrtionDTO*/
        User user = new User();

        user.setEmail(registrationDTO.getEmail());
        user.setRole(registrationDTO.getRole());
        user.setPassword(registrationDTO.getPassword());

        Profile profile = new Profile();

        profile.setFullName(registrationDTO.getFullName());
        profile.setBio(registrationDTO.getBio());
        profile.setImageUrl(registrationDTO.getImageUrl());
        profile.setSocialMediaUrls(registrationDTO.getSocialMediaUrls());

        user.setProfile(profile);


        return user;
    }

    //update user and profile
    public static void updateUserProfile(User user, UserProfileRequestDto request)
    {
        user.setEmail(request.email);
        user.setRole(request.role);

        Profile profile = user.getProfile();
        if(profile != null)
        {
            profile.setFullName(request.fullName);
            profile.setBio(request.bio);
            profile.setImageUrl(request.imageUrl);
            profile.setSocialMediaUrls(request.socialMediaUrls);
        }
    }
}