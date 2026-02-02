package com.opentuter.userservice.mapper;


//*
// Mapper class act as trnslator or converter
//  imagine you have a Entity and DTO .the mapper clas is the
//  person who sits in the middle and rewrites the document for you
//  -- Important --
//   1.Centralization(one place rule)
//   2.Two-Way Traffic
//    Entity -> DTO
//    DTO -> Entity
//     */

import com.opentuter.userservice.dto.UserRejistrationDto;
import com.opentuter.userservice.dto.UserResponseDto;
import com.opentuter.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //convert entity into response DTO
    public UserResponseDto toReseponseDTO(User user)
    {
        if(user == null)
        {
            return null;
        }
        //collect data from using user schema and return to UserResponseDto
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt()
        );
    }

    //conver request dto into Entity
    public static User toUserModel(UserRejistrationDto registrationDTO)
    {
        if(registrationDTO == null)
        {
            return  null;
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
