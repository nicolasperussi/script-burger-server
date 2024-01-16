package com.nicolasperussi.scriptburger.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolasperussi.scriptburger.domain.Address;
import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.domain.User;
import com.nicolasperussi.scriptburger.domain.enums.UserRole;
import com.nicolasperussi.scriptburger.repositories.UserRepository;
import com.nicolasperussi.scriptburger.services.ProductService;
import com.nicolasperussi.scriptburger.utils.Slugify;

@Configuration
public class Seeding implements CommandLineRunner {
  @Autowired
  ProductService productService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    populateProducts();
    createAdminUser();
  }

  private void populateProducts() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Product[] products = objectMapper.readValue(new File("public/products.json"), Product[].class);

      for (Product product : products) {
        Product newProduct = new Product(product.getName(), product.getDescription(),
            Slugify.slugify(product.getName()), product.getOverview(), product.getPrice(),
            product.getCategory());
        productService.create(newProduct);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createAdminUser() {
    String encryptedPassword = new BCryptPasswordEncoder().encode("admin");
    Address defaultAddress = new Address("01001-000", "Praça da Sé", "0");
    List<Address> addresses = new ArrayList<Address>();
    addresses.add(defaultAddress);

    User newUser = new User("Administrator", "admin@admin.com", "999999999", encryptedPassword, addresses);
    newUser.setRole(UserRole.ADMIN);
    this.userRepository.save(newUser);
  }

}
