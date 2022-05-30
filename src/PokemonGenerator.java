import java.io.*;
import java. util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

class PokemonGenerator{

  private HashMap<String, String> pokemon;
  private static PokemonGenerator instance = null;

  private PokemonGenerator() throws FileNotFoundException{
    try {
      pokemon = new HashMap <String,String>();
      File gen = new File ("PokemonList.txt");
      Scanner in = new Scanner (gen);
      while (in.hasNextLine()){
        String line = in.nextLine().trim();
        String [] splitta = line.split(",");
        pokemon.put(splitta[0],splitta[1]);
      }
      in.close();
    }catch (Exception e){
      System.out.println("FUCK");
    }
  }



  public static PokemonGenerator getInstance() {
    if (instance == null){
      try {
        instance = new PokemonGenerator();
      } catch (Exception e) {
        System.out.println("FUCKer");
      }
      
    }
    return instance;
  }

  public Pokemon generateRandomPokemon(int level){
    Pokemon toReturn = null;
    Random h  = new Random();
    Random r = new Random();
    Set <String> allNames = pokemon.keySet();
    Object [] s = allNames.toArray();
    int rand = r.nextInt(s.length);
    String name = ((String) s[rand]);
    String type = pokemon.get(name);

    int hp = 20 + h.nextInt(6);

    
    
    if ( type.equalsIgnoreCase("Fire")) {
      toReturn = new Fire(name,hp,hp);
    }else if (type.equalsIgnoreCase("Water")) {
      toReturn = new Water(name,hp,hp);
    } else {
      toReturn = new Grass (name,hp,hp);
    }
    for (int i = 0; i < level; i++) {
      toReturn = addRandomBuff(toReturn);
    }

    return toReturn;
  }

  public Pokemon getPokemon(String name){
    Random r = new Random();
    int hp = 20 + r.nextInt(5);
    String type = pokemon.get(name);
    if (type.equalsIgnoreCase("Fire")) {
      return new Fire(name,hp,hp);
    }else if (type.equalsIgnoreCase("Water")) {
      return new Water(name,hp,hp);
    } else {
      return new Grass (name,hp,hp);
    }
  }

  public Pokemon addRandomBuff(Pokemon p){
    Random r = new Random();
    int test = r.nextInt(2);
    if (test == 0) {
      return new AttackUp(p);
    } else {
      return new HpUp(p);
    }
  }

  public Pokemon addRandomDebuff(Pokemon p){
    Random r = new Random();
    int test = r.nextInt(2);
    if (test == 0) {
      return new AttackDown(p);
    } else {
      return new HpDown(p);
    }
  }
}