package com.nicolasperussi.scriptburger.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.nicolasperussi.scriptburger.domain.Address;
import com.nicolasperussi.scriptburger.domain.Admin;
import com.nicolasperussi.scriptburger.domain.Client;
import com.nicolasperussi.scriptburger.domain.Courier;
import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.repositories.UserRepository;
import com.nicolasperussi.scriptburger.services.ProductService;
import com.nicolasperussi.scriptburger.utils.Slugify;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class Seeding implements CommandLineRunner {
  Faker faker = new Faker(new Locale("pt-BR"));

  @Autowired
  ProductService productService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    populateProducts();
    createDefaultUsers();
    createFakeUsers();
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

  private void createDefaultUsers() {
    Dotenv dotenv = null;
    dotenv = Dotenv.configure().load();

    String encryptedPassword = new BCryptPasswordEncoder().encode(dotenv.get("ADMIN_KEY"));
    Address defaultAddress = new Address("01001-000", "Praça da Sé", "0");
    Address secondAddress = new Address("03280-020", "Rua Manoel Luiz de Araújo Costa", "150");
    List<Address> addresses = new ArrayList<Address>();
    addresses.add(defaultAddress);

    // Create Admin user
    Admin newAdmin = new Admin("Administrator", "admin@admin.com", "999999999", encryptedPassword);

    // Create Client user
    Client newClient = new Client("Nicolas Perussi", "client@client.com", "999999999", encryptedPassword);
    newClient.addAddress(defaultAddress);
    newClient.addAddress(secondAddress);

    // Create Courier user
    Courier newCourier = new Courier("Elizeu Antunes", "courier@courier.com", "999999999", encryptedPassword,
        "EUS8753");

    // Save admin, client and courier
    this.userRepository.saveAll(Arrays.asList(newAdmin, newClient, newCourier));
  }

  private void createFakeUsers() {
    List<Client> clients = new ArrayList<Client>();

    for (int i = 0; i < 30; i++) {
      clients
          .add(new Client(faker.name().name(), faker.internet().emailAddress(),
              faker.phoneNumber().cellPhone(),
              faker.internet().password()));
    }

    for (Client client : clients) {
      userRepository.save(client);
    }
  }
}
