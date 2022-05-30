import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

public class Trainer extends Entity
{
  private static int mHP = 25;
  private int money = 25;
  private int potions = 1;
  private int pokeballs = 5;
  private Point Loc;
  private ArrayList<Pokemon>pokemon;
  //public Map map;
  

  public Trainer(String n, Pokemon p) {
    super(n, mHP, mHP);
    this.pokemon = new ArrayList<Pokemon>();
    this.pokemon.add(p);
    this.Loc = Map.getInstance().findStart();
  }
  
  public int getMoney() {
    return this.money;
  }
  
  public boolean spendMoney(int amt)
  {
    if (this.money < amt){
      return false;
    }
    else{
      this.money -= amt;
    return true;
    }
    
  }
  
  public void receiveMoney(int amt){
    this.money += amt;
  }
  
  public boolean hasPotion(){
    if (this.potions > 0){
      return true;
      }
      else {
      return false;
      }
  }
  
  public boolean receivePotion(){
    potions++;
    return true;
  }
  
  public void usePotion(int pokeIndex){
    if(this.hasPotion()){
      this.pokemon.get(pokeIndex).heal();
      this.potions--;
    }
  }
  
  public boolean hasPokeball(){
    if (this.pokeballs > 0){
    return true;  
    }
    else  {
      return false;
    }
  }
  
  public boolean receivePokeball(){
    pokeballs++;
    return true;
  }
  
  public boolean catchPokemon(Pokemon p){
    Random r = new Random();
    double chance = 1 - (p.getHP()/p.getMaxHP());
    double check = r.nextDouble();
    if (check <= chance) {
      this.pokemon.add(p);   
      return true;
    } 
    else {
      return false;
    }
    
  }

  
  public Point getLocation(){
    return this.Loc;
  }
  
  public char goNorth(){
    if(this.Loc.getX() > 0) {
    
    this.Loc.setLocation((int)this.Loc.getX()-1, (int)this.Loc.getY());
    Map.getInstance().reveal(this.Loc);
    return Map.getInstance().getCharAtLoc(this.Loc);
    }
    else{
      return 'q';
    }
  }
  
  public char goSouth(){
    if(this.Loc.getX() < 4){
      
      this.Loc.setLocation((int)this.Loc.getX()+1, (int)this.Loc.getY());
      Map.getInstance().reveal(this.Loc);
      return Map.getInstance().getCharAtLoc(this.Loc);
      
    }
    else {
      return 'q';
    }
  
  }
  
  public char goEast() {
    if(this.Loc.getY() < 4){
    this.Loc.setLocation((int)this.Loc.getX(), (int)this.Loc.getY()+1);
    Map.getInstance().reveal(this.Loc);
    return Map.getInstance().getCharAtLoc(this.Loc);
    
    }
    else {
      return 'q';
    }
  }
  
  public char goWest() {
    if(this.Loc.getY() > 0){
    this.Loc.setLocation((int)this.Loc.getX(), (int)this.Loc.getY()-1);
    Map.getInstance().reveal(this.Loc);
    return Map.getInstance().getCharAtLoc(this.Loc);
    
    }
    else{
      return 'q';
    }
  }
  
  public int getNumPokemon(){
    int total = 0;
    for (Pokemon p: this.pokemon) {
      total++;
    }
    return total;
  }
  
  public void healAllPokemon(){
    for (int i = 0; i < this.pokemon.size(); i++) {
      this.pokemon.get(i).heal();
    }
  }

  public void buffAllPokemon(){
    for (int i = 0; i < pokemon.size(); i++) {
      pokemon.set(i, PokemonGenerator.getInstance().addRandomBuff(pokemon.get(i)));
    }
  }

  public void debuffPokemon(int index){
    pokemon.set(index, PokemonGenerator.getInstance().addRandomDebuff(pokemon.get(index)));
  }

  public Pokemon getPokemon(int index){
    return this.pokemon.get(index);
  }

  public String getPokemonList(){
    String toReturn = new String();
    int numTracker = 1;
    for (Pokemon p: this.pokemon) {
      toReturn += numTracker + ". " + p.toString();
      toReturn += "\n";
      numTracker++;
    }
    return toReturn;
  }
  public Pokemon removePokemon(int index){
    return this.pokemon.remove(index);
  }

  public String toString(){
    String toReturn = super.toString();
    toReturn += "\nMoney: " + this.money +"\n";
    toReturn += "Potions: " + this.potions +"\n";
    toReturn += "Poke Balls: " + this.pokeballs +"\n";
    toReturn += "Pokemon:\n______\n";
    toReturn += getPokemonList();
    toReturn += Map.getInstance().maptoString(this.Loc);
   return toReturn; 
  }
}
