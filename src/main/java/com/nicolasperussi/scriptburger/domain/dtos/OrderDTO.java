package com.nicolasperussi.scriptburger.domain.dtos;

public class OrderDTO {
  private Long userId;
  private OrderItemDTO[] items;

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

}
