package com.opentuter.profileservice.mapper;

import com.opentuter.profileservice.dto.ProfileDto;
import com.opentuter.profileservice.model.Profile;

public class ProfileMapper {

    //implement method for convert profileDto into profile Entity
    public static Profile toProfileModel(ProfileDto profileDto)
    {
        if(profileDto == null)
        {
            return null;
        }
        //*
        // form data mapping into user model using
        // userRegistrtionDTO*/
        Profile profile = new Profile();

        profile.setFullName(profileDto.getFullName());
        profile.setBio(profileDto.getBio());
        profile.setImageUrl(profileDto.getImageUrl());
        profile.setSocialMediaUrl(profileDto.getSocailMediaUrl());


        return profile;
    }

    //implement method to convert profile entity into  profileDto
    public static ProfileDto toProfileDto(Profile profile)
    {
        if(profile == null)
        {
            return null;
        }
        //collect data from using profile  schema and return to ProfileDto

        return  new ProfileDto(
                profile.getFullName(),
                profile.getBio(),
                profile.getImageUrl(),
                profile.getSocialMediaUrl()
        );

    }
}
