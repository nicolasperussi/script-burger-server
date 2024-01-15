package com.nicolasperussi.scriptburger.domain;

import com.nicolasperussi.scriptburger.domain.enums.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Lob
  private String description;
  private String slug;
  private String overview;
  private double price;
  private Category category;

  public Product() {
  }

  public Product(String name, String description, String slug, String overview, double price, Category category) {
    this.name = name;
    this.description = description;
    this.slug = slug;
    this.overview = overview;
    this.price = price;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getSlug() {
    return slug;
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setSlug(String slug) {
    this.slug = slug;
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

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", description=" + description + ", slug=" + slug + ", overview="
        + overview + ", price=" + price + ", category=" + category + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
