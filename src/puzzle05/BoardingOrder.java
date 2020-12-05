package puzzle05;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;

public class BoardingOrder {

  static final String PATH = "./Data/input5";
  static final char LEFT = 'L';
  static final char RIGHT = 'R';
  static final char BACK = 'B';
  static final char FRONT = 'F';

  public static void main(String args[]){
    String row;
    BufferedReader reader = utils.BufReader.getReader(PATH);
    ArrayList<Integer> seatIds = new ArrayList<>();
    try{
      while((row = reader.readLine()) != null){
        int seatRow = getSeatRow(row);
        int seatColumn = getSeatColumn(row);
        seatIds.add(getSeatId(seatColumn, seatRow));
      }
      System.out.println("Max seat id is " + getMaxSeatId(seatIds));
      System.out.println("My own id is " + getOwnSeatId(seatIds));
    }catch(Exception e){ }
  }

  static int getSeatRow(final String row){
    int lowerBoundary = 0;
    int upperBoundary = 127;
    for (int i = 0; i < 7; i++){
      if (row.charAt(i) == FRONT){
        upperBoundary = (lowerBoundary + upperBoundary)/2;
      }else{
        lowerBoundary = 1 + (lowerBoundary + upperBoundary)/2;
      }
    }
    return lowerBoundary;
  }

  static int getSeatColumn(final String row){
    int lowerBoundary = 0;
    int upperBoundary = 7;
    for (int i = 7; i < 10; i++){
      if (row.charAt(i) == LEFT){
        upperBoundary = (lowerBoundary + upperBoundary)/2;
      }else{
        lowerBoundary = 1 + (lowerBoundary + upperBoundary)/2;
      }
    }
    return lowerBoundary;
  }

  static int getMaxSeatId(ArrayList<Integer> list){
    int max = 0;
    for (int i = 0; i < list.size(); i++){
      if (list.get(i) > max){
        max = list.get(i);
      }
    }
    return max;
  }

  static int getSeatId(final int column, final int row){
    final int multiplier = 8;
    final int seatId = row*multiplier+column;
    return seatId;
  }

  static int getOwnSeatId(ArrayList<Integer> list){
    Collections.sort(list);
    for (int i = 0; i < list.size(); i++){
      if (list.get(i+1) - list.get(i) != 1){
        return list.get(i) + 1;
      }
    }
    return -1;
  }
}
