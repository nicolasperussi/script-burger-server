package com.nicolasperussi.scriptburger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolasperussi.scriptburger.domain.OrderItem;
import com.nicolasperussi.scriptburger.exceptions.ResourceNotFoundException;
import com.nicolasperussi.scriptburger.repositories.OrderItemRepository;

@Service
public class OrderItemService {
  @Autowired
  private OrderItemRepository repository;

  public List<OrderItem> findAll() {
    return repository.findAll();
  }

  public OrderItem findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order item with id " + id));
  }

  public OrderItem create(OrderItem orderItem) {
    return repository.save(orderItem);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
