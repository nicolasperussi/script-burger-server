package com.nicolasperussi.scriptburger.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.domain.dtos.ProductDTO;
import com.nicolasperussi.scriptburger.services.ProductService;
import com.nicolasperussi.scriptburger.utils.Slugify;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<List<Product>> findAll() {
    List<Product> list = service.findAll();

    if (list.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> findById(@NonNull @PathVariable Long id) {
    Product product = service.findById(id);

    return ResponseEntity.ok().body(product);
  }

  @GetMapping("/sandwiches")
  public ResponseEntity<List<Product>> findAllSandwiches() {
    List<Product> sandwiches = service.findAllSandwiches();

    return ResponseEntity.ok().body(sandwiches);
  }

  @GetMapping("/sides")
  public ResponseEntity<List<Product>> findAllSides() {
    List<Product> sides = service.findAllSides();

    return ResponseEntity.ok().body(sides);
  }

  @GetMapping("/desserts")
  public ResponseEntity<List<Product>> findAllDesserts() {
    List<Product> desserts = service.findAllDesserts();

    return ResponseEntity.ok().body(desserts);
  }

  @GetMapping("/drinks")
  public ResponseEntity<List<Product>> findAllDrinks() {
    List<Product> drinks = service.findAllDrinks();

    return ResponseEntity.ok().body(drinks);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@Valid @ModelAttribute ProductDTO product) throws IOException {
    service.create(new Product(product.getName(), product.getDescription(),
        Slugify.slugify(product.getName()), product.getOverview(), product.getPrice(), product.getCategory()));

    MultipartFile image = product.getImage();
    System.out.println(image.getOriginalFilename());
    if (image != null && !image.isEmpty()) {
      // Convert the image to a BufferedImage
      BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

      // Create the images directory if it doesn't exist
      Path imagesDir = Paths.get("images");
      if (!Files.exists(imagesDir)) {
        Files.createDirectories(imagesDir);
      }

      // Save the image with a new name
      String newImageName = Slugify.slugify(product.getName()) + ".jpg";
      File outputFile = imagesDir.resolve(newImageName).toFile();
      ImageIO.write(bufferedImage, "jpg", outputFile);
    }

    return ResponseEntity.status(201).build();
  }

  // TODO: add patch mapping to product

  // TODO: delete product
}
