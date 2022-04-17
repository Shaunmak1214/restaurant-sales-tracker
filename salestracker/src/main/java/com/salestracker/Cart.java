package com.salestracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
  private final String cart_id;
  private List<Item> cart_items;
  private String cart_disc_code;
  private Discount cart_discount_applied;
  private Double cart_total_price;
  private Double cart_net_price;

  public Cart() {
    this.cart_id = UUID.randomUUID().toString();
    this.cart_items = new ArrayList<Item>();
    this.cart_disc_code = "";
    this.cart_discount_applied = null;
    this.cart_total_price = 0.0;
    this.cart_net_price = 0.0;
  }

  public String getCart_id() {
    return cart_id;
  }

  public Item[] getCart_items() {
    Item[] itemsArr = cart_items.toArray(new Item[ cart_items.size() ]);
    return itemsArr;
  }

  public String getCart_disc_code() {
    return cart_disc_code;
  }

  public Discount getCart_discount_applied() {
    return cart_discount_applied;
  }

  public Double getCart_total_price() {
    return cart_total_price;
  }

  public Double getCart_net_price() {
    return cart_net_price;
  }

  public Pair<Double, Double> getCart_total_price_and_net_price() {
    return new Pair<Double, Double>(cart_total_price, cart_net_price);
  }

  // Setters
  public void addItems(Item item) {
    cart_items.add(item);

    calculateTotalPrice();
  }

  public void removeItem(String item_id) {
    for (Item item : cart_items) {
      if (item.getItem_id().equals(item_id)) {
        cart_items.remove(item);
        break;
      }
    }
    
    calculateTotalPrice();
  }

  public Boolean handleDiscount (String discount_id) {
    this.cart_disc_code = discount_id;

    // Get discount list
    Discount[] discounts = Discounts.getDiscounts();
    for (Discount discount : discounts) {
      if (discount.getDisc_code().toString().equals(discount_id)) {
        this.cart_discount_applied = discount;
        Utils.log("Discount code %s applied", discount_id);

        // Calculate total price
        calculateTotalPrice();
        return true;
      }
    }

    Utils.log("Discount code %s not found", discount_id);
    return false;
  }

  public Pair<Double, Double> calculateTotalPrice() {
    Double totalDiscount = 0.0;
    Double totalNet = 0.0;
    if (cart_discount_applied != null) {
      if(cart_discount_applied.getDisc_type().equals("Food")) {
        for (Item item : cart_items) {
          if (item.getItem_type().equalsIgnoreCase("Food")) {
            totalDiscount += item.getItem_price() * (1 - (cart_discount_applied.getDisc_food_value() / 100.0));
            totalNet += item.getItem_price();
          }else {
            totalNet += item.getItem_price();
            totalDiscount += item.getItem_price();
          }
        }
      } else if (cart_discount_applied.getDisc_type().equals("Drink")) {
        for (Item item : cart_items) {
          if (item.getItem_type().equalsIgnoreCase("Drink")) {
            totalDiscount += item.getItem_price() * (1- (cart_discount_applied.getDisc_drink_value() / 100.0));
            totalNet += item.getItem_price();
          }else {
            totalNet += item.getItem_price();
            totalDiscount += item.getItem_price();
          }
        }
      } else if (cart_discount_applied.getDisc_type().equals("All")) {
        for (Item item : cart_items) {
          if (item.getItem_type().equalsIgnoreCase("Food")) {
            totalDiscount += item.getItem_price() * (1-(cart_discount_applied.getDisc_food_value() / 100.0));
            
          } else if (item.getItem_type().equalsIgnoreCase("Drink")) {
            totalDiscount += item.getItem_price() * (1-(cart_discount_applied.getDisc_drink_value() / 100.0));
          }

          totalNet += item.getItem_price();
        }
      }
    }else {
      for (Item item : cart_items) {
        totalNet += item.getItem_price();
        totalDiscount += item.getItem_price();
      }
    }

    this.cart_total_price = totalDiscount;
    this.cart_net_price = totalNet;

    // create pair
    Pair<Double, Double> pair = new Pair<Double, Double>(cart_total_price, cart_net_price);

    return pair;
  }

  public Sales checkout() {
    Item[] itemsArr = cart_items.toArray(new Item[ cart_items.size() ]);
    Sales sales = new Sales.SalesBuilder(this.cart_total_price, this.cart_net_price, itemsArr, this.cart_disc_code, this.cart_discount_applied).build();

    return sales;
  }

  public void clearCart() {
    this.cart_items.clear();
    this.cart_disc_code = "";
    this.cart_discount_applied = null;
    this.cart_total_price = 0.0;
    this.cart_net_price = 0.0;
  }

  @Override
  public String toString() {
    return "Cart " + this.cart_id + " initialized";
  }
}
