package com.opentuter.profileservice.controller;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.dto.UserProfileUpdateDto;
import com.opentuter.profileservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserProfileService userService;

    public UserController(UserProfileService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileResponseDto> register(@RequestBody UserProfileRequestDto userRejistrationDto)
    {
        UserProfileResponseDto response = userService.addUser(userRejistrationDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Implement method for LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String token = userService.verify(loginData.get("email"), loginData.get("password"));
        if(token.equals("Fail")){
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/view/{email}")
    public UserProfileResponseDto getUser(@PathVariable String email)
    {
        return userService.getUser(email);
    }

    @GetMapping("/veiwAll")
    public List<UserProfileResponseDto> getAllUser()
    {
        return userService.getAllUser();
    }

    @PutMapping("/update/{id}")
    public UserProfileResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UserProfileUpdateDto userUpdateDto
    ) {
        return userService.updateUser(id, userUpdateDto);
    }




    @DeleteMapping("/delete/{email}")
    public UserProfileResponseDto deleteUser(@PathVariable String email)
    {
        return userService.deleteUser(email);
    }
}