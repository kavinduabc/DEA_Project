package com.opentuter.profileservice.service;

import com.opentuter.profileservice.model.User;
import com.opentuter.profileservice.repository.UserRepository;
import com.opentuter.profileservice.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(Utils.USER_CHECK_EMAIL + email);
        }

        // Generic USER authority (not role-based) - authentication only, no role-based authorization
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
}