package com.salestracker;

public class App {
  public static void main(String[] args) {
    Utils.welcome();

    Cart cart = new Cart();

    new Drinks();
    new Foods();
    new Discounts();
    new Interface(cart);
  }
}