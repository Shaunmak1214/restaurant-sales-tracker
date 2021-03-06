package com.salestracker;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
public class MenuFrame extends JFrame{  

  // set colors
  private static final Color  primaryColor = new Color(111, 45, 189);
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
  private int totalDrinksAndFoodsLength = Drinks.getDrinks().length + Foods.getFoods().length;
  private String[] itemsTypeBasedOnAllItemsLabel = new String[totalDrinksAndFoodsLength];

  JPanel topbar = new JPanel();
  JPanel bottombar = new JPanel();
  JPanel bottomLeftBar = new JPanel();
  JPanel bottomRightBar = new JPanel();
  JPanel center = new JPanel();
  JPanel centerLeft = new JPanel();
  JPanel centerLeftTop = new JPanel();
  JPanel centerLeftBottom = new JPanel();
  JPanel centerRight = new JPanel();
  JPanel centerRightTop = new JPanel();
  JPanel centerRightCartItemsPanel = new JPanel();
  JPanel centerRightBottom = new JPanel();
  JPanel[] itemsPanel = new JPanel[totalDrinksAndFoodsLength];
  List<JPanel> cartItemsPanel = new ArrayList<JPanel>();

  JLabel[] allItemsLabel = new JLabel[totalDrinksAndFoodsLength];
  JLabel[] allItemsPriceLabel = new JLabel[totalDrinksAndFoodsLength];
  JLabel window_label = new JLabel("Western Wizards");
  JLabel discount_text = new JLabel("Enter discount code here: ");
  JLabel discount_label = new JLabel("Discount applied: ");
  JLabel discount_value_label = new JLabel("");
  JLabel cartLabel = new JLabel("Cart");
  JLabel totalPriceTextLabel = new JLabel("Total Price: ");
  JLabel totalPriceLabel = new JLabel("MYR0.00");
  JLabel netPriceTextLabel = new JLabel("Net Price: ");
  JLabel netPriceLabel = new JLabel("MYR0.00");
  JLabel centerLeftTopLabel = new JLabel("Drinks");
  JLabel centerLeftBottomLabel = new JLabel("Foods");
  List<JLabel> cartItemsLabel = new ArrayList<JLabel>();

  JTextField discount_code_input = new JTextField(10);

  JButton checkout_button = new JButton("Checkout");
  JButton submit_disc_button = new JButton("Apply Discount");
  JButton[] add_to_cart_btn_arr = new JButton[totalDrinksAndFoodsLength];
  List<JButton> cartItemsRmvBtn = new ArrayList<JButton>();

