package com.opentuter.userservice.service;

import com.opentuter.userservice.dto.UserRejistrationDto;
import com.opentuter.userservice.dto.UserResponseDto;
import com.opentuter.userservice.mapper.UserMapper;
import com.opentuter.userservice.model.User;
import com.opentuter.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy; // 1. IMPORTANT IMPORT
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder encoder,
                       @Lazy AuthenticationManager authenticationManager, // 2. IMPORTANT: @Lazy prevents StackOverflow
                       JwtService jwtService)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserResponseDto addUser(UserRejistrationDto registrationDto)
    {
        User user = UserMapper.toUserModel(registrationDto);
        user.setPassword(encoder.encode(registrationDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        User saveUser = userRepository.save(user);
        return userMapper.toReseponseDTO(saveUser);
    }

    public String verify(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        if(authentication.isAuthenticated()){
            User user = userRepository.findByEmail(email);
            return jwtService.generateToken(user);
        }
        return "Fail";
    }
}