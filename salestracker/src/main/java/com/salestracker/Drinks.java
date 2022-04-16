package com.salestracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Drinks {
 public static List<Drink> drinksList = new ArrayList<Drink>();

  public Drinks() {
    JSONArray drinksJsonArr = Utils.readFromDB("Drink");
    for (Object drinkObj : drinksJsonArr) {
      JSONObject drinkJsonObj = (JSONObject) drinkObj;
      String drinkName = (String) drinkJsonObj.get("drink_name");
      String drinkType = "Drink";
      Double drinkPrice = (Double) drinkJsonObj.get("drink_price");
      int drinkDiscount = 0;
      String drinkId = (String) drinkJsonObj.get("drink_id");

      Drink drink = new Drink(drinkName, drinkType, drinkPrice, drinkDiscount, drinkId);
      drinksList.add(drink);
    }

    Drink[] drinks = drinksList.toArray( new Drink[ drinksList.size() ] );
    Utils.spaceLine(1);
    Utils.dispArray("These are the drinks: ", drinks);
    for (Drink drink : drinks) {
      Utils.log("Drink price: %s", drink.getDrink_price());
    }
  }

  public static Drink[] getDrinks() {
    return drinksList.toArray( new Drink[ drinksList.size() ] );
  }
}
