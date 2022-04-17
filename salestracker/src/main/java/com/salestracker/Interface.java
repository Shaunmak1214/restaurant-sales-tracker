package com.salestracker;
import java.awt.event.*;

public class Interface  {
  public Interface(Cart cart) {
    MenuFrame menu = new MenuFrame(cart);
    
    menu.setVisible(true);
    menu.checkout_button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Utils.log("Checkout button clicked");
        BillFrame bill = new BillFrame(cart);
        cart.checkout();
        bill.setVisible(true);
        menu.setVisible(false);
      }
    });
  }
}
