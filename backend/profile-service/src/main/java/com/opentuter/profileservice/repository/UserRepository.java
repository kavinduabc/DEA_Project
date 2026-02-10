package com.opentuter.profileservice.repository;

import com.opentuter.profileservice.dto.UserProfileResponseDto;
import com.opentuter.profileservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);


    UserProfileResponseDto deleteByEmail(String email);

    List<User> id(Long id);
}

