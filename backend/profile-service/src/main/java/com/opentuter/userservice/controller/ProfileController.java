package com.opentuter.userservice.controller;

import com.opentuter.userservice.dto.ProfileDto;
import com.opentuter.userservice.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;


    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/{id}")
    public ProfileDto createProfile(
            @PathVariable Long id,
            @RequestBody ProfileDto profileDto
    ){
        return profileService.createProfile(id, profileDto);
    }

    @GetMapping("/{id}")
    public ProfileDto  getProfile(@PathVariable Long id)
    {
        return profileService.getProfile(id);
    }

    @PutMapping("/{id}")
    public ProfileDto updateProfile(
            @PathVariable Long id,
            @RequestBody(required = false) ProfileDto profileDto
    ){
        return profileService.updateProfile(id, profileDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id)
    {
        profileService.deleteProfile(id);
        return "Profile deleted successfully";
    }
}
