package com.nicolasperussi.scriptburger.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasperussi.scriptburger.domain.Client;
import com.nicolasperussi.scriptburger.domain.Order;
import com.nicolasperussi.scriptburger.domain.OrderItem;
import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.domain.dtos.OrderByUserDTO;
import com.nicolasperussi.scriptburger.domain.dtos.OrderDTO;
import com.nicolasperussi.scriptburger.domain.dtos.OrderItemDTO;
import com.nicolasperussi.scriptburger.domain.enums.OrderStatus;
import com.nicolasperussi.scriptburger.repositories.OrderItemRepository;
import com.nicolasperussi.scriptburger.services.OrderService;
import com.nicolasperussi.scriptburger.services.ProductService;
import com.nicolasperussi.scriptburger.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
  @Autowired
  private OrderService service;

  @Autowired
  private UserService userService;
  @Autowired
  private ProductService productService;
  @Autowired
  private OrderItemRepository orderItemRepository;

  @GetMapping
  public ResponseEntity<List<Order>> findAll() {
    List<Order> list = service.findAll();
    if (list.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/user/{clientId}")
  public ResponseEntity<List<OrderByUserDTO>> findByClientId(@NonNull @PathVariable Long clientId) {
    List<OrderByUserDTO> orders = service.findByClientId(clientId);

    if (orders.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return ResponseEntity.ok().body(orders);
  }

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody OrderDTO order) {
    Order newOrder = new Order();
    newOrder.setMoment(Instant.now());
    newOrder.setStatus(OrderStatus.WAITING);
    Client client = (Client) userService.findById(order.getUserId());
    newOrder.setClient(client);

    newOrder.setDeliveryAddress(order.getAddress());

    service.create(newOrder);

    for (OrderItemDTO item : order.getItems()) {
      Product product = productService.findById(item.getProductId());
      OrderItem orderItem = new OrderItem(newOrder, product, item.getQuantity());
      orderItemRepository.save(orderItem);
    }

    return ResponseEntity.status(201).build();
  }

  @PatchMapping(value = "/{orderId}")
  public ResponseEntity<Order> nextOrderStep(@PathVariable Long orderId) {
    return ResponseEntity.ok().body(service.nextOrderStatus(orderId));
  }

  @PatchMapping(value = "/cancel/{orderId}")
  public ResponseEntity<Order> cancel(@PathVariable Long orderId) {
    return ResponseEntity.ok().body(service.cancelOrder(orderId));
  }

  @PatchMapping(value = "/{orderId}/courier/{courierId}")
  public ResponseEntity<Order> assignCourier(@PathVariable Long orderId, @PathVariable Long courierId) {
    return ResponseEntity.ok().body(service.assignCourier(orderId, courierId));
  }
}
