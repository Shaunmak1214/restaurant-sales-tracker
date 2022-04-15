package com.salestracker;

import java.util.UUID;

public class Food extends Item {
  private final String food_type;
  private final int food_price;
  private final int food_discount;
  private final String food_id;

  // constructor
  public Food(String food_name, String food_type, int food_price, int food_discount, String food_id) {
    super(food_name, "food", food_price, food_discount, UUID.randomUUID(), food_id);
    this.food_type = food_type;
    this.food_price = food_price;
    this.food_discount = food_discount;
    this.food_id = food_id;
  }

  // getter
  public String getFood_type() {
    return food_type;
  }

  public int getFood_price() {
    return food_price;
  }

  public int getFood_discount() {
    return food_discount;
  }

  public String getFood_id() {
    return food_id;
  }

  @Override
  public String toString() {
    return "Food: " + this.food_id + ", " + this.food_type + ", " + this.food_price + ", " + this.food_discount;
  }
}