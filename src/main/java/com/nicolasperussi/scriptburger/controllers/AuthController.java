package com.nicolasperussi.scriptburger.controllers;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.nicolasperussi.scriptburger.domain.dtos.UpdateAdminDTO;
import com.nicolasperussi.scriptburger.domain.dtos.UpdateClientDTO;
import com.nicolasperussi.scriptburger.domain.dtos.UpdateCourierDTO;
import com.nicolasperussi.scriptburger.exceptions.DatabaseException;
import com.nicolasperussi.scriptburger.repositories.UserRepository;
import com.nicolasperussi.scriptburger.security.TokenService;
import com.nicolasperussi.scriptburger.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/auth", produces = { "application/json" })
@Tag(name = "Authentication")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository repository;
  @Autowired
  private UserService service;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  @Operation(summary = "Authenticate user", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User authenticated."),
  })
  public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO data) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),
        data.password());

    Authentication auth = this.authenticationManager.authenticate(usernamePassword);

    User user = (User) auth.getPrincipal();
    String token = tokenService.generateToken(user);

    return ResponseEntity.ok((new LoginResponseDTO(token, user)));
  }

  @PostMapping("/register/client")
  @Operation(summary = "Register a new client", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Client registered."),
  })
  public ResponseEntity<Void> registerClient(@Valid @RequestBody RegisterClientDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Client newClient = new Client(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword);

    this.repository.save(newClient);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/register/admin")
  @Operation(summary = "Register a new admin", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Admin registered."),
  })
  public ResponseEntity<Void> registerAdmin(@Valid @RequestBody RegisterAdminDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Admin newAdmin = new Admin(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword);

    this.repository.save(newAdmin);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/register/courier")
  @Operation(summary = "Register a new courier", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Courier registered."),
  })
  public ResponseEntity<Void> registerCourier(@Valid @RequestBody RegisterCourierDTO data) {
    if (this.repository.findByEmail(data.getEmail()) != null)
      throw new DatabaseException("This e-mail is already in use");

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Courier newCourier = new Courier(data.getName(), data.getEmail(), data.getPhone(), encryptedPassword,
        data.getLicensePlate());

    this.repository.save(newCourier);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/get/couriers")
  public ResponseEntity<List<Courier>> getAllCouriers() {
    List<Courier> list = repository.findAllCouriers();
    if (list.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return ResponseEntity.ok().body(list);
  }

  @PatchMapping("/update/client/{clientId}")
  @Operation(summary = "Update client informations", method = "PATCH")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Client informations updated successfully."),
  })
  public ResponseEntity<Void> updateClient(@PathVariable Long clientId, @Valid @RequestBody UpdateClientDTO data) {
    Client client = (Client) this.service.findById(clientId);

    if (data.getName() != null)
      client.setName(data.getName());
    if (data.getEmail() != null)
      client.setEmail(data.getEmail());
    if (data.getPhone() != null)
      client.setPhone(data.getPhone());
    if (data.getAddresses() != null)
      client.setAddresses(data.getAddresses());

    this.repository.save(client);

    return ResponseEntity.ok().build();
  }

  @PatchMapping("/update/admin/{adminId}")
  @Operation(summary = "Update admin informations", method = "PATCH")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Admin informations updated successfully."),
  })
  public ResponseEntity<Void> updateAdmin(@PathVariable Long adminId, @Valid @RequestBody UpdateAdminDTO data) {
    Admin admin = (Admin) this.service.findById(adminId);

    if (data.getName() != null)
      admin.setName(data.getName());
    if (data.getEmail() != null)
      admin.setEmail(data.getEmail());
    if (data.getPhone() != null)
      admin.setPhone(data.getPhone());

    this.repository.save(admin);

    return ResponseEntity.ok().build();
  }

  @PatchMapping("/update/courier/{courierId}")
  @Operation(summary = "Update courier informations", method = "PATCH")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Courier informations updated successfully."),
  })
  public ResponseEntity<Void> updateCourier(@PathVariable Long courierId, @Valid @RequestBody UpdateCourierDTO data) {
    Courier courier = (Courier) this.service.findById(courierId);

    if (data.getName() != null)
      courier.setName(data.getName());
    if (data.getEmail() != null)
      courier.setEmail(data.getEmail());
    if (data.getPhone() != null)
      courier.setPhone(data.getPhone());
    if (data.getLicensePlate() != null)
      courier.setLicensePlate(data.getLicensePlate());

    this.repository.save(courier);

    return ResponseEntity.ok().build();
  }

  // TODO: change user password (should return a new jwt)
}