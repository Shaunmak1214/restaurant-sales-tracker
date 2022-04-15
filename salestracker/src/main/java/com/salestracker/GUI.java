package com.salestracker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;

public class GUI extends JFrame{  

  public GUI() {
    super("Sales Tracker");

    JPanel toolbar = new JPanel();
    toolbar.setBackground(Color.PINK);
    toolbar.setLayout(new GridLayout(1,3));
  }

  public static void main(String[] a){
    new GUI();
  }
}