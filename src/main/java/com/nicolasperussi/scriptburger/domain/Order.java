package com.nicolasperussi.scriptburger.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nicolasperussi.scriptburger.domain.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant moment;
  private Integer status;

  @ManyToOne
  @JoinColumn(name = "client_id")
  @JsonIgnoreProperties("orders")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "courier_id")
  @JsonIgnoreProperties("orders")
  private Courier courier;

  @OneToMany(mappedBy = "id.order")
  private Set<OrderItem> items = new HashSet<>();

  // Assign address to order
  private Address deliveryAddress;

  public Order() {
  }

  public Order(Instant moment, OrderStatus status, Client client) {
    this.moment = moment;
    setStatus(status);
    this.client = client;
  }

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

  public void setStatus(OrderStatus status) {
    if (status != null) {
      this.status = status.getCode();
    }
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Courier getCourier() {
    return courier;
  }

  public void setCourier(Courier courier) {
    this.courier = courier;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public void addItem(OrderItem item) {
    item.setOrder(this);
    this.getItems().add(item);
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", moment=" + moment + ", status=" + status + ", client=" + client + ", items=" + items
        + "]";
  }

}
