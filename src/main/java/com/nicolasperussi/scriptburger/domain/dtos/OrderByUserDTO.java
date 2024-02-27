package com.nicolasperussi.scriptburger.domain.dtos;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nicolasperussi.scriptburger.domain.Address;
import com.nicolasperussi.scriptburger.domain.Courier;
import com.nicolasperussi.scriptburger.domain.OrderItem;
import com.nicolasperussi.scriptburger.domain.enums.OrderStatus;

public class OrderByUserDTO {
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant moment;
  private Integer status;
  private Set<OrderItem> items = new HashSet<>();

  @JsonIgnoreProperties("orders")
  private Courier courier;
  
  private Address deliveryAddress;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getMoment() {
    return moment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public OrderStatus getStatus() {
    return OrderStatus.valueOf(status);
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public void setItems(Set<OrderItem> items) {
    this.items = items;
  }

  public Courier getCourier() {
    return courier;
  }

  public void setCourier(Courier courier) {
    this.courier = courier;
  }

  public Address getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(Address deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public BigDecimal getTotalPrice() {
    BigDecimal totalPrice = new BigDecimal(0);

    for (OrderItem item : this.getItems()) {
      totalPrice = totalPrice.add(item.getTotalPrice());
    }

    return totalPrice;
  }

}
