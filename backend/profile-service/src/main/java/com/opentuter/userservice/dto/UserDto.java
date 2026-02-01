package com.opentuter.userservice.dto;

import java.time.LocalDateTime;

//**
// A DTO is a plain class used to send data
// between backend and frontend.
// --Important in DTO class--
//   security purpose .don't accidentally leak sensitive data
//   you send smaller amount od data over the internet, which makes your app faster
//   */
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createsAt;


}
