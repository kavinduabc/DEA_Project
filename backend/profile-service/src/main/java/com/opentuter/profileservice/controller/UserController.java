package com.opentuter.profileservice.controller;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.dto.UserProfileUpdateDto;
import com.opentuter.profileservice.model.User;
import com.opentuter.profileservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

     private final UserProfileService userProfileService;


    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/reg")
    public UserProfileResponseDto addUser(@RequestBody UserProfileRequestDto userProfileRequestDto)
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
    public UserProfileResponseDto viewUserById(@PathVariable String email)
    {
        return userProfileService.getUser(email);
    }

    @GetMapping("/views")
    public List<UserProfileResponseDto> viewAllUsers()
    {
        return  userProfileService.getAllUser();
    }

    @PutMapping("/update/{id}")
    public UserProfileResponseDto updateUser(
            @PathVariable UUID uuid,
            @RequestBody UserProfileUpdateDto userProfileUpdateDto

            )
    {
        return userProfileService.updateUser(uuid, userProfileUpdateDto);
    }

    @DeleteMapping("/delete/{email}")
    public UserProfileResponseDto deleteUser(@PathVariable String email)
    {
        return userProfileService.deleteUser(email);
    }
}