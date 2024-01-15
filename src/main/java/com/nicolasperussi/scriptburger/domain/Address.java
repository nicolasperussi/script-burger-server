package com.nicolasperussi.scriptburger.domain;

import java.io.Serializable;

public class Address implements Serializable {
  private String cep;
  private String street;
  private String number;

  public Address() {
  }

  public Address(String cep, String street, String number) {
    this.cep = cep;
    this.street = street;
    this.number = number;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

}
