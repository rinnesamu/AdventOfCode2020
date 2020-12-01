
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FindSum {

  public static void main(String args[]) {
    final int SUM_TO_FIND = 2020;
    SumOfTwo(SUM_TO_FIND);
    SumOfThree(SUM_TO_FIND);

  }

  static void SumOfTwo(final int SUM_TO_FIND){
    BufferedReader reader = null;
    ArrayList<Integer> list = new ArrayList<>();
    String row;
    try {
      reader = new BufferedReader(new FileReader("./Data/input"));
      while ((row = reader.readLine()) != null) {
        int num = Integer.parseInt(row);
        if (list.contains(num)){
          int otherNum = SUM_TO_FIND-num;
          int mult = num*otherNum;
          System.out.println("Numbers are " + num + " and " + otherNum
            + " and you get " + mult + " when you multiply those together");
          break;
        }

        list.add(SUM_TO_FIND-num);
      }
    } catch (Exception e) {
    }
  }

  static void SumOfThree(final int SUM_TO_FIND) {
    BufferedReader reader = null;
    HashMap<Integer, int[]> map = new HashMap();
    ArrayList<Integer> listOfPastNumbers = new ArrayList<>();
    String row;
    try{
      reader = new BufferedReader(new FileReader("./Data/input"));
      while ((row = reader.readLine()) != null) {
        int num = Integer.parseInt(row);
        int[] summedNumbers = map.get(num);
        if (summedNumbers != null){
          System.out.println("Numbers are " + num + ", " + summedNumbers[0] +
            " and  " + summedNumbers[1] +
            ". If you multiply those together you get " +
            num*summedNumbers[0]*summedNumbers[1]);
          break;
        }
        if (listOfPastNumbers.size() >= 1){
          for (int i : listOfPastNumbers){
            if (i+num < SUM_TO_FIND){
              int keyToAdd = SUM_TO_FIND-i-num;
              map.put(keyToAdd, new int[]{i, num});
            }
          }
        }
        listOfPastNumbers.add(num);

      }
    } catch (Exception e){

    }
  }

}
