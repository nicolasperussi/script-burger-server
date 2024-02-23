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

import com.nicolasperussi.scriptburger.domain.Admin;
import com.nicolasperussi.scriptburger.domain.Client;
import com.nicolasperussi.scriptburger.domain.Courier;
import com.nicolasperussi.scriptburger.domain.User;
import com.nicolasperussi.scriptburger.domain.dtos.LoginDTO;
import com.nicolasperussi.scriptburger.domain.dtos.LoginResponseDTO;
import com.nicolasperussi.scriptburger.domain.dtos.RegisterAdminDTO;
import com.nicolasperussi.scriptburger.domain.dtos.RegisterClientDTO;
import com.nicolasperussi.scriptburger.domain.dtos.RegisterCourierDTO;
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

  @PostMapping("/register/client")
  public ResponseEntity<Void> registerClient(@Valid @RequestBody RegisterClientDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Client newClient = new Client(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword);

    this.repository.save(newClient);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/register/admin")
  public ResponseEntity<Void> registerAdmin(@Valid @RequestBody RegisterAdminDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Admin newAdmin = new Admin(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword);

    this.repository.save(newAdmin);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/register/courier")
  public ResponseEntity<Void> registerCourier(@Valid @RequestBody RegisterCourierDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Courier newCourier = new Courier(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword, data.getLicensePlate());

    this.repository.save(newCourier);

    return ResponseEntity.ok().build();
  }
}