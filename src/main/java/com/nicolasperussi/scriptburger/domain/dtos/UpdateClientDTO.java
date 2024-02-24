package com.nicolasperussi.scriptburger.domain.dtos;

import java.util.List;

import com.nicolasperussi.scriptburger.domain.Address;

import jakarta.validation.constraints.Email;

public class UpdateClientDTO {
    private String name;

    @Email(message = "The e-mail must be a valid e-mail address")
    private String email;

    private String phone;

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}