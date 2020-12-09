package puzzle08;

import java.io.BufferedReader;
import java.util.ArrayList;

public class BootCode {
  static final String PATH = "./Data/input8";
  static final String CHANGE_INDEX_BY = "jmp";
  static final String INCREASE_COUNT_BY = "acc";
  static final String DO_NOTHING = "nop";
  static int CHANGED_INSTRUCTION_INDEX = -1;
  public static void main(String args[]){
    findLoop();
    findCorruptedCode();
  }
  static void findLoop(){
    BufferedReader reader = utils.BufReader.getReader(PATH);
    String row;
    try{
      ArrayList<String[]> listOfInstructions = new ArrayList<>();
      while((row = reader.readLine()) != null){
        String[] split = row.split(" ");
        String amount = split[1];
        String instruction = split[0];
        String[] fullLine = {instruction, amount};
        listOfInstructions.add(fullLine);
      }
      boolean looped = false;
      int i = 0;
      int accCount = 0;
      ArrayList<Integer> visitedIndexes = new ArrayList<>();
      do{
        if (visitedIndexes.contains(i)){
          looped = true;
        } else {
          visitedIndexes.add(i);
          String[] fullInstruction = listOfInstructions.get(i);
          String instruction = fullInstruction[0];
          int amount = Integer.parseInt(fullInstruction[1]);
          switch (instruction){
            case CHANGE_INDEX_BY:
              i += amount;
              break;
            case INCREASE_COUNT_BY:
              accCount += amount;
              i++;
              break;
            case DO_NOTHING:
              i++;
              break;
            default:
              System.out.println("Unkown instruction");
              break;
          }
        }
      }while(!looped);
      System.out.println(accCount);

    }catch (Exception e){
      e.printStackTrace();
    }
  }

  static void findCorruptedCode(){
    BufferedReader reader = utils.BufReader.getReader(PATH);
    String row;
    try{
      ArrayList<String[]> listOfInstructions = new ArrayList<>();
      while((row = reader.readLine()) != null){
        String[] split = row.split(" ");
        String amount = split[1];
        String instruction = split[0];
        String[] fullLine = {instruction, amount};
        listOfInstructions.add(fullLine);
      }
      boolean terminated = false;
      int accCount = 0;
      while (!terminated) {
        ArrayList<String[]> fixedList = getNextValidIndex(listOfInstructions);
        boolean looped = false;
        int i = 0;
        accCount = 0;
        ArrayList<Integer> visitedIndexes = new ArrayList<>();
        do {
          if (i == fixedList.size() -1){
            terminated = true;
          }else if (visitedIndexes.contains(i)) {
            looped = true;
          } else {
            visitedIndexes.add(i);
            String[] fullInstruction = fixedList.get(i);
            String instruction = fullInstruction[0];
            int amount = Integer.parseInt(fullInstruction[1]);
            switch (instruction) {
              case CHANGE_INDEX_BY:
                i += amount;
                break;
              case INCREASE_COUNT_BY:
                accCount += amount;
                i++;
                break;
              case DO_NOTHING:
                i++;
                break;
              default:
                System.out.println("Unkown instruction");
                break;
            }
          }
        } while (!looped && !terminated);
      }
      System.out.println(accCount);

    }catch (Exception e){
      e.printStackTrace();
    }
  }
  static ArrayList<String[]> getNextValidIndex(ArrayList<String[]> list){
    ArrayList<String[]> returnList = new ArrayList<>();
    returnList.addAll(list);
    while(true){
      CHANGED_INSTRUCTION_INDEX++;
      if(returnList.get(CHANGED_INSTRUCTION_INDEX)[0].equals(DO_NOTHING)){
        String[] newValue = {CHANGE_INDEX_BY, returnList.get(CHANGED_INSTRUCTION_INDEX)[1]};
        returnList.set(CHANGED_INSTRUCTION_INDEX, newValue);
        return returnList;
      }else if (returnList.get(CHANGED_INSTRUCTION_INDEX)[0].equals(CHANGE_INDEX_BY)){
        String[] newValue = {DO_NOTHING, returnList.get(CHANGED_INSTRUCTION_INDEX)[1]};
        returnList.set(CHANGED_INSTRUCTION_INDEX, newValue);
        return returnList;
      }
    }
  }
}
