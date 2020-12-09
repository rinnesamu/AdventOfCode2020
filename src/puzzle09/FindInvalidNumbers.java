package puzzle09;

import java.io.BufferedReader;
import java.util.ArrayList;

public class FindInvalidNumbers {
  static final String PATH = "./Data/input9";
  static final int prefix = 25;

  public static void main(String args[]){
    findFirstInvalidNumber();
  }
  static void findFirstInvalidNumber(){
    BufferedReader reader = utils.BufReader.getReader(PATH);
    String row;
    ArrayList<Long> fullList = new ArrayList<>();
    long invalidNumber = 0;
    try{
      while((row = reader.readLine()) != null){
        fullList.add(Long.parseLong(row));
      }
      for (int i = prefix; i < fullList.size(); i++){
        ArrayList<Long> numbersToMatch = new ArrayList<>();
        long sumOfTwo = fullList.get(i);
        boolean noMatch = true;
        for (int j = i-prefix; j < i; j++){
          long numberToCheck = fullList.get(j);
          if (numbersToMatch.contains(numberToCheck)){
            noMatch = false;
            break;
          }else{
            numbersToMatch.add(sumOfTwo-numberToCheck);
          }
        }
        if (noMatch){
          invalidNumber = fullList.get(i);
          break;
        }
      }
      System.out.println(invalidNumber);
      findRangeThatSumsToNumber(invalidNumber, fullList);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  static void findRangeThatSumsToNumber(Long number, ArrayList<Long> list){
    int startIndex = 0;
    int endIndex = 0;
    long sum = 0;
    boolean rangeFound = false;
    while(!rangeFound){
      sum += list.get(endIndex);
      if(sum == number){
        rangeFound = true;
      }else if (sum > number){
        startIndex++;
        endIndex = startIndex;
        sum = 0;
      }else{
        endIndex++;
      }
    }
    long[] minAndMax = minMax(startIndex, endIndex, list);
    System.out.println(minAndMax[0] + minAndMax[1]);

  }

  static long[] minMax(int startIndex, int endIndex, ArrayList<Long> list){
    long[] returnList = new long[2];
    for (int i = startIndex; i <= endIndex; i++){
      if (i == startIndex){
        returnList[0] = list.get(i);
        returnList[1] = list.get(i);
      }else if (list.get(i) < returnList[0]){
        returnList[0] = list.get(i);
      }else if (list.get(i) > returnList[1]){
        returnList[1] = list.get(i);
      }
    }
    return returnList;
  }
}
