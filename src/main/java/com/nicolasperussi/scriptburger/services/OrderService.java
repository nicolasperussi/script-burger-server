package com.nicolasperussi.scriptburger.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolasperussi.scriptburger.domain.Order;
import com.nicolasperussi.scriptburger.domain.dtos.OrderByUserDTO;
import com.nicolasperussi.scriptburger.exceptions.ResourceNotFoundException;
import com.nicolasperussi.scriptburger.repositories.OrderRepository;

@Service
public class OrderService {
  @Autowired
  private OrderRepository repository;

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order with id " + id));
  }

  public List<OrderByUserDTO> findByClientId(Long clientId) {
    List<Order> orders = repository.findByClientId(clientId);
    return orders.stream().map(this::convertToOrderByUserDTO).collect(Collectors.toList());
  }

  public Order create(Order order) {
    return repository.save(order);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  private OrderByUserDTO convertToOrderByUserDTO(Order order) {
    OrderByUserDTO dto = new OrderByUserDTO();
    dto.setId(order.getId());
    dto.setItems(order.getItems());
    dto.setMoment(order.getMoment());
    dto.setStatus(order.getStatus().getCode());

    return dto;
  }
}