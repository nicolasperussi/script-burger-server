package com.nicolasperussi.scriptburger.domain.enums;

public enum Category {
  SANDWICH("sandwich"),
  SIDE("side"),
  DESSERT("dessert"),
  DRINK("drink");

  private String category;

  Category(String category) {
    this.category = category;
  }

  public String getCategory() {
    return category;
  }
}
