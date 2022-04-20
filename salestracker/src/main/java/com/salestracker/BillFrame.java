package com.salestracker;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BillFrame extends JFrame {
  // set colors
  private static final Color primaryColor = new Color(111, 45, 189);
  private static final Color backgroundColor = new Color(184, 208, 235);
  private static final Color accentColor = new Color(178, 152, 220);
  private static final Color textColor = new Color(255, 255, 255);
  private static final Color highlightColor = new Color(185, 250, 248);

  // set dimensions
  private static final int frameWidth = 1000;
  private static final int frameHeight = 600;
  private static final int centerLeftWidth = (int) Math.round(1000 * 0.65);
  private static final int centerRightWidth = (int) Math.round(1000 * 0.35);

  private Cart cart = null;

  JPanel topbar = new JPanel();
  JPanel center = new JPanel();
  JPanel tablePanel = new JPanel();
  JPanel bottomBar = new JPanel();

  JLabel window_label = new JLabel("Billing - Western Wizards");
  JLabel total_price_label = new JLabel("Total Price: ");
  JLabel total_price_value = new JLabel("0.00");
  JLabel net_price_label = new JLabel("Net Price: ");
  JLabel net_price_value = new JLabel("0.00");
  JLabel discount_code_label = new JLabel("Discount Code: ");
  JLabel discount_code_value = new JLabel("");
  JLabel discount_applied_label = new JLabel("Discount Applied: ");
  JLabel discount_applied_value = new JLabel("");

  public BillFrame(Cart cart) {
    super("Western Wizards Billing");
    this.cart = cart;
    setLayout(new BorderLayout());

    Item[] cart_items = cart.getCart_items();
    String cart_disc_code = cart.getCart_disc_code();
    Discount cart_discount_applied = cart.getCart_discount_applied();
    String cart_discount_name = "";
    Double cart_total_price = cart.getCart_total_price();
    Double cart_net_price = cart.getCart_net_price();

    if( cart_discount_applied != null ) {
      cart_discount_name = cart.getCart_discount_applied().getDisc_name();
    }

    // Labels
    window_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    window_label.setForeground(highlightColor);

    total_price_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    total_price_label.setForeground(textColor);
    total_price_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    total_price_value.setFont(new Font("Montserrat", Font.BOLD, 20));
    total_price_value.setForeground(textColor);
    total_price_value.setText(NumberFormat.getCurrencyInstance().format(cart_total_price));
    total_price_value.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    net_price_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    net_price_label.setForeground(textColor);
    net_price_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    net_price_value.setFont(new Font("Montserrat", Font.BOLD, 20));
    net_price_value.setForeground(textColor);
    net_price_value.setText(NumberFormat.getCurrencyInstance().format(cart_net_price));
    net_price_value.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    discount_code_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    discount_code_label.setForeground(textColor);
    discount_code_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    discount_code_value.setFont(new Font("Montserrat", Font.BOLD, 20));
    discount_code_value.setForeground(textColor);
    discount_code_value.setText(cart_disc_code);
    discount_code_value.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    discount_applied_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    discount_applied_label.setForeground(textColor);
    discount_applied_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    discount_applied_value.setFont(new Font("Montserrat", Font.BOLD, 20));
    discount_applied_value.setForeground(textColor);
    discount_applied_value.setText(cart_discount_name);
    discount_applied_value.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    // Panel
    topbar.setLayout(new FlowLayout());
    topbar.setBackground(primaryColor);
    topbar.add(window_label);
    topbar.setVisible(true);

    center.setLayout(new BorderLayout());
    center.setBackground(backgroundColor);

    bottomBar.setLayout(new BoxLayout(bottomBar, BoxLayout.Y_AXIS));
    bottomBar.setBackground(primaryColor);
    bottomBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    bottomBar.add(total_price_label);
    bottomBar.add(total_price_value);
    bottomBar.add(net_price_label);
    bottomBar.add(net_price_value);
    bottomBar.add(discount_code_label);
    bottomBar.add(discount_code_value);
    bottomBar.add(discount_applied_label);
    bottomBar.add(discount_applied_value);
    

    String[] columnNames = {"Item Name", "Price"};
    
    Object[][] data = new Object[cart_items.length][3];

    for (int i = 0; i < cart_items.length; i++) {
      data[i][0] = cart_items[i].getItem_name();
      data[i][1] = cart_items[i].getItem_price();
    }

    JTable table = new JTable(data, columnNames);

    table.setPreferredScrollableViewportSize(new Dimension(centerLeftWidth, frameHeight));
    table.setFillsViewportHeight(true);
    table.setFont(new Font("Inter", Font.PLAIN, 14));
    JScrollPane scrollpane = new JScrollPane(table);

    tablePanel.setLayout(new BorderLayout());
    tablePanel.add(scrollpane, BorderLayout.CENTER);

    tablePanel.setVisible(true);
    center.add(tablePanel, BorderLayout.CENTER);

    this.add(topbar, BorderLayout.NORTH);
    this.add(center, BorderLayout.CENTER);
    this.add(bottomBar, BorderLayout.SOUTH);
    
    setResizable(false);
    setSize(frameWidth, frameHeight);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
