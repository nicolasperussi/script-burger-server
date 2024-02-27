package com.nicolasperussi.scriptburger.domain.dtos;

import com.nicolasperussi.scriptburger.domain.Address;

public class OrderDTO {
  private Long userId;
  private OrderItemDTO[] items;
  private Address address;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public OrderItemDTO[] getItems() {
    return items;
  }

  public void setItems(OrderItemDTO[] items) {
    this.items = items;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
