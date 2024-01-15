package com.nicolasperussi.scriptburger.domain.dtos;

import java.util.List;

import com.nicolasperussi.scriptburger.domain.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class RegisterDTO {
    @NotBlank(message = "The user must have a name")
    private String name;

    @NotBlank(message = "The user must have an e-mail")
    @Email(message = "The e-mail must be a valid e-mail address")
    private String email;

    @NotBlank(message = "The user must have a phone number")
    private String phone;

    @NotBlank(message = "The user must have a password")
    private String password;

    @NotEmpty(message = "The user must have at least one address")
    private List<Address> addresses;

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}