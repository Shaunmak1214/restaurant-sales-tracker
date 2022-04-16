package com.salestracker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;

public class GUI extends JFrame{  

  // set colors
  private static final Color  primaryColor = new Color(111, 45, 189);
  private static final Color secondColor = new Color(166, 99, 204);
  private static final Color backgroundColor = new Color(184, 208, 235);
  private static final Color accentColor = new Color(178, 152, 220);
  private static final Color textColor = new Color(255, 255, 255);
  private static final Color highlightColor = new Color(185, 250, 248);

  // set dimensions
  private static final int frameWidth = 1000;
  private static final int frameHeight = 600;
  private static final int centerLeftWidth = (int) Math.round(1000 * 0.65);
  private static final int centerRightWidth = (int) Math.round(1000 * 0.35);

  public GUI() {
    super("Sales Tracker");

    // set border layout 
    setLayout(new BorderLayout());
    JPanel topbar = new JPanel();
    JPanel bottombar = new JPanel();
    JPanel bottomLeftBar = new JPanel();
    JPanel bottomRightBar = new JPanel();
    JPanel center = new JPanel();
    JPanel centerLeft = new JPanel();
    JPanel centerRight = new JPanel();
    JPanel centerRightTop = new JPanel();
    JPanel[] itemsPanel = new JPanel[Drinks.getDrinks().length];

    JLabel window_label = new JLabel("Sales Tracker");
    JLabel[] drinksLabel = new JLabel[Drinks.getDrinks().length];
    JLabel discount_text = new JLabel("Enter discount code here: ");
    JLabel discount_label = new JLabel("Discount applied: ");
    JLabel cartLabel = new JLabel("Cart");
    JLabel itemsLabel = new JLabel("Items Available");

    JTextField discount_code_input = new JTextField(10);

    JButton checkout_button = new JButton("Checkout");
    JButton submit_disc_button = new JButton("Apply Discount");
    JButton[] add_to_cart_btn_arr = new JButton[Drinks.getDrinks().length];

    // set labels
    for (int i = 0; i < drinksLabel.length; i++) {
      drinksLabel[i] = new JLabel(Drinks.getDrinks()[i].getDrink_name());
    }

    window_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    window_label.setForeground(highlightColor);

    discount_text.setFont(new Font("Montserrat", Font.PLAIN, 12));
    discount_text.setForeground(textColor);

    checkout_button.setFont(new Font("Montserrat", Font.PLAIN, 12));
    checkout_button.setBackground(accentColor);

    submit_disc_button.setFont(new Font("Montserrat", Font.PLAIN, 12));
    submit_disc_button.setBackground(accentColor);

    cartLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
    cartLabel.setForeground(highlightColor);

    itemsLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
    itemsLabel.setForeground(highlightColor);

    // Panel

    topbar.setLayout(new FlowLayout());
    topbar.setBackground(primaryColor);
    topbar.add(window_label);

    center.setLayout(new BorderLayout());
    center.setBackground(backgroundColor);

    centerLeft.setLayout(new BoxLayout(centerLeft, BoxLayout.Y_AXIS));
    centerLeft.setBackground(backgroundColor);
    centerLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    centerLeft.setPreferredSize(new Dimension( centerLeftWidth, frameHeight));
    centerLeft.add(itemsLabel);

    centerRightTop.setLayout(new FlowLayout());
    centerRightTop.setBackground(backgroundColor);
    centerRightTop.setPreferredSize(new Dimension(centerRightWidth, 450));
    centerRightTop.add(cartLabel);

    centerRight.setLayout(new BoxLayout(centerRight, BoxLayout.Y_AXIS));
    centerRight.setBackground(backgroundColor);
    centerRight.setPreferredSize(new Dimension(centerRightWidth, 0));
    centerRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    centerRight.add(centerRightTop);

    for (int i = 0; i < drinksLabel.length; i++) {
      JPanel itemPanel = new JPanel();
      JButton add_to_cart_btn = new JButton("Add to cart");

      itemsPanel[i] = itemPanel;
      add_to_cart_btn_arr[i] = add_to_cart_btn;

      add_to_cart_btn_arr[i].setFont(new Font("Montserrat", Font.PLAIN, 12));
      add_to_cart_btn_arr[i].setBackground(backgroundColor);
      add_to_cart_btn_arr[i].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          Utils.log("Clicked Button, adding item %s to cart", Drinks.getDrinks()[0].getDrink_name());
          JLabel item_label = new JLabel(Drinks.getDrinks()[0].getDrink_name());
          item_label.setFont(new Font("Montserrat", Font.PLAIN, 12));
          item_label.setForeground(textColor);
          centerRight.add(item_label);
          centerRight.revalidate();
        }
      });

      drinksLabel[i].setFont(new Font("Montserrat", Font.PLAIN, 14));
      drinksLabel[i].setForeground(primaryColor);
      drinksLabel[i].setPreferredSize(new Dimension(centerLeftWidth - 80, 30));

      itemsPanel[i].setLayout(new BorderLayout());
      itemsPanel[i].setBackground(backgroundColor);
      itemsPanel[i].setVisible(true);
      itemsPanel[i].setPreferredSize(new Dimension(centerLeftWidth - 150, 50));
      itemsPanel[i].add(drinksLabel[i], BorderLayout.WEST);
      itemsPanel[i].add(add_to_cart_btn_arr[i], BorderLayout.EAST);
      
      centerLeft.add(itemsPanel[i]);
    }

    bottombar.setLayout(new BorderLayout());
    bottombar.setBackground(primaryColor);

    bottomLeftBar.setLayout(new FlowLayout());
    bottomLeftBar.setBackground(primaryColor);
    bottomLeftBar.add(discount_text);
    bottomLeftBar.add(discount_code_input);
    bottomLeftBar.add(submit_disc_button);

    bottomRightBar.setLayout(new FlowLayout());
    bottomRightBar.setBackground(primaryColor);
    bottomRightBar.add(checkout_button);

    bottombar.add(bottomLeftBar, BorderLayout.WEST);
    bottombar.add(bottomRightBar, BorderLayout.EAST);

    this.add(topbar, BorderLayout.NORTH);
    this.add(bottombar, BorderLayout.SOUTH);
    this.add(center, BorderLayout.CENTER);
    center.add(new JScrollPane(centerLeft), BorderLayout.WEST);
    center.add(new JScrollPane(centerRight), BorderLayout.EAST);

    setResizable(false);
    setSize(frameWidth, frameHeight);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] a){
    new GUI();
  }
}