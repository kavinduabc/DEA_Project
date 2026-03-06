package com.opentuter.profileservice.controller;

import com.opentuter.profileservice.dto.LoginResponseDto;
import com.opentuter.profileservice.dto.LoginRequestDto;
import com.opentuter.profileservice.dto.ProfileRequestDto;
import com.opentuter.profileservice.dto.ProfileResponseDto;
import com.opentuter.profileservice.dto.ProfileUpdateDto;
import com.opentuter.profileservice.service.ProfileService;
import com.opentuter.profileservice.service.impl.ProfileServiceImpl;
import com.opentuter.profileservice.util.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
@RestController

@RequestMapping(Utils.BASE_URL)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProfileController {

    private final ProfileService userProfileService;

    @Autowired
    public ProfileController(ProfileServiceImpl userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping(Utils.REGISTRATION_URL)
    public ResponseEntity<ProfileResponseDto> addUser(
            @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto response = userProfileService.addUser(profileRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(Utils.VERIFY_URL)
    public ResponseEntity<LoginResponseDto> verifyUser(
            @Valid @RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto authResponse = userProfileService.verify(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping(Utils.VIEW_USER_BY_ID_URL)
    public ResponseEntity<ProfileResponseDto> viewUserById(
            @PathVariable UUID id) {
        ProfileResponseDto response = userProfileService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(Utils.VIEW_USER_BY_EMAIL_URL)
    public ResponseEntity<ProfileResponseDto> viewUserByEmail(
            @PathVariable String email) {
        ProfileResponseDto response = userProfileService.getUserByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(Utils.VIEW_USER_URL)
    public ResponseEntity<List<ProfileResponseDto>> viewAllUsers() {
        List<ProfileResponseDto> response = userProfileService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(Utils.UPDATE_USER_BY_ID_URL)
    public ResponseEntity<ProfileResponseDto> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody ProfileUpdateDto profileUpdateDto) {
        ProfileResponseDto response = userProfileService.updateUser(id, profileUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(Utils.DELETE_USER_BY_ID_URL)
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {

        userProfileService.deleteUser(id);

        return ResponseEntity.ok(Utils.USER_DELETED);
    }
}