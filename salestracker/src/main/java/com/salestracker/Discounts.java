package com.salestracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Discounts {
  private static List<Discount> discountsList = new ArrayList<Discount>();

  public Discounts() {
    JSONArray discountsJsonArr = Utils.readFromDB("Discount");
    for (Object discountObj : discountsJsonArr) {
      JSONObject discountJsonObj = (JSONObject) discountObj;
      if((Boolean) discountJsonObj.get("is_active")){
        UUID disc_id = UUID.fromString(discountJsonObj.get("disc_id").toString());
        String disc_name = (String) discountJsonObj.get("disc_name");
        String disc_type = (String) discountJsonObj.get("disc_type");
        
        int disc_food_value = Integer.valueOf(discountJsonObj.get("disc_food_value").toString());
        int disc_drink_value = Integer.valueOf(discountJsonObj.get("disc_drink_value").toString());
        String disc_code = (String) discountJsonObj.get("disc_code");
  
        Discount discount = new Discount.DiscountBuilder(disc_id, disc_name, disc_type,disc_code, disc_food_value, disc_drink_value).build();
        discountsList.add(discount);
      }
    }
    Discount[] discounts = discountsList.toArray( new Discount[ discountsList.size() ] );
    Utils.spaceLine(1);
    Utils.dispArray("These are the discounts: ", discounts);
  }

  public static Discount[] getDiscounts() {
    Discount[] discounts = discountsList.toArray( new Discount[ discountsList.size() ] );
    return discounts;
  }
}
