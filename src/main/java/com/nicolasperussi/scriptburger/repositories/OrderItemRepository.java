package com.nicolasperussi.scriptburger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicolasperussi.scriptburger.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
