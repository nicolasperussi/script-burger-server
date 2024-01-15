package com.nicolasperussi.scriptburger.domain.dtos;

import java.util.List;

import com.nicolasperussi.scriptburger.domain.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class RegisterDTO {
    @NotBlank(message = "O usuário deve ter um nome")
    private String name;

    @NotBlank(message = "O usuário deve ter um e-mail")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @NotBlank(message = "O usuário deve ter um número de telefone")
    private String phone;

    @NotBlank(message = "O usuário deve ter uma senha")
    private String password;

    @NotEmpty(message = "O usuário deve ter pelo menos um endereço cadastrado")
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