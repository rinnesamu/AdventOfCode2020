package puzzle07;

import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BagCounter {
  static final String PATH = "./Data/input7";
  public static void main(String args[]){
    howManyBagsCanContainShinyGold();
    howManyBagsShinyGoldContains();
  }
  static void howManyBagsCanContainShinyGold(){
    BufferedReader reader = utils.BufReader.getReader(PATH);
    HashMap<String, ArrayList<String>> bagsContaining = new HashMap<>();

    String row;
    try{
      while((row = reader.readLine())!= null){
        String[] split = row.split("[0-9]");
        String value = "";
        for (int i = 0; i < split.length; i++){
          String[] secondSplit = split[i].split(" ");
          String bag;
          if (secondSplit[0].length() == 0){
            bag = secondSplit[1] + secondSplit[2];
          }else{
            bag = secondSplit[0] + secondSplit[1];
          }
          if (i == 0){
            value = bag;
          }else{
            if (bagsContaining.get(bag) == null){
              ArrayList<String> bags = new ArrayList<>();
              bags.add(value);
              bagsContaining.put(bag, bags);
            }else{
              ArrayList<String> newList = bagsContaining.get(bag);
              newList.add(value);
              bagsContaining.replace(bag, newList);
            }
          }
        }
      }

      ArrayList<String> shiny = bagsContaining.get("shinygold");
      int count = shiny.size();
      int startIndex = 0;
      boolean added;
      do{
        added = false;
        for (int i = startIndex; i < count; i++){
          String temp = shiny.get(i);
          ArrayList<String> tempList = bagsContaining.get(temp);
          if (tempList != null) {
            for (String s : tempList) {
              if (!shiny.contains(s)) {
                shiny.add(s);
                added = true;
              }
            }
          }
        }
        startIndex = count;
        count = shiny.size();
      }while (added);
      System.out.println(shiny.size());
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  static void howManyBagsShinyGoldContains(){
    BufferedReader reader = utils.BufReader.getReader(PATH);
    HashMap<String, ArrayList<String>> bagContaining = new HashMap<>();
    String row;
    try{
      while ((row = reader.readLine()) != null){
        String key = "";
        ArrayList<String> bagsInside = new ArrayList<>();
        String[] split = row.split("bag");
        for (int i = 0; i < split.length-1; i++){
          String[] secondSplit = split[i].split(" ");
          if (i == 0) {
            key = secondSplit[secondSplit.length - 2] + " " +
              secondSplit[secondSplit.length - 1];
          }else {
            if (secondSplit[secondSplit.length - 3].equals("contain")) {
              bagsInside = null;
            } else {
              int loopAmount =
                Integer.parseInt(secondSplit[secondSplit.length - 3]);
              String bagName = secondSplit[secondSplit.length - 2] + " " +
                secondSplit[secondSplit.length - 1];
              for (int j = 0; j < loopAmount; j++) {
                bagsInside.add(bagName);
              }
            }
          }
        }
        bagContaining.put(key, bagsInside);
      }

      ArrayList<String> shiny = bagContaining.get("shiny gold");
      int count = shiny.size();
      int startIndex = 0;
      do{
        for (int i = startIndex; i < count; i++) {
          if (bagContaining.get(shiny.get(i)) != null) {
            for (String s : bagContaining.get(shiny.get(i))) {
              shiny.add(s);
            }
          }
        }
        startIndex = count;
        count = shiny.size();
      } while(count != startIndex);
      System.out.println(shiny.size());


    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
