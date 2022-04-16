package com.salestracker;

public class Pair<F, S> {         
  public final F first;
  public final S second;

  // getters
  public F first() { return first; }
  public S second() { return second; }

  public Pair(F first, S second) {         
    this.first = first;
    this.second = second;
  }
}