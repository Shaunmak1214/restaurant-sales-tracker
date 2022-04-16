package com.salestracker;

public class App {
  public static void main(String[] args) {
    Utils.welcome();

    Cart cart = new Cart();

    new Drinks();
    new Discounts();
    new GUI();

    /* 
    ======================================================
    Raw code of cart items addition
    ====================================================== */
    cart.addItems(Drinks.getDrinks()[0]);
    Utils.log("Total price %s", cart.getCart_total_price_and_net_price().first());
    Utils.log("Net price %s", cart.getCart_total_price_and_net_price().second());

    Utils.log("Discount applied: %s", cart.handleDiscount("HRF"));

    cart.addItems(Drinks.getDrinks()[1]);
    Utils.log("Total price %s", cart.getCart_total_price_and_net_price().first());
    Utils.log("Net price %s", cart.getCart_total_price_and_net_price().second());
    Item[] currentCartItems = cart.getCart_items();
    Utils.dispArray("Current cart items: ", currentCartItems);

    cart.checkout();
    
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