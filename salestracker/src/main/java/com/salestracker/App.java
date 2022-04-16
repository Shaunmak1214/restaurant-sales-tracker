package com.salestracker;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class App {
  public static void main(String[] args) {
    List<Drink> drinksList = new ArrayList<Drink>();

    // Initialize cart
    Cart cart = new Cart();

    Utils.spaceLine(2);
    Utils.log("Welcome to the sales tracker app %s", "John");
    Utils.log("The time now is: %s", Utils.getDateTime());
    Utils.spaceLine(1);

    /* 
    ======================================================
    Initializing Drinks
    ====================================================== */

    // Populating drinks
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

    /* 
    ======================================================
    Initializing Discounts
    ====================================================== */
    new Discounts();

    /* 
    ======================================================
    Raw code of cart items addition
    ====================================================== */
    cart.addItems(drinks[0]);
    Utils.log("Total price %s", cart.getCart_total_price_and_net_price().first());
    Utils.log("Net price %s", cart.getCart_total_price_and_net_price().second());

    Utils.log("Discount applied: %s", cart.handleDiscount("HRF"));

    cart.addItems(drinks[1]);
    Utils.log("Total price %s", cart.getCart_total_price_and_net_price().first());
    Utils.log("Net price %s", cart.getCart_total_price_and_net_price().second());
    Item[] currentCartItems = cart.getCart_items();
    Utils.dispArray("Current cart items: ", currentCartItems);

    cart.checkout();
    
    // Boolean append = Utils.appendToDB("Drinks", newDrinksArr);

    User user1 = new User.UserBuilder("Lokesh", "Gupta", Utils.genRandomUUID())
      .age(30)
      .phone("1234567")
      .address("Fake address 1234")
      .build();

    // String result = Utils.findFoodByFoodId("mayo");
    // Utils.log("%s", result);

    // Create a new instance of the Food class
    // Food food = new Food();
    // food.eat();
  }
}