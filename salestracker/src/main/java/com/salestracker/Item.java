package com.salestracker;

import java.util.UUID;

abstract class Item {
  protected String item_name;
  protected String item_type;
  protected int item_price;
  protected int item_discount_amount;
  protected UUID item_discount_id;
  protected String item_id;

  Item(String item_name, String item_type, int item_price, int item_discount_amount, UUID item_discount_id, String item_id) {
    this.item_name = item_name;
    this.item_type = item_type;
    this.item_price = item_price;
    this.item_discount_amount = item_discount_amount;
    this.item_discount_id = item_discount_id;
    this.item_id = item_id;
  }

  public String getItem_name() {
    return item_name;
  }

  public String getItem_type() {
    return item_type;
  }

  public int getItem_price() {
    return item_price;
  }

  public int getItem_discount() {
    return item_discount_amount;
  }

  public String getItem_id() {
    return item_id;
  }

  @Override
  public String toString() {
    return "Item: " + this.item_id + ", " + this.item_name + ", " + this.item_type + ", " + this.item_price + ", " + this.item_discount_amount;
  }
}