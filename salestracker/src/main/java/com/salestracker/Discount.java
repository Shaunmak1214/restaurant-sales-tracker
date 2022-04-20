package com.salestracker;

import java.util.UUID;

public class Discount {
  private final UUID disc_id;
  private final String disc_name;
  private final String disc_type;
  private final Boolean is_active;
  private final int disc_food_value;
  private final int disc_drink_value;
  private final String disc_code;

  private Discount(DiscountBuilder builder) {
    this.disc_id = builder.disc_id;
    this.disc_name = builder.disc_name;
    this.disc_type = builder.disc_type;
    this.is_active = builder.is_active;
    this.disc_code = builder.disc_code;
    this.disc_food_value = builder.disc_food_value;
    this.disc_drink_value = builder.disc_drink_value;
  }

  //All getter, and NO setter to provide immutability
  public UUID getDisc_id() {
    return disc_id;
  }

  public String getDisc_name() {
    return disc_name;
  }

  public String getDisc_type() {
    return disc_type;
  }

  public Boolean getIs_active() {
    return is_active;
  }

  public String getDisc_code() {
    return disc_code;
  }

  public int getDisc_food_value() {
    return disc_food_value;
  }

  public int getDisc_drink_value() {
    return disc_drink_value;
  }

  @Override
  public String toString() {
    return "Discount: " + this.disc_id + ", " + this.disc_name + ", " + this.disc_type + ", " + this.is_active + ", " + this.disc_code + ", " + this.disc_food_value + ", " + this.disc_drink_value;
  }

  public static class DiscountBuilder 
  {
    private final UUID disc_id;
    private final String disc_name;
    private final String disc_type;
    private final Boolean is_active;
    private final String disc_code;
    private final int disc_food_value;
    private final int disc_drink_value;

    public DiscountBuilder(UUID disc_id, String disc_name, String disc_type, String disc_code, int disc_food_value, int disc_drink_value) {
      this.disc_id = disc_id;
      this.disc_name = disc_name;
      this.disc_type = disc_type;
      this.disc_code = disc_code;
      this.is_active = true;
      this.disc_food_value = disc_food_value;
      this.disc_drink_value = disc_drink_value;
    }

    public Discount build() {
      Discount discount = new Discount(this);

      return discount;
    }
  }
}
