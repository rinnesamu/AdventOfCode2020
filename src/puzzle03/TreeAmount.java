package puzzle03;

import java.io.BufferedReader;
import java.util.ArrayList;

public class TreeAmount {

  public static void main(String args[]){
    int first = countSumOfTrees(1, 1);
    int second = countSumOfTrees(3, 1);
    int third = countSumOfTrees(5,1);
    int fourth = countSumOfTrees(7,1);
    int fifth = countSumOfTrees(1,2);
    int mult = first*second*third*fourth*fifth;
    System.out.println("You get " + mult + " when you multiply those together");
  }


  static int countSumOfTrees(int right, int down){
    final char TREE = '#';
    final char NO_TREE = '.';
    String row;
    int sumOfTrees = 0;
    ArrayList<ArrayList> map = new ArrayList<>();
    ArrayList<Integer> layer = null;
    int depth = 0;
    BufferedReader reader = utils.BufReader.getReader("./Data/input3");
    try {
      while ((row = reader.readLine()) != null){
        layer = new ArrayList<>();
        for (int i = 0; i< row.length(); i++){
          if (row.charAt(i) == TREE){
            layer.add(1);
          } else {
            layer.add(0);
          }
        }
        map.add(layer);
        depth++;
      }
      int xCoord = 0;
      for (int a = down; a < depth; a += down){
        xCoord = (xCoord + right) % map.get(a).size();
        sumOfTrees  = sumOfTrees + (int) map.get(a).get(xCoord);
      }
      System.out.println("Trees encoutered: " + sumOfTrees);
      return sumOfTrees;

    } catch (Exception e){
      System.out.println("Encountered a problem");
      return 0;

    }
  }
}
