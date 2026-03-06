package com.opentuter.profileservice.service.impl;

import com.opentuter.profileservice.dto.*;
import com.opentuter.profileservice.exception.BadRequestException;
import com.opentuter.profileservice.exception.ResourceNotFoundException;
import com.opentuter.profileservice.mapper.UserMapper;
import com.opentuter.profileservice.model.Profile;
import com.opentuter.profileservice.model.User;
import com.opentuter.profileservice.repository.UserRepository;
import com.opentuter.profileservice.service.JwtService;
import com.opentuter.profileservice.service.ProfileService;

import com.opentuter.profileservice.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class ProfileServiceImpl implements ProfileService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository,
                              UserMapper userMapper,
                              PasswordEncoder encoder,
                              @Lazy AuthenticationManager authenticationManager,
                              JwtService jwtService) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    //**
    // implement methods for addUser
    // first of all , check user is already in sign up in our system */
    @Override
    public ProfileResponseDto addUser(ProfileRequestDto dto) {

        User existingUser = userRepository.findByEmail(dto.getEmail());

        if (existingUser != null) {
            throw new BadRequestException(Utils.EMAIL_ALREADY_USE);
        }

        User user = UserMapper.toUserModel(dto);
        user.setPassword(encoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toReseponseDTO(savedUser);
    }

    //**
    // implement the method for verify user ..
    // and this method include authentication with jwt token
    // */
    @Override
    public LoginResponseDto verify(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        if (!authentication.isAuthenticated()) {
            throw new BadRequestException(Utils.INVALID_CREDENTIALS);
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException(Utils.USER_NOT_FOUND);
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDto(
                token,
                user.getEmail(),
                user.getRole(),
                Instant.now().plusSeconds(Utils.tokenExpirationTime)
        );
    }

    //**
    // implement the method for get user by id
    // */
    @Override
    public ProfileResponseDto getUserById(UUID id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ResourceNotFoundException(Utils.USER_NOT_FOUND);
        }

        return userMapper.toReseponseDTO(user);
    }

    //**
    // implement the method for get user by email
    // */
    @Override
    public ProfileResponseDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException(Utils.USER_NOT_FOUND);
        }

        return userMapper.toReseponseDTO(user);
    }

    //**
    // implement the method for get all users
    // */
    @Override
    public List<ProfileResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::toReseponseDTO)
                .collect(Collectors.toList());
    }

    //**
    // implement the method for update user using user id
    //*/
    @Override
    public ProfileResponseDto updateUser(UUID id, ProfileUpdateDto dto) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ResourceNotFoundException(Utils.USER_NOT_FOUND);
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

        Profile profile = user.getProfile();

        if (profile != null) {

            if (dto.getFullName() != null) {
                profile.setFullName(dto.getFullName());
            }

            if (dto.getBio() != null) {
                profile.setBio(dto.getBio());
            }

            if (dto.getImageUrl() != null) {
                profile.setImageUrl(dto.getImageUrl());
            }

            if (dto.getSocialMediaUrls() != null) {
                profile.setSocialMediaUrls(dto.getSocialMediaUrls());
            }
        }

        User updatedUser = userRepository.save(user);

        return userMapper.toReseponseDTO(updatedUser);
    }

    //**
    // implement the method for delete user using user id
    //*/
    @Override
    public ProfileResponseDto deleteUser(UUID id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ResourceNotFoundException(Utils.USER_NOT_FOUND);
        }

        userRepository.delete(user);

        return userMapper.toReseponseDTO(user);
    }
}