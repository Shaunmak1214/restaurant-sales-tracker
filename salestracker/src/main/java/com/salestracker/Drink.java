package com.salestracker;

import java.util.UUID;

// create drink class extended to item
public class Drink extends Item {
  private final String drink_type;
  private final int drink_price;
  private final int drink_discount;
  private final String drink_id;

  // constructor
  public Drink(String drink_name, String drink_type, int drink_price, int drink_discount, String drink_id) {
    super(drink_name, "drink", drink_price, drink_discount, UUID.randomUUID(), drink_id);
    this.drink_type = drink_type;
    this.drink_price = drink_price;
    this.drink_discount = drink_discount;
    this.drink_id = drink_id;
  }

  // getter
  public String getDrink_type() {
    return drink_type;
  }

  public int getDrink_price() {
    return drink_price;
  }

  public int getDrink_discount() {
    return drink_discount;
  }

  public String getDrink_id() {
    return drink_id;
  }

  @Override
  public String toString() {
    return "Drink: " + this.drink_id + ", " + this.drink_type + ", " + this.drink_price + ", " + this.drink_discount;
  }
}