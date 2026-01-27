package com.opentutor.profileservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ProfileRequestDTO {

    @NotBlank(message = "Name is required")
    @Length(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Length(min = 10, max = 10, message = "Phone number should be valid")
    private String phone;

    private String address;

    // Constructors
    public ProfileRequestDTO() {
    }

    public ProfileRequestDTO(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
