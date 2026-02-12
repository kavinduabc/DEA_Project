package com.opentuter.profileservice.controller;

import com.opentuter.profileservice.dto.LoginResponseDto;
import com.opentuter.profileservice.dto.LoginRequestDto;
import com.opentuter.profileservice.dto.ProfileRequestDto;
import com.opentuter.profileservice.dto.ProfileResponseDto;
import com.opentuter.profileservice.dto.ProfileUpdateDto;
import com.opentuter.profileservice.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProfileController {

    private final ProfileService userProfileService;

    @Autowired
    public ProfileController(ProfileService userProfileService) {

        this.userProfileService = userProfileService;
    }

    //**
    // implement the endpoint for register user  */
    @PostMapping("/reg")
    public ResponseEntity<ProfileResponseDto> addUser(
            @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto response = userProfileService.addUser(profileRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //**
    // implement the endpoint for verify the user  */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> verifyUser(
            @Valid @RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto authResponse = userProfileService.verify(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    //**
    // implement the endpoint for get user using email  */
    @GetMapping("/view/{email}")
    public ResponseEntity<ProfileResponseDto> viewUserByEmail(
            @PathVariable String email) {
        ProfileResponseDto response = userProfileService.getUser(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //**
    // implement the endpoint for get all users  */
    @GetMapping("/views")
    public ResponseEntity<List<ProfileResponseDto>> viewAllUsers() {
        List<ProfileResponseDto> response = userProfileService.getAllUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //**
    // implement the endpoint for update user using uuid  */
    @PutMapping("/update/{uuid}")
    public ResponseEntity<ProfileResponseDto> updateUser(
            @PathVariable UUID uuid,
            @Valid @RequestBody ProfileUpdateDto profileUpdateDto) {
        ProfileResponseDto response = userProfileService.updateUser(uuid, profileUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //**
    // implement the endpoint for delete user using email  */
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ProfileResponseDto> deleteUser(
            @PathVariable String email) {
        ProfileResponseDto response = userProfileService.deleteUser(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}