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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/products", produces = { "application/json" })
@Tag(name = "Products")
public class ProductController {
  @Autowired
  private ProductService service;

  @GetMapping
  @Operation(summary = "Fetch all products", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Products were fetched."),
      @ApiResponse(responseCode = "204", description = "No products were found.", content = @Content)
  })
  public ResponseEntity<List<Product>> findAll() {
    List<Product> list = service.findAll();

    if (list.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  @Operation(summary = "Fetch product by ID", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Product found and fetched."),
      @ApiResponse(responseCode = "404", description = "Product not found.", content = @Content)
  })
  public ResponseEntity<Product> findById(@NonNull @PathVariable Long id) {
    Product product = service.findById(id);

    return ResponseEntity.ok().body(product);
  }

  @GetMapping("/sandwiches")
  @Operation(summary = "Fetch all sandwiches", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Sandwiches found and fetched."),
      @ApiResponse(responseCode = "204", description = "Sandwiches not found.", content = @Content)
  })
  public ResponseEntity<List<Product>> findAllSandwiches() {
    List<Product> sandwiches = service.findAllSandwiches();

    return ResponseEntity.ok().body(sandwiches);
  }

  @GetMapping("/sides")
  @Operation(summary = "Fetch all sides", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Sides found and fetched."),
      @ApiResponse(responseCode = "204", description = "Sides not found.", content = @Content)
  })
  public ResponseEntity<List<Product>> findAllSides() {
    List<Product> sides = service.findAllSides();

    return ResponseEntity.ok().body(sides);
  }

  @GetMapping("/desserts")
  @Operation(summary = "Fetch all desserts", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Desserts found and fetched."),
      @ApiResponse(responseCode = "204", description = "Desserts not found.", content = @Content)
  })
  public ResponseEntity<List<Product>> findAllDesserts() {
    List<Product> desserts = service.findAllDesserts();

    return ResponseEntity.ok().body(desserts);
  }

  @GetMapping("/drinks")
  @Operation(summary = "Fetch all drinks", method = "GET")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Drinks found and fetched."),
      @ApiResponse(responseCode = "204", description = "Drinks not found.", content = @Content)
  })
  public ResponseEntity<List<Product>> findAllDrinks() {
    List<Product> drinks = service.findAllDrinks();

    return ResponseEntity.ok().body(drinks);
  }

  @PostMapping
  @Operation(summary = "Create new product", method = "POST")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Product created successfully."),
  })
  public ResponseEntity<Product> createProduct(@Valid @ModelAttribute ProductDTO product) throws IOException {
    service.create(new Product(product.getName(), product.getDescription(),
        Slugify.slugify(product.getName()), product.getOverview(), product.getPrice(), product.getCategory()));

    MultipartFile image = product.getImage();
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
