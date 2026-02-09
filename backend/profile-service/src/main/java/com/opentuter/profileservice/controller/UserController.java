package com.opentuter.profileservice.controller;

import com.opentuter.profileservice.dto.UserProfileRequestDto;
import com.opentuter.profileservice.dto.UserResponseDto;
import com.opentuter.profileservice.dto.UserUpdateDto;
import com.opentuter.profileservice.service.UserService;
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
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserProfileRequestDto userRejistrationDto)
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

    @GetMapping("/view/{email}")
    public UserResponseDto getUser(@PathVariable String email)
    {
        return userService.getUser(email);
    }

    @GetMapping("/veiwAll")
    public List<UserResponseDto> getAllUser()
    {
        return userService.getAllUser();
    }

    @PutMapping("/update/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateDto userUpdateDto
    ) {
        return userService.updateUser(id, userUpdateDto);
    }




    @DeleteMapping("/delete/{email}")
    public  UserResponseDto deleteUser(@PathVariable String email)
    {
        return userService.deleteUser(email);
    }
}