import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Map {
  private char [][]  map;
  private boolean [] [] revealed;
  private static Map instance = null;

  public Map () {
    this.map = new char [5] [5];
    this.revealed = new boolean [5][5];

    for ( int i = 0; i < 5; i++) {
      for (int j = 0; j <5; j++) {
        this.revealed[i][j] = false;
      }
    } 
   
  }
public static Map getInstance(){
  if (instance == null){
    instance =  new Map ();
  }
  return instance;
}

public void loadMap (int mapNum) throws FileNotFoundException  {
  
    Integer num = mapNum;
    String fileName = "Area" + num + ".txt";
    File mapFile = new File (fileName);
    Scanner in = new Scanner(mapFile); //KV

    int row = 0;
    while(in.hasNextLine()){

      String line = in.nextLine();
      for (int i = 0; i < 5; i++) {
        this.map [row][i] = line.charAt(i*2);
      }
     
      row++;
    }
    for ( int i = 0; i < 5; i++) {
      for (int j = 0; j <5; j++) {
        this.revealed[i][j] = false;
      }
    } 
  }

  public char getCharAtLoc( Point p ) {
    try {
      return map[(int) p.getX()][(int) p.getY()];
    } catch (Exception e) {
      return 'q';
    }
  }

  public String maptoString (Point p) {
    String toReturn = new String();
    toReturn += "Map:\n";
    for ( int i = 0; i < 5; i++) {
      for (int j = 0; j <5; j++) {
        if (!this.revealed [i][j]) {
          toReturn += "X";
        } else {
          if (i == ((int) p.getX()) && j == ((int) p.getY()) ) {
            toReturn += "*";
          } else {
            toReturn += this.map[i][j];
          }
        }
        toReturn += " ";
      
      }  
    toReturn += "\n";
    }
    return toReturn;
  }

    
  public Point findStart () {
    Point toReturn = new Point(); 
    for ( int i = 0; i < 5; i++) {
      for (int j = 0; j <5; j++) {
        if (this.map[i][j] == 's') {
          toReturn.setLocation (i,j);
          this.reveal(toReturn);
          this.removeCharAtLoc(toReturn);
        }
      }
    }
    return toReturn;
  }

  // after the player traverses a location, the position gets revealed on the map
  public void reveal (Point p) {    
    this.revealed[(int) p.getX()][(int) p.getY()] = true;
  }


  public void removeCharAtLoc(Point p) {
    this.map[(int) p.getX()][(int) p.getY()] = 'n';
  }
  // returns the values from the map as strings to be read by user
  
}