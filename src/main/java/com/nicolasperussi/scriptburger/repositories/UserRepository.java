package com.nicolasperussi.scriptburger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.nicolasperussi.scriptburger.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByEmail(String email);
}
