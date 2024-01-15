package com.nicolasperussi.scriptburger.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasperussi.scriptburger.domain.User;
import com.nicolasperussi.scriptburger.domain.dtos.LoginDTO;
import com.nicolasperussi.scriptburger.domain.dtos.LoginResponseDTO;
import com.nicolasperussi.scriptburger.domain.dtos.RegisterDTO;
import com.nicolasperussi.scriptburger.exceptions.DatabaseException;
import com.nicolasperussi.scriptburger.repositories.UserRepository;
import com.nicolasperussi.scriptburger.security.TokenService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository repository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO data) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),
        data.password());

    Authentication auth = this.authenticationManager.authenticate(usernamePassword);

    User user = (User) auth.getPrincipal();
    String token = tokenService.generateToken(user);

    return ResponseEntity.ok((new LoginResponseDTO(token, user)));
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(@Valid @RequestBody RegisterDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    User newUser = new User(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword, data.getAddresses());

    this.repository.save(newUser);

    return ResponseEntity.ok().build();
  }
}