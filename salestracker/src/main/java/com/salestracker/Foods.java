package com.salestracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Foods {
  public static List<Food> foodList = new ArrayList<Food>();

  public Foods() {
    JSONArray foodJsonArray = Utils.readFromDB("Food");
    for (Object foodObj : foodJsonArray) {
      JSONObject foodJsonObj = (JSONObject) foodObj;
      String foodName = (String) foodJsonObj.get("food_name");
      String foodType = "Drink";
      Double foodPrice = (Double) foodJsonObj.get("food_price");
      int foodDiscount = 0;
      String foodId = (String) foodJsonObj.get("food_id");

      Food food = new Food(foodName, foodType, foodPrice, foodDiscount, foodId);
      foodList.add(food);
    }

    Food[] foods = foodList.toArray( new Food[ foodList.size() ] );
    Utils.spaceLine(1);
    Utils.dispArray("These are the foods: ", foods);
    for (Food food : foods) {
      Utils.log("Drink price: %s", food.getFood_price());
    }
  }

  public static Food[] getFoods() {
    return foodList.toArray( new Food[ foodList.size() ] );
  }
}
