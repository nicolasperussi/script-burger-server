package com.nicolasperussi.scriptburger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.domain.enums.Category;
import com.nicolasperussi.scriptburger.exceptions.ResourceNotFoundException;
import com.nicolasperussi.scriptburger.repositories.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(@NonNull Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Couldn't find product with id " + id));
  }

  public List<Product> findAllSandwiches() {
    return repository.findByCategory(Category.SANDWICH);
  }

  public List<Product> findAllDrinks() {
    return repository.findByCategory(Category.DRINK);
  }

  public List<Product> findAllDesserts() {
    return repository.findByCategory(Category.DESSERT);
  }

  public List<Product> findAllSides() {
    return repository.findByCategory(Category.SIDE);
  }

  public Product create(@NonNull Product product) {
    return repository.save(product);
  }

  public void delete(@NonNull Long id) {
    repository.deleteById(id);
  }

}
