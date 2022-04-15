package com.salestracker;

import java.util.UUID;

import java.io.FileNotFoundException;
import java.io.IOException; 

import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.File;  
import java.io.FileReader;

import java.io.FileWriter;
import java.io.BufferedWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {

  // Overloading
  public static void log(String message, Object... args){
    System.out.println(String.format(message, args));
  }

  public static void log(int message, Object... args){
    System.out.println(message);
  }

  public static void log(double message, Object... args){
    System.out.println(message);
  }

  public static void log(boolean message, Object... args){
    System.out.println(message);
  }

  public static void spaceLine (int num) {
    for (int i = 0; i < num; i++) {
      System.out.println();
    }
  }

  public static String getDateTime(){
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = sdf.format(date);

    return formattedDate;
  }

  public static String genRandomUUID() {
    return UUID.randomUUID().toString();
  }

  public static Boolean writeToFile (String fileName, String content){
    try {
      File file = new File(fileName);
      if (!file.exists()) {
        log("Creating file: %s", fileName);
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(content);
      bw.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  private static String getFileNameByDbName(String dbName){
    if(dbName == "Food"){
      return "db/Food.json";
    }else if(dbName == "Drink"){
      return "db/Drink.json";
    }else {
      return "";
    }
  }
  
  public static JSONArray readFromDB (String dbName){
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = new JSONArray();

    try {
      Object obj = parser.parse(new FileReader(getFileNameByDbName(dbName)));
      jsonArray = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }

    return jsonArray;
  }

  public static Boolean appendToDB (String dbName, JSONArray arrayToAppend) {
    String dbFileName = getFileNameByDbName(dbName);
    JSONArray foodListFromFile = readFromDB(dbName);

    // loop json array and put in object
    for (Object obj : arrayToAppend) {
      JSONObject jsonObject = (JSONObject) obj;
      foodListFromFile.add(jsonObject);
    }

    try {
      File file = new File(dbFileName);
      FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
      fw.write(foodListFromFile.toJSONString());
      fw.flush();
      fw.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static String findValueByKey (JSONObject targetObj, String keyName) {
    String valueFound = (String) targetObj.get(keyName);
    return valueFound;
  }

  public static String findFoodByFoodId (String foodId) {
    JSONArray foodList = readFromDB("Food");
    String foodName = "";

    for (Object obj : foodList) {
      JSONObject jsonObject = (JSONObject) obj;
      String foodIdFromFile = findValueByKey(jsonObject, "food_id");
      if (foodIdFromFile.equals(foodId)) {
        foodName = findValueByKey(jsonObject, "food_name");
        break;
      }
    }

    return foodName;
  }

  public static String findDrinkByDrinkId (String drinkId) {
    JSONArray drinkList = readFromDB("Drink");
    String drinkName = "";

    for (Object obj : drinkList) {
      JSONObject jsonObject = (JSONObject) obj;
      String drinkIdFromFile = findValueByKey(jsonObject, "drink_id");
      if (drinkIdFromFile.equals(drinkId)) {
        drinkName = findValueByKey(jsonObject, "drink_name");
        break;
      }
    }

    return drinkName;
  }
}