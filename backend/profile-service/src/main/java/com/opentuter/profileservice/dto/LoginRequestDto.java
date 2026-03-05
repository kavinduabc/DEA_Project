package com.opentuter.profileservice.dto;

import com.opentuter.profileservice.util.Utils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {

    @Email(message = Utils.EMAIL_VALIDE)
    @NotBlank(message = Utils.EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = Utils.PASSWORD_NOT_BLANK)
    private String password;

    // Default Constructor
    public LoginRequestDto() {
    }

    // Constructor with parameters
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return Utils.LOGIN_TO_STRING_LOGREQ +
                Utils.LOGIN_TO_STRING_EMAIL + email + '\'' +
                ", password='" + (password != null ? "***" : "null") + '\'' +
                '}';
    }
}

