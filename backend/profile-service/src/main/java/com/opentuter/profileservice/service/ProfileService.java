package com.opentuter.profileservice.service;

import com.opentuter.profileservice.dto.ProfileRequestDto;
import com.opentuter.profileservice.dto.ProfileResponseDto;
import com.opentuter.profileservice.dto.ProfileUpdateDto;
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
import org.springframework.web.bind.annotation.PathVariable;

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
        try{
            if(userRepository.findByEmail(registrationDto.getEmail()) !=  null)
            {
                throw  new BadRequestException("Email already exists");
            }

            User user = UserMapper.toUserModel(registrationDto);
            user.setPassword(encoder.encode(registrationDto.getPassword()));
            User savedUser = userRepository.save(user);
            return  userMapper.toReseponseDTO(savedUser);

        }catch (BadRequestException ex)
        {
            throw  ex;
        } catch (Exception e) {
            throw new RuntimeException("Error while registrering user");
        }

    }

    //**
    // implement the function for verify user
    // and authenticate with jwt token*/
    public String verify(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            if (authentication.isAuthenticated()) {
                User user = userRepository.findByEmail(email);
                return jwtService.generateToken(user);
            }
            throw new RuntimeException("Invalid credentials");
        }catch (BadRequestException ex)
        {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Authentication faild");
        }
    }
     //**
     // implement the function for get user by email*/
    public ProfileResponseDto getUser(@PathVariable String email)
    {
        try {
            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new RuntimeException("User is not found");
            }
            return userMapper.toReseponseDTO(user);
        } catch (ResourceNotFoundException ex)
        {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching user");
        }
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
        try {
            User user = userRepository.findById(uuid)
                    .orElseThrow(() -> new RuntimeException("User is Not found"));

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

            return userMapper.toReseponseDTO(user);
        }catch(ResourceNotFoundException ex)
        {
            throw  ex;
        }catch(Exception e)
        {
            throw new RuntimeException("Error while updating user");
        }
    }

    //**
    // implement the function for delete user */
    public ProfileResponseDto deleteUser(String email) {
        try {
            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new RuntimeException("User is not found");
            }

            userRepository.delete(user);

            return userMapper.toReseponseDTO(user);
        }catch(ResourceNotFoundException ex)
        {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user");
        }
    }



}