package com.opentuter.userservice.controller;

import com.opentuter.userservice.dto.UserRejistrationDto;
import com.opentuter.userservice.dto.UserResponseDto;
import com.opentuter.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRejistrationDto userRejistrationDto)
    {
        UserResponseDto response = userService.addUser(userRejistrationDto);
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
}