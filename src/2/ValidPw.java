import java.io.BufferedReader;
import java.io.FileReader;

public class ValidPw {
  public static void main(String args[]){
    containsBetween();
    oneOfCharsIs();

  }

  static void containsBetween(){
    BufferedReader reader;
    String row;
    int max;
    int min;
    int validCount = 0;
    int invalidCount = 0;
    String letter;
    try{
      reader = new BufferedReader(new FileReader("./Data/input2"));
      while ((row = reader.readLine()) != null){
        int count = 0;
        String[] split = row.split(" ", 3);
        String[] minMax = split[0].split("-", 2);
        min = Integer.parseInt(minMax[0]);
        max = Integer.parseInt(minMax[1]);
        letter = split[1].substring(0,1);
        char c = letter.charAt(0);
        for(int i = 0; i < split[2].length(); i++){
          if (c == split[2].charAt(i) ){
            count++;
          }
        }
        if (count >= min && count <= max){
          validCount++;
        }else{
          invalidCount++;
        }
      }
      System.out.println("Valid count is:" + validCount + "\nInvalid count is:" + invalidCount);
    } catch (Exception e){

    }
  }

  static void oneOfCharsIs(){
    BufferedReader reader;
    String row;
    int firstIndex;
    int secondIndex;
    int validCount = 0;
    int invalidCount = 0;
    String letter;
    try{
      reader = new BufferedReader(new FileReader("./Data/input2"));
      while ((row = reader.readLine()) != null){
        int count = 0;
        String[] split = row.split(" ", 3);
        String[] minMax = split[0].split("-", 2);
        firstIndex = Integer.parseInt(minMax[0]) - 1;
        secondIndex = Integer.parseInt(minMax[1]) - 1;
        letter = split[1].substring(0,1);
        char c = letter.charAt(0);
        if (split[2].charAt(firstIndex) == c){
          if (split[2].charAt(secondIndex) != c){
            validCount++;
          }else{
            invalidCount++;
          }
        }else if (split[2].charAt(secondIndex) == c) {
          validCount++;
        }else{
          invalidCount++;
        }
      }
      System.out.println("Valid count is:" + validCount + "\nInvalid count is:" + invalidCount);
    } catch (Exception e){

    }

  }
}
