package com.nicolasperussi.scriptburger.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends User {
    @ElementCollection
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.getAddresses().add(address);
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        getOrders().add(order);
    }

}
