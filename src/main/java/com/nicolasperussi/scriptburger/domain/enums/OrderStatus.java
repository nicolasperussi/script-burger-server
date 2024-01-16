package com.nicolasperussi.scriptburger.domain.enums;

public enum OrderStatus {
  WAITING(1),
  IN_PRODUCTION(2),
  IN_TRANSIT(3),
  DELIVERED(4);

  private int code;

  OrderStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static OrderStatus valueOf(int code) {
    for (OrderStatus value : OrderStatus.values()) {
      if (value.getCode() == code) {
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid OrderStatus code");
  }

}
