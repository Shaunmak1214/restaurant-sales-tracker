package com.salestracker;

import java.util.UUID;

// create drink class extended to item
public class Drink extends Item {
  private final String drink_type;

  // constructor
  public Drink(String drink_name, String drink_type, Double drink_price, int drink_discount, String drink_id) {
    super(drink_name, "drink", drink_price, drink_discount, UUID.randomUUID(), drink_id);
    this.drink_type = drink_type;
  }

  // getter
  public String getDrink_type() {
    return drink_type;
  }

  public Double getDrink_price() {
    return getItem_price();
  }

  public int getDrink_discount() {
    return getItem_discount();
  }

  public String getDrink_id() {
    return getItem_id();
  }

  public String getDrink_name(){
    return getItem_name();
  }

  public String getItem_type () {
    return "Drink";
  }

  @Override
  public String toString() {
    return "Drink: " + getDrink_name();
  }
}