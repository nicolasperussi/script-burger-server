package com.nicolasperussi.scriptburger.config;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.services.ProductService;
import com.nicolasperussi.scriptburger.utils.Slugify;

@Configuration
public class Seeding implements CommandLineRunner {
  @Autowired
  ProductService productService;

  @Override
  public void run(String... args) throws Exception {
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

}
