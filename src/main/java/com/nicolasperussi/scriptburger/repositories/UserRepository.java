package com.nicolasperussi.scriptburger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.nicolasperussi.scriptburger.domain.Courier;
import com.nicolasperussi.scriptburger.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByEmail(String email);

  @Query("SELECT c FROM Courier c")
  List<Courier> findAllCouriers();
}
