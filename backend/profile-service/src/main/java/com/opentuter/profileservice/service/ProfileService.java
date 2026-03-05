package com.opentuter.profileservice.service;

import com.opentuter.profileservice.dto.LoginResponseDto;
import com.opentuter.profileservice.dto.ProfileRequestDto;
import com.opentuter.profileservice.dto.ProfileResponseDto;
import com.opentuter.profileservice.dto.ProfileUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    public ProfileResponseDto addUser(ProfileRequestDto profileRequestDto);
    public LoginResponseDto verify(String email, String password);
    public ProfileResponseDto getUserById(UUID id);
    public List<ProfileResponseDto> getAllUsers();
    public ProfileResponseDto updateUser(UUID id, ProfileUpdateDto profileUpdateDto);
    public ProfileResponseDto deleteUser(UUID id);

}
