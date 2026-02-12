package com.opentuter.profileservice.service;

import com.opentuter.profileservice.dto.ProfileRequestDto;
import com.opentuter.profileservice.dto.ProfileResponseDto;
import com.opentuter.profileservice.dto.ProfileUpdateDto;
import com.opentuter.profileservice.dto.LoginResponseDto;
import com.opentuter.profileservice.exception.BadRequestException;
import com.opentuter.profileservice.exception.ResourceNotFoundException;
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

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public ProfileService(UserRepository userRepository,
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
    public ProfileResponseDto addUser(ProfileRequestDto registrationDto)
    {
        if(userRepository.findByEmail(registrationDto.getEmail()) !=  null)
        {
            throw new BadRequestException("Email already exists");
        }

        try {
            User user = UserMapper.toUserModel(registrationDto);
            user.setPassword(encoder.encode(registrationDto.getPassword()));
            User savedUser = userRepository.save(user);
            return userMapper.toReseponseDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error while registering user");
        }
    }

    //**
    // implement the function for login user and generate jwt token
    // return the token using AuthRespnseDto
    // */
    public LoginResponseDto verify(String email, String password) {
        try {
            // using authentication manager to authenticate user using email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // implement the method for generate the jwt token
            if (authentication.isAuthenticated()) {
                User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new ResourceNotFoundException("User not found");
                }


                String token = jwtService.generateToken(user);
                Instant expiresAt = Instant.now().plusSeconds(86400);


                return new LoginResponseDto(token, user.getEmail(), user.getRole(), expiresAt);
            }

            throw new BadRequestException("Invalid credentials");
        } catch (BadRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Authentication failed: " + e.getMessage());
        }
    }

    //**
    // implement the function for get user by email*/
    public ProfileResponseDto getUser(String email)
    {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User is not found");
        }

        return userMapper.toReseponseDTO(user);
    }

    //**
    // implement function for get all user*/
    public List<ProfileResponseDto> getAllUser()
    {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(userMapper::toReseponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching users");
        }
    }

    //**
    // implement the function for update user and user profile*/
    public ProfileResponseDto updateUser(UUID uuid, ProfileUpdateDto userProfileUpdateDto)
    {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User is not found"));

        if (userProfileUpdateDto.getEmail() != null) {
            user.setEmail(userProfileUpdateDto.getEmail());
        }

        if (userProfileUpdateDto.getRole() != null) {
            user.setRole(userProfileUpdateDto.getRole());
        }

        Profile profile = user.getProfile();
        if (profile != null) {
            if (userProfileUpdateDto.getFullName() != null) {
                profile.setFullName(userProfileUpdateDto.getFullName());
            }

            if (userProfileUpdateDto.getBio() != null) {
                profile.setBio(userProfileUpdateDto.getBio());
            }

            if (userProfileUpdateDto.getImageUrl() != null) {
                profile.setImageUrl(userProfileUpdateDto.getImageUrl());
            }

            if (userProfileUpdateDto.getSocialMediaUrls() != null) {
                profile.setSocialMediaUrls(userProfileUpdateDto.getSocialMediaUrls());
            }
        }

        try {
            return userMapper.toReseponseDTO(userRepository.save(user));
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user");
        }
    }

    //**
    // implement the function for delete user */
    public ProfileResponseDto deleteUser(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User is not found");
        }

        try {
            userRepository.delete(user);
            return userMapper.toReseponseDTO(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user");
        }
    }

}
