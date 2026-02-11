package com.opentuter.profileservice.controller;

import com.opentuter.profileservice.dto.ProfileRequestDto;
import com.opentuter.profileservice.dto.ProfileResponseDto;
import com.opentuter.profileservice.dto.ProfileUpdateDto;
import com.opentuter.profileservice.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class ProfileController {

     private final ProfileService userProfileService;


    public ProfileController(ProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/reg")
    public ProfileResponseDto addUser(@RequestBody ProfileRequestDto userProfileRequestDto)
    {
        return  userProfileService.addUser(userProfileRequestDto);
    }

    @PostMapping("/logon")
    public String verifyUser(
            @PathVariable String email,
            @PathVariable String password
    ){
        return userProfileService.verify(email, password);
    }


    @GetMapping("/view/{email}")
    public ProfileResponseDto viewUserById(@PathVariable String email)
    {
        return userProfileService.getUser(email);
    }

    @GetMapping("/views")
    public List<ProfileResponseDto> viewAllUsers()
    {
        return  userProfileService.getAllUser();
    }

    @PutMapping("/update/{id}")
    public ProfileResponseDto updateUser(
            @PathVariable UUID uuid,
            @RequestBody ProfileUpdateDto userProfileUpdateDto

            )
    {
        return userProfileService.updateUser(uuid, userProfileUpdateDto);
    }

    @DeleteMapping("/delete/{email}")
    public ProfileResponseDto deleteUser(@PathVariable String email)
    {
        return userProfileService.deleteUser(email);
    }
}