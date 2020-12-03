package utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufReader {
  public static BufferedReader getReader(String uri){
    try {
      FileReader fileReader = new FileReader(uri);
      return new BufferedReader(fileReader);
    } catch (Exception e){
      return null;
    }
  }

}
