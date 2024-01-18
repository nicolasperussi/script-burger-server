package com.nicolasperussi.scriptburger.domain.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.nicolasperussi.scriptburger.domain.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductDTO {

  @NotBlank(message = "The product must have a name")
  private String name;
  @NotBlank(message = "The product must have a description")
  private String description;

  @NotBlank(message = "The product must have an overview")
  private String overview;

  @NotNull(message = "The product must have a price")
  @Positive(message = "The price must be greater than zero")
  private double price;

  @NotNull(message = "The product must have a category")
  private Category category;

  @NotNull(message = "The product must have an image")
  private MultipartFile image;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getOverview() {
    return overview;
  }

  public double getPrice() {
    return price;
  }

  public Category getCategory() {
    return category;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

}
