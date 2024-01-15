package com.nicolasperussi.scriptburger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.domain.enums.Category;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategory(Category category);

}
