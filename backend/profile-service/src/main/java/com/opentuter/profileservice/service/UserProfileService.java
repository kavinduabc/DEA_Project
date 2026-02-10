package com.opentuter.profileservice.service;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.dto.UserProfileUpdateDto;
import com.opentuter.profileservice.mapper.UserMapper;
import com.opentuter.profileservice.model.Profile;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public UserProfileService(UserRepository userRepository,
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


        User user = UserMapper.toUserModel(registrationDto);
        user.setPassword(encoder.encode(registrationDto.getPassword()));
        User savedUser = userRepository.save(user);
        return  userMapper.toReseponseDTO(savedUser);
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
        throw new RuntimeException("Invalid credentials");
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
    // implement function for get all user*/
    public List<UserProfileResponseDto> getAllUser()
    {


        return userRepository.findAll()
                .stream()
                .map(userMapper::toReseponseDTO)
                .collect(Collectors.toList());
    }


    //**
    // implement the function for update user and user profile*/
    public UserProfileResponseDto updateUser(UUID uuid, UserProfileUpdateDto userProfileUpdateDto)
    {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User is Not found"));

        if(userProfileUpdateDto.getEmail() != null)
        {
            user.setEmail(userProfileUpdateDto.getEmail());
        }

        if(userProfileUpdateDto.getRole() != null)
        {
            user.setRole(userProfileUpdateDto.getRole());
        }

        Profile profile = user.getProfile();
        if(profile != null)
        {
            if(userProfileUpdateDto.getFullName() != null)
            {
                profile.setFullName(userProfileUpdateDto.getFullName());
            }

            if(userProfileUpdateDto.getBio() != null)
            {
                profile.setBio(userProfileUpdateDto.getBio());
            }

            if(userProfileUpdateDto.getImageUrl() != null)
            {
                profile.setImageUrl(userProfileUpdateDto.getImageUrl());
            }

            if(userProfileUpdateDto.getSocialMediaUrls() != null)
            {
                profile.setSocialMediaUrls(userProfileUpdateDto.getSocialMediaUrls());
            }
        }

        return userMapper.toReseponseDTO(user);
    }

    //**
    // implement the function for delete user */
    public UserProfileResponseDto deleteUser(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User is not found");
        }

        userRepository.delete(user);

        return userMapper.toReseponseDTO(user);
    }



}