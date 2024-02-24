package com.nicolasperussi.scriptburger.domain.dtos;

import jakarta.validation.constraints.Email;

public class UpdateCourierDTO {
    private String name;

    @Email(message = "The e-mail must be a valid e-mail address")
    private String email;

    private String phone;

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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}