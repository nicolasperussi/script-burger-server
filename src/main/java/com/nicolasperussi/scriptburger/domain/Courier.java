package com.nicolasperussi.scriptburger.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Courier extends User {
    private String licensePlate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Courier() {}

    public Courier(String name, String email, String phone, String password, String licensePlate) {
        super(name, email, phone, password);
        this.licensePlate = licensePlate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        getOrders().add(order);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
