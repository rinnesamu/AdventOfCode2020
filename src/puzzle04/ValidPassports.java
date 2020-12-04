package puzzle04;

import java.io.BufferedReader;
import java.util.Arrays;


public class ValidPassports {

  static boolean byrFound;
  static boolean iyrFound;
  static boolean eyrFound;
  static boolean hgtFound;
  static boolean hclFound;
  static boolean eclFound;
  static boolean pidFound;
  static boolean cidFound;
  static final String PATH = "./Data/input4";
  static final String BIRTH_YEAR = "byr";
  static final String ISSUE_YEAR = "iyr";
  static final String EXPIRATION_YEAR = "eyr";
  static final String HEIGHT = "hgt";
  static final String HAIR_COLOR = "hcl";
  static final String EYE_COLOR = "ecl";
  static final String PASSPORT_ID = "pid";
  static final String COUNTRY_ID = "cid";
  static final char FIRST_CHARACTER_OF_HCL = '#';
  static final String[] ALLOWED_EYE_COLOR = {"amb", "blu",
    "brn", "gry", "grn", "hzl", "oth"};

  public static void main(String args[]){
    //validWithoutRestrictions();
    validWithRestrictions();
  }
  static void validWithoutRestrictions(){
    reset();
    String row;
    int validPassportCount = 0;
    BufferedReader reader = utils.BufReader.getReader(PATH);
    try{
      while ((row = reader.readLine()) != null){
        if (row.equals("")){
          if(checkBooleans()){
            validPassportCount++;
          }
          reset();
        }else{
          String[] noSpaceRow = row.split("\\s");
          for (String s : noSpaceRow){
            String[] split = s.split(":");
            switch (split[0]){
              case BIRTH_YEAR:
                byrFound = true;
                break;
              case ISSUE_YEAR:
                iyrFound = true;
                break;
              case EXPIRATION_YEAR:
                eyrFound = true;
                break;
              case HEIGHT:
                hgtFound = true;
                break;
              case HAIR_COLOR:
                hclFound = true;
                break;
              case EYE_COLOR:
                eclFound = true;
                break;
              case PASSPORT_ID:
                pidFound = true;
                break;
              default:
                System.out.println("Not important information");
            }
          }
        }
      }
      if (checkBooleans()) { // check if last row was ok.
        validPassportCount++;
      }
      System.out.println("Valid passports found: " + validPassportCount );
    }catch (Exception e) {

    }
  }

  static void validWithRestrictions(){
    reset();
    String row;
    int validPassportCount = 0;
    BufferedReader reader = utils.BufReader.getReader(PATH);
    try{
      while ((row = reader.readLine()) != null){
        if (row.equals("")){
          if(checkBooleans()){
            validPassportCount++;
          }
          reset();
        }else{
          String[] noSpaceRow = row.split("\\s");
          for (String s : noSpaceRow){
            String[] split = s.split(":");
            String value = split[1];
            switch (split[0]){
              case BIRTH_YEAR:
                if(Integer.parseInt(value) >= 1920 &&
                  Integer.parseInt(value) <= 2002){
                  byrFound = true;
                } else{
                  System.out.println("Invalid birth year " +value);
                }
                break;
              case ISSUE_YEAR:
                if(Integer.parseInt(value) >= 2010 &&
                  Integer.parseInt(value) <= 2020) {
                  iyrFound = true;
                } else{
                  System.out.println("Invalid issue year " + value);
                }
                break;
              case EXPIRATION_YEAR:
                if(Integer.parseInt(value) >= 2020 &&
                  Integer.parseInt(value) <= 2030) {
                  eyrFound = true;
                }else{
                  System.out.println("invalid expiration year " + value);
                }
                break;
              case HEIGHT:
                if (value.length() <= 3){
                  break;
                }
                char lastChar = value.charAt(value.length()-1);
                char secondLastChar = value.charAt(value.length()-2);
                String lastTwoChars = String.valueOf(secondLastChar) +
                  String.valueOf(lastChar);
                String heightDigits = "";
                for (int i = 0; i < value.length()-2; i++){
                  heightDigits += String.valueOf(value.charAt(i));
                }
                int heightValue = Integer.parseInt(heightDigits);
                if (lastTwoChars.equals("cm")){
                  if (heightValue >= 150 && heightValue <= 193){
                    hgtFound = true;
                  }
                }else if (lastTwoChars.equals("in")){
                  if (heightValue >= 59 && heightValue <= 76){
                    hgtFound = true;
                  }
                }else{
                  System.out.println("Invalid height: " + value);
                }
                break;
              case HAIR_COLOR:
                if(value.matches("^[" + FIRST_CHARACTER_OF_HCL + "]" +
                  "[a-f0-9]{6}")){
                  hclFound = true;
                }else{
                  System.out.println("Invalid hair color " + value);
                }
                break;
              case EYE_COLOR:
                if ( Arrays.stream(ALLOWED_EYE_COLOR)
                  .anyMatch(key -> key.equals(value))){
                  eclFound = true;
                }else{
                  System.out.println("Invalid eye color " + value);
                }
                break;
              case PASSPORT_ID:
                if(value.matches("[0-9]{9}")){
                  pidFound = true;
                }else{
                  System.out.println("Invalid passport id " + value);
                }
                break;
              default:
                System.out.println("Not important information");
            }
          }
        }

      }
      if (checkBooleans()) { // check if last row was ok.
        validPassportCount++;
      }
      System.out.println("Valid passports found: " + validPassportCount );

    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void reset(){
    byrFound = false;
    iyrFound = false;
    eyrFound = false;
    hgtFound = false;
    hclFound = false;
    eclFound = false;
    pidFound = false;
    cidFound = false;
  }

  static boolean checkBooleans(){
    if (byrFound && iyrFound && eyrFound && hgtFound &&
      hclFound && eclFound && pidFound) { // check if last row was ok.
      return true;
    }
    return false;
  }
}