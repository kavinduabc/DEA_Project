package com.opentuter.userservice.controller;

import com.opentuter.userservice.dto.UserRejistrationDto;
import com.opentuter.userservice.dto.UserResponseDto;
import com.opentuter.userservice.model.User;
import com.opentuter.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return  new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
