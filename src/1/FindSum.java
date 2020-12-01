
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FindSum {

  public static void main(String args[]) {
    final int SUM_TO_FIND = 2020;
    BufferedReader reader = null;
    ArrayList<Integer> list = new ArrayList<>();
    try {
      String row;
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

}
