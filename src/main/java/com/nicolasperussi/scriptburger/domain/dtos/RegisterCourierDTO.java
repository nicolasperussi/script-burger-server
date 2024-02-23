package com.nicolasperussi.scriptburger.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterCourierDTO {
    @NotBlank(message = "The courier must have a name")
    private String name;

    @NotBlank(message = "The courier must have an e-mail")
    @Email(message = "The e-mail must be a valid e-mail address")
    private String email;

    @NotBlank(message = "The courier must have a phone number")
    private String phone;

    @NotBlank(message = "The courier must have a password")
    private String password;

    @NotBlank(message = "The courier must have a license plate for his vehicle")
    private String licensePlate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    
}