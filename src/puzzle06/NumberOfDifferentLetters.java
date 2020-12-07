package puzzle06;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

public class NumberOfDifferentLetters {
  static final String PATH = "./data/input6";

  public static void main(String args[]){
    allGroupAnswersCount();
    everyInGroupAnsweredYesCount();
  }

  static void allGroupAnswersCount(){
    ArrayList<Character> answers = new ArrayList<>();
    int groupAnswerSum = 0;
    BufferedReader reader = utils.BufReader.getReader(PATH);
    String row;
    try{
      while ((row = reader.readLine()) != null){
        if (row.equals("")){
          groupAnswerSum += answers.size();
          answers.clear();
        }else{
          for (int i = 0; i < row.length(); i++){
            if (!answers.contains(row.charAt(i))){
              answers.add(row.charAt(i));
            }
          }

        }
      }
      groupAnswerSum += answers.size();
      System.out.println("Different groups answers sum is " + groupAnswerSum);
    }catch (Exception e){

    }
  }

  static void everyInGroupAnsweredYesCount(){
    HashMap<Character, Integer> answerCounts = new HashMap<>();
    ArrayList<Character> answers = new ArrayList<>();
    int groupAnswerSum = 0;
    int groupMembers = 0;
    int sumOfAllYes = 0;
    BufferedReader reader = utils.BufReader.getReader(PATH);
    String row;
    try {
      while ((row = reader.readLine()) != null) {
        if (row.equals("")) {
          for (int i = 0; i < answers.size(); i++){
            if (answerCounts.get(answers.get(i)) == groupMembers){
              sumOfAllYes++;
            }
          }
          groupAnswerSum += sumOfAllYes;
          groupMembers = 0;
          sumOfAllYes = 0;
          answers.clear();
          answerCounts.clear();
        } else {
          groupMembers++;
          for (int i = 0; i < row.length(); i++) {
            if (!answers.contains(row.charAt(i))) {
              answers.add(row.charAt(i));
            }
            if (answerCounts.containsKey(row.charAt(i))){
              answerCounts.replace(row.charAt(i), answerCounts.get(row.charAt(i)) + 1);
            }else{
              answerCounts.put(row.charAt(i), 1);
            }
          }
        }
      }
      for (int i = 0; i < answers.size(); i++){
        if (answerCounts.get(answers.get(i)) == groupMembers){
          sumOfAllYes++;
        }
      }
      groupAnswerSum += sumOfAllYes;
      System.out.println("Different groups answers (all yes) sum is " + groupAnswerSum);
    }catch (Exception e){

    }
  }
}
