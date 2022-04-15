package com.salestracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class App {
  public static void main(String[] args) {
    List<Drink> drinksList = new ArrayList<Drink>();

    Utils.spaceLine(2);
    Utils.log("Welcome to the sales tracker app %s", "John");
    Utils.log("The time now is: %s", Utils.getDateTime());
    Utils.spaceLine(1);

    JSONArray drinksJsonArr = Utils.readFromDB("Drink");
    for (Object drinkObj : drinksJsonArr) {
      JSONObject drinkJsonObj = (JSONObject) drinkObj;
      String drinkName = (String) drinkJsonObj.get("drink_name");
      String drinkType = "Drink";
      Double drinkPrice = (Double) drinkJsonObj.get("drink_price");
      int drinkDiscount = 123;
      String drinkId = (String) drinkJsonObj.get("drink_id");

      Drink drink = new Drink(drinkName, drinkType, drinkPrice, drinkDiscount, drinkId);
      drinksList.add(drink);
    }

    // // create a new drink
    // Drink drink = new Drink("Coke", "drink", 100.00, 10, UUID.randomUUID().toString());
    // Utils.log("Drink: %s", drink);
    // Utils.log("Drink name: %s", drink.getDrink_name());

    // // append to drink list
    // drinksList.add(drink);

    // // print out drink list
    // Utils.log("Drinks: %s", drinksList);

    Drink[] drinks = drinksList.toArray( new Drink[ drinksList.size() ] );
    Utils.spaceLine(1);

    // print out drinks array
    Utils.log("Drinks array: %s", drinks.length);

    // JSONArray newDrinksArr = new JSONArray();
    // JSONObject newDrinks = new JSONObject();
    // newDrinks.put("drink_id", "cotolini");
    // newDrinksArr.add(newDrinks);

    // Boolean append = Utils.appendToDB("Drinks", newDrinksArr);

    // User user1 = new User.UserBuilder("Lokesh", "Gupta", Utils.genRandomUUID())
    //   .age(30)
    //   .phone("1234567")
    //   .address("Fake address 1234")
    //   .build();

    // Discount discount = new Discount.DiscountBuilder("discount_id_1", "10%", "10", 0, 0).build();

    // Utils.log("Discount: %s", discount);
    // Utils.log("User: %s", user1);
    // Utils.log(user1.getAge());

    // String result = Utils.findFoodByFoodId("mayo");
    // Utils.log("%s", result);

    // Create a new instance of the Food class
    // Food food = new Food();
    // food.eat();
  }
}