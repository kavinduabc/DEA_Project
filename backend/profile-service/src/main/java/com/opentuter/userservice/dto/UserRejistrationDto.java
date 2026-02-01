package com.opentuter.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


//**
// A DTO is like a  saaled envelope.
// it is use for carry a data detween frontend to backend
// */
//**
// A DTO is a plain class used to send data
// between backend and frontend.
// --Important in DTO class--
//   security purpose .don't accidentally leak sensitive data
//   you send smaller amount od data over the internet, which makes your app faster
//   */
public class UserRejistrationDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
