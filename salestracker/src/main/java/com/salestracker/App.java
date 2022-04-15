package com.salestracker;

import java.util.UUID;

public class App {
  public static void main(String[] args) {
    Utils.spaceLine(2);
    Utils.log("Welcome to the sales tracker app %s", "John");
    Utils.log("The time now is: %s", Utils.getDateTime());
    Utils.spaceLine(1);

    // create a new drink
    Drink drink = new Drink("Coke", "drink", 100, 10, UUID.randomUUID().toString());
    Utils.log("Drink: %s", drink);

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