  public MenuFrame(Cart cart) {
    super("Western Wizards");
    this.cart = cart;

    // set border layout 
    setLayout(new BorderLayout());

    // set labels
    for (int i = 0; i < allItemsLabel.length; i++) {
      int total_drinks_length = Drinks.getDrinks().length;
      if (i < total_drinks_length) {
        allItemsLabel[i] = new JLabel(Drinks.getDrinks()[i].getDrink_name() + " - MYR" + Drinks.getDrinks()[i].getDrink_price());
        allItemsPriceLabel[i] = new JLabel(String.valueOf(Drinks.getDrinks()[i].getDrink_price()));
        itemsTypeBasedOnAllItemsLabel[i] = "Drink";

      }else {
        allItemsLabel[i] = new JLabel(Foods.getFoods()[i - total_drinks_length].getFood_name() + " - MYR" + Foods.getFoods()[i - total_drinks_length].getFood_price());
        allItemsPriceLabel [i] = new JLabel(String.valueOf(Foods.getFoods()[i - total_drinks_length].getFood_price()));
        itemsTypeBasedOnAllItemsLabel[i] = "Food";
      }
    }

    window_label.setFont(new Font("Montserrat", Font.BOLD, 20));
    window_label.setForeground(highlightColor);

    discount_text.setFont(new Font("Montserrat", Font.PLAIN, 12));
    discount_text.setForeground(textColor);

    checkout_button.setFont(new Font("Montserrat", Font.PLAIN, 12));
    checkout_button.setBackground(accentColor);

    discount_label.setFont(new Font("Montserrat", Font.PLAIN, 12));
    discount_label.setForeground(textColor);
    discount_value_label.setFont(new Font("Montserrat", Font.PLAIN, 12));
    discount_value_label.setForeground(textColor);

    submit_disc_button.setFont(new Font("Montserrat", Font.PLAIN, 12));
    submit_disc_button.setBackground(accentColor);
    submit_disc_button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String discount_code = discount_code_input.getText();
        Boolean valid_discount = cart.handleDiscount(discount_code);

        if(valid_discount) {
          discount_value_label.setText(cart.getCart_disc_code());
          NumberFormat totalPriceFormatter = NumberFormat.getCurrencyInstance();
          totalPriceLabel.setText(totalPriceFormatter.format(cart.getCart_total_price()));
          netPriceLabel.setText(totalPriceFormatter.format(cart.getCart_net_price()));
          submit_disc_button.setEnabled(false);
        }else {
          createPopUp("discount_code_invalid");
          discount_value_label.setText("discount_code_invalid");
        }
      }
    });

    cartLabel.setFont(new Font("Montserrat", Font.BOLD, 20));

    centerLeftTopLabel.setFont(new Font("Montserrat", Font.BOLD, 20));

    centerLeftBottomLabel.setFont(new Font("Montserrat", Font.BOLD, 20));

    // Panel
    topbar.setLayout(new FlowLayout());
    topbar.setBackground(primaryColor);
    topbar.add(window_label);

    center.setLayout(new BorderLayout());
    center.setBackground(backgroundColor);

    centerLeftTop.setLayout(new BoxLayout(centerLeftTop, BoxLayout.Y_AXIS));
    centerLeftTop.setBackground(backgroundColor);
    centerLeftTop.add(centerLeftTopLabel);

    centerLeftBottom.setLayout(new BoxLayout(centerLeftBottom, BoxLayout.Y_AXIS));
    centerLeftBottom.setBackground(backgroundColor);
    centerLeftBottom.add(centerLeftBottomLabel);

    centerLeft.setLayout(new BoxLayout(centerLeft, BoxLayout.Y_AXIS));
    centerLeft.setBackground(backgroundColor);
    centerLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    centerLeft.setPreferredSize(new Dimension( centerLeftWidth-20, frameHeight));
    centerLeft.add(centerLeftTop);
    centerLeft.add(centerLeftBottom);

    centerRightTop.setLayout(new FlowLayout());
    centerRightTop.setBackground(backgroundColor);
    centerRightTop.setPreferredSize(new Dimension(centerRightWidth, 30));
    centerRightTop.add(cartLabel);

    centerRightBottom.setLayout(new FlowLayout());
    centerRightBottom.setBackground(backgroundColor);
    centerRightBottom.setPreferredSize(new Dimension(centerRightWidth-20, 30));
    centerRightBottom.add(netPriceTextLabel);
    centerRightBottom.add(netPriceLabel);
    centerRightBottom.add(totalPriceTextLabel);
    centerRightBottom.add(totalPriceLabel);

    centerRightCartItemsPanel.setLayout(new BoxLayout(centerRightCartItemsPanel, BoxLayout.PAGE_AXIS));
    centerRightCartItemsPanel.setBackground(backgroundColor);
    centerRightCartItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    centerRightCartItemsPanel.setAlignmentY(Component.TOP_ALIGNMENT);

    centerRight.setLayout(new BorderLayout());
    centerRight.setBackground(backgroundColor);
    centerRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    centerRight.setAlignmentY(Component.TOP_ALIGNMENT);

    centerRight.add(centerRightTop, BorderLayout.PAGE_START);
    centerRight.add(new JScrollPane(centerRightCartItemsPanel), BorderLayout.CENTER);
    centerRight.add(centerRightBottom, BorderLayout.PAGE_END);

    for (int i = 0; i < allItemsLabel.length; i++) {
      JPanel itemPanel = new JPanel();
      JLabel itemPriceLabel = new JLabel();
      JButton add_to_cart_btn = new JButton("Add to cart");
      final Integer innerI = new Integer(i);

      itemsPanel[i] = itemPanel;
      add_to_cart_btn_arr[i] = add_to_cart_btn;
      add_to_cart_btn_arr[i].setFont(new Font("Montserrat", Font.PLAIN, 12));
      add_to_cart_btn_arr[i].setBackground(backgroundColor);
      add_to_cart_btn_arr[i].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          JPanel itemDetailsPanel = new JPanel();
          int total_drinks_length = Drinks.getDrinks().length;
          Double current_item_price = 0.00;
          String target_item_name = "";

          itemDetailsPanel.setBackground(backgroundColor);
          itemDetailsPanel.setLayout(new BoxLayout(itemDetailsPanel, BoxLayout.Y_AXIS));

          if (innerI < total_drinks_length) {
            target_item_name = Drinks.getDrinks()[innerI].getDrink_name();
            current_item_price = Drinks.getDrinks()[innerI].getItem_price();
            cart.addItems(Drinks.getDrinks()[innerI]);
          }else {
            target_item_name = Foods.getFoods()[innerI - total_drinks_length].getFood_name();
            current_item_price = Foods.getFoods()[innerI - total_drinks_length].getItem_price();
            cart.addItems(Foods.getFoods()[innerI - total_drinks_length]);
          }

          NumberFormat totalPriceFormatter = NumberFormat.getCurrencyInstance();
          totalPriceLabel.setText(totalPriceFormatter.format(cart.getCart_total_price()));
          netPriceLabel.setText(totalPriceFormatter.format(cart.getCart_net_price()));
          
          Utils.log("Clicked Button, adding item %s to cart", target_item_name);
          JLabel item_label = new JLabel(target_item_name);
          JLabel item_price = new JLabel("");
          item_price.setText(totalPriceFormatter.format(current_item_price));
          JPanel item_panel = new JPanel();
          JButton remove_from_cart_btn = new JButton("Remove from cart");

          item_label.setBackground(backgroundColor);
          item_label.setVisible(true);
          item_label.setFont(new Font("Montserrat", Font.BOLD, 12));
          item_label.setForeground(primaryColor);

          item_price.setBackground(backgroundColor);
          item_price.setVisible(true);
          item_price.setFont(new Font("Montserrat", Font.BOLD, 10));

          itemDetailsPanel.add(item_label);
          itemDetailsPanel.add(item_price);

          item_panel.setLayout(new BorderLayout());
          item_panel.setBackground(backgroundColor);
          item_panel.setPreferredSize(new Dimension(centerRightWidth-80, 40));

          remove_from_cart_btn.setFont(new Font("Montserrat", Font.PLAIN, 9));
          remove_from_cart_btn.setBackground(backgroundColor);
          remove_from_cart_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              int total_drinks_length = Drinks.getDrinks().length;

              if (innerI < total_drinks_length) {
                cart.removeItem(Drinks.getDrinks()[innerI].getDrink_id());
              }else {
                cart.removeItem(Foods.getFoods()[innerI - total_drinks_length].getFood_id());
              }
              NumberFormat totalPriceFormatter = NumberFormat.getCurrencyInstance();
              totalPriceLabel.setText(totalPriceFormatter.format(cart.getCart_total_price()));
              netPriceLabel.setText(totalPriceFormatter.format(cart.getCart_net_price()));

              centerRightCartItemsPanel.remove(item_panel);
              centerRightCartItemsPanel.revalidate();
              centerRightCartItemsPanel.repaint();
            } 
          });
          cartItemsRmvBtn.add(remove_from_cart_btn);

          item_panel.add(itemDetailsPanel, BorderLayout.WEST);
          item_panel.add(remove_from_cart_btn, BorderLayout.EAST);
          
          cartItemsPanel.add(item_panel);
          centerRightCartItemsPanel.add(item_panel);

          centerRightCartItemsPanel.revalidate();
          centerRightCartItemsPanel.repaint();
        }
      });

      allItemsLabel[i].setFont(new Font("Montserrat", Font.PLAIN, 14));
      allItemsLabel[i].setForeground(primaryColor);
      allItemsLabel[i].setPreferredSize(new Dimension(centerLeftWidth - 80, 30));

      allItemsPriceLabel[i].setFont(new Font("Montserrat", Font.PLAIN, 14));
      allItemsPriceLabel[i].setForeground(primaryColor);
      allItemsPriceLabel[i].setPreferredSize(new Dimension(centerLeftWidth - 80, 30));

      itemsPanel[i].setLayout(new BorderLayout());
      itemsPanel[i].setBackground(backgroundColor);
      itemsPanel[i].setVisible(true);
      itemsPanel[i].setPreferredSize(new Dimension(centerLeftWidth - 150, 50));
      itemsPanel[i].add(allItemsLabel[i], BorderLayout.WEST);
      itemsPanel[i].add(add_to_cart_btn_arr[i], BorderLayout.EAST);
      
      int total_drinks_length = Drinks.getDrinks().length;
      if(innerI < total_drinks_length) {
        centerLeftTop.add(itemsPanel[i]);
      }else {
        centerLeftBottom.add(itemsPanel[i]);
      }
    }

    bottombar.setLayout(new BorderLayout());
    bottombar.setBackground(primaryColor);

    bottomLeftBar.setLayout(new FlowLayout());
    bottomLeftBar.setBackground(primaryColor);
    bottomLeftBar.add(discount_text);
    bottomLeftBar.add(discount_code_input);
    bottomLeftBar.add(submit_disc_button);
    bottomLeftBar.add(discount_label);
    bottomLeftBar.add(discount_value_label);

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

  public static void createPopUp(String prompt) {
    if(prompt.equals("discount_code_invalid")) {
        JOptionPane.showMessageDialog(null, "The discount code is invalid", "Error", JOptionPane.ERROR_MESSAGE);  
    }    
  }
}