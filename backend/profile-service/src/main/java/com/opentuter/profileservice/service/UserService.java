package com.opentuter.profileservice.service;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.dto.UserProfileUpdateDto;
import com.opentuter.profileservice.mapper.UserMapper;
import com.opentuter.profileservice.model.User;
import com.opentuter.profileservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy; // 1. IMPORTANT IMPORT
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder encoder,
                       @Lazy AuthenticationManager authenticationManager,
                       JwtService jwtService)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    //**
    // implement the function for register user
    // password encode using bcrpt*/
    public UserProfileResponseDto addUser(UserProfileRequestDto registrationDto)
    {
//        User user = UserMapper.toUserModel(registrationDto);
//        user.setPassword(encoder.encode(registrationDto.getPassword()));
//
//
//        User saveUser = userRepository.save(user);
//        return userMapper.toReseponseDTO(saveUser);

        User user = UserMapper.toUserModel(registrationDto);
        user.setPassword(encoder.encode(registrationDto.getPassword()));
        User savedUser = userRepository.save(user);
        return  UserMapper.
    }

    //**
    // implement the function for verify user
    // and authenticate with jwt token*/
    public String verify(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        if(authentication.isAuthenticated()){
            User user = userRepository.findByEmail(email);
            return jwtService.generateToken(user);
        }
        return "Fail";
    }
     //**
     // implement the function for get user by email*/
    public UserProfileResponseDto getUser(@PathVariable String email)
    {
        User user = userRepository.findByEmail(email);

        if(user == null)
        {
            throw new RuntimeException("User is not found");
        }
        return userMapper.toReseponseDTO(user);
    }

    //**
    // implement the function for delete user */
    public UserProfileResponseDto deleteUser(@PathVariable String email)
    {
       User user = userRepository.findByEmail(email);

       if(user == null)
       {
           throw new RuntimeException("User is not found");
       }

       userRepository.delete(user);

       return userMapper.toReseponseDTO(user);
    }

    //**
    // implement function for get all user*/
    public List<UserProfileResponseDto> getAllUser()
    {


        return userRepository.findAll()
                .stream()
                .map(userMapper::toReseponseDTO)
                .collect(Collectors.toList());
    }

    //**
// implement method for update user by email*/
    public UserProfileResponseDto updateUser(Long id, UserProfileUpdateDto userUpdateDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userUpdateDto.getRole() != null) {
            user.setRole(userUpdateDto.getRole());
        }

        if (userUpdateDto.getPassword() != null) {
            user.setPassword(encoder.encode(userUpdateDto.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        return userMapper.toReseponseDTO(updatedUser);
    }

}