package com.salestracker;

import java.util.UUID;

public class Sales {
  private final UUID sale_id;
  private final int sale_total_price;
  private final int sale_total_discount;
  private final Item sale_items[];

  private Sales (SalesBuilder builder){
    this.sale_id = builder.sale_id;
    this.sale_total_price = builder.sale_total_price;
    this.sale_total_discount = builder.sale_total_discount;
    this.sale_items = builder.sale_items;
  }

  public static class SalesBuilder {
    private UUID sale_id;
    private int sale_total_price;
    private int sale_total_discount;
    private Item sale_items[];

    public SalesBuilder() {
      this.sale_id = UUID.randomUUID();
      this.sale_total_price = 0;
      this.sale_total_discount = 0;
      this.sale_items = new Item[0];
    }

    public SalesBuilder addItem(int item_price, int item_discount_amount, Item[] sale_items) {
      this.sale_total_price += item_price;
      this.sale_total_discount += item_discount_amount;
      this.sale_items = sale_items;
      return this;
    }

    public Sales build() {
      return new Sales(this);
    }
  }
}