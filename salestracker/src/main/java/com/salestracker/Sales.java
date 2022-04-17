package com.salestracker;

import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Sales {
  private final UUID sale_id;
  private final Double sale_total_price;
  private final Double sale_net_price;
  private final String disc_code;
  private final Discount discount_applied;
  private final Item sale_items[];

  private Sales (SalesBuilder builder){
    this.sale_id = builder.sale_id;
    this.sale_total_price = builder.sale_total_price;
    this.sale_net_price = builder.sale_net_price;
    this.sale_items = builder.sale_items;
    this.disc_code = builder.disc_code;
    this.discount_applied = builder.discount_applied;
  }

  //All getter, and NO setter to provide immutability
  public UUID getSale_id() {
    return sale_id;
  }

  public Double getSale_total_price() {
    return sale_total_price;
  }

  public Double getSale_net_price() {
    return sale_net_price;
  }

  public String getDisc_code() {
    return disc_code;
  }

  public Discount getDiscount_applied() {
    return discount_applied;
  }

  public Item[] getSale_items() {
    return sale_items;
  }

  @Override
  public String toString() {
    
    return "Sales: " + this.sale_id+", " +this.sale_total_price+", "+this.sale_net_price+", "+this.sale_items+", "+this.disc_code+", "+this.discount_applied;
  }


  public static class SalesBuilder {
    private UUID sale_id;
    private Double sale_total_price;
    private Double sale_net_price;
    private Item sale_items[];
    private String disc_code;
    private Discount discount_applied;

    public SalesBuilder(Double total_price, Double net_price, Item items[], String disc_code, Discount discount_applied) {
      this.sale_id = UUID.randomUUID();
      this.sale_total_price = total_price;
      this.sale_net_price = net_price;
      this.sale_items = items;
      this.disc_code = disc_code;
      this.discount_applied = discount_applied;
    }

    public Sales build() {
      Sales sale = new Sales(this);
      
      // write to sales db
      Utils.log("Writing to sales db");
      JSONObject salesObj = new JSONObject();
      JSONArray itemsArr = new JSONArray();
      for (Item item : sale.sale_items) {
        JSONObject itemObj = new JSONObject();
        String item_id=  item.getItem_id().toString();
        String item_name = item.getItem_name().toString();
        itemObj.put("item_id", item_id);
        itemObj.put("item_name", item_name);
        itemObj.put("item_price", item.getItem_price());

        itemsArr.add(itemObj);
      }
      // stringify the items into json array
      String sale_id_str  = sale.sale_id.toString(); 

      String disc_applied= "";
      // check if discount is used
      if (sale.discount_applied != null) {
        disc_applied= this.discount_applied.getDisc_id().toString();
      }
      salesObj.put("sale_id",  sale_id_str);
      salesObj.put("sale_total_price", this.sale_total_price);
      salesObj.put("sale_net_price", this.sale_net_price);
      salesObj.put("disc_code", this.disc_code);
      salesObj.put("discount_applied", disc_applied);
      salesObj.put("sale_items", itemsArr);

      JSONArray salesArr = new JSONArray();
      salesArr.add(salesObj);
      Utils.appendToDB("Sales", salesArr);

      return sale;
    }
  }
}