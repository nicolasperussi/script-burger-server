package com.nicolasperussi.scriptburger.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin() {
    }

    public Admin(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
