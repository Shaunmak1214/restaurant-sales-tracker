package com.salestracker;

import java.util.UUID;

public class Food extends Item {
  private final String food_type;

  // constructor
  public Food(String food_name, String food_type, Double food_price, int food_discount, String food_id) {
    super(food_name, "food", food_price, food_discount, UUID.randomUUID(), food_id);
    this.food_type = food_type;
  }

  // getter
  public String getFood_name() {
    return getItem_name();
  }

  public String getFood_type() {
    return food_type;
  }

  public Double getFood_price() {
    return getItem_price();
  }

  public int getFood_discount() {
    return getItem_discount();
  }

  public String getFood_id() {
    return getItem_id();
  }

  @Override
  public String toString() {
    return "Food: " + getFood_name() + "added";
  }
}