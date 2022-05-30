import java.util.Random;

public abstract class Pokemon extends Entity {
  
  static final double [][] battleTable = 
  {{1,.5,2},{2,1,.5},{.5,2,1}};
  
  Pokemon(String n, int h, int m){
    super(n, h, m); 
  }

  public String getAttackTypeMenu(){
    //returns the basic and special attack menu string.
    return "1. Basic Attack\n2. Special Attack";
  }

  public int getNumAttackTypeMenuItems(){
   // returns 2;
    return 2;
  }
  public abstract String getAttackMenu(int atkType);

  public int getNumAttackMenuItems(int atkType){
    //returns the number of moves in the above menu. This is overridden in the base pokémon classes for the special elemental attacks.
    return 3;
  }


  public String attack(Pokemon p, int atkType, int move){

//calculates the total damage, deals it to the defending pokémon, and builds the full attack string that will be returned to be displayed during a fight (ex. “Oddish is SLAMMED by Charmander and takes 7 damage”).
      int damage = (int) (getAttackDamage(atkType, move)*getAttackMultiplier(p,atkType) + getAttackBonus(atkType));
      p.takeDamage(damage);
      String toReturn = new String();
      toReturn += this.getName() + " attacks " + p.getName() + " with " + p.getAttackString(atkType,move) + ". " + p.getName() + " takes " + damage + " damage!";
      return toReturn;

  }


  public String getAttackString(int atkType, int move ) {
     if (atkType == 1) {
        switch (move) {
          case 1:
            return "Slammed";
          case 2:
            return "Tackled";
          default:
            return "Punched";
        }
      } else {
        return "";
      }
  }

  public int getAttackDamage(int atkType, int move) {
    
    //returns the randomized damage for the chosen move. Also overridden in the base pokémon classes for special elemental attacks.
    Random r = new Random();
    switch (move) {
      case 1:
        return r.nextInt(6);
      case 2: 
        return r.nextInt(2)+2;
      default:
        return r.nextInt(4)+1;
    }

  }

  public double getAttackMultiplier(Pokemon p, int atkType) {
   //returns the attack multiplier for that class’s moves (ex. for elemental attacks it’ll return the result of the battle table). This can be overridden in the base pokémon classes for the special elemental attacks and/or in the decoration buff/debuff classes.
    if (atkType == 2)
      return battleTable[getType()][p.getType()]; 
    else 
      return 1;
    
  }
  public int getAttackBonus(int atkType){
//returns the attack bonus that will be added to the calculated damage. This can be overridden in the base pokémon classes for the special elemental attacks and/or in the decoration buff/debuff classes.
    return 0;
  }

  public int getType() {
    return 0;
  }

}