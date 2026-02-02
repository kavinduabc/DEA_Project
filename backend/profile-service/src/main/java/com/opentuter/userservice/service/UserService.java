package com.opentuter.userservice.service;

import com.opentuter.userservice.dto.UserRejistrationDto;
import com.opentuter.userservice.dto.UserResponseDto;
import com.opentuter.userservice.mapper.UserMapper;
import com.opentuter.userservice.model.User;
import com.opentuter.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    //create constructor Injection for dependencies
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    //implement method for user rejistration
    public UserResponseDto addUser(@RequestBody UserRejistrationDto registrationDto)
    {
        //*
        //1. convert the sign up page entered data into a database model
        //2. save the user model into the database
        //   using userRepository.save() returns the user model that was actually saved
        //3. convert the saved user model back to a response DTO   */

        User user = userMapper.toUserModel(registrationDto);


        user.setEmail(registrationDto.getEmail());
        user.setPassword(encoder.encode(registrationDto.getPassword()));

        User saveUser = userRepository.save( user);
        return userMapper.toReseponseDTO(saveUser);

    }

    //implement the method for view user

}
