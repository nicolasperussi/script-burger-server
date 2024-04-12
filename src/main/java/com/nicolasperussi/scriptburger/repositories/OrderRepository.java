package com.nicolasperussi.scriptburger.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nicolasperussi.scriptburger.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByClientId(Long clientId, Sort sort);
}
