package com.opentuter.profileservice.mapper;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //convert entity into response DTO
    public UserProfileResponseDto toReseponseDTO(User user)
    {
        if(user == null)
        {
            return null;
        }
        //collect data from using user schema and return to UserResponseDto
        return new UserProfileResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
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

        return user;
    }
}