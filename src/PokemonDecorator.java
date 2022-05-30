import java.util.ArrayList;
public abstract class PokemonDecorator extends Pokemon{

  private Pokemon pokemon;

  public PokemonDecorator(Pokemon p, String extraName, int extraHp){
    //super(p.getName(), p.getHP(),p.getMaxHP());
    super(p.getName() + extraName, p.getHP() + extraHp, p.getMaxHP() + extraHp);
   /* String name = new String();
    pokemon = p;
    
    
    char [] parsa = p.getName().toCharArray();
    boolean buffa = false;
    String splitta = new String();
    for (int i = 0; i < parsa.length; i ++) {
      if (!buffa) {
        if (parsa[i] == '-'|| parsa[i] == '+'){
          buffa = !buffa;
          splitta += parsa[i];
        } else {
          name += parsa[i];
        }
      } else {
        splitta += parsa[i];
      }
    }
    String [] buffs = new String [2];
    if (splitta.substring(0,1).equalsIgnoreCase("-")) {
      buffs = splitta.split("+");
    }else {
      buffs = splitta.split("-");
    }
      for (int k = 0; k < buffs.length; k++) {
        if (extraName.substring(1).equalsIgnoreCase(buffs[k].substring(1))) {
          if (extraName.substring(0,1).equalsIgnoreCase(buffs[k].substring(0,1))) {
            name += buffs[k];
          }
        }else {
          name += buffs[k];
        } 
      }*/

    switch (p.getType()) {
      case 0:
        pokemon = new Fire(p.getName(), p.getHP()+extraHp,p.getMaxHP()+extraHp);
        break;
      case 1:
        pokemon = new Water(p.getName(), p.getHP()+extraHp,p.getMaxHP()+extraHp);
        break;
      case 2:
        pokemon = new Grass(p.getName(), p.getHP()+extraHp,p.getMaxHP()+extraHp);
        break;
    } 
  }

  public String getAttackMenu(int atkType){
    return pokemon.getAttackMenu(atkType);
  }

  public int getNumAttackMenuItems(int atkType){
   return pokemon.getNumAttackMenuItems(atkType);
  }

  public String getAttackString(int atkType, int move){
    return pokemon.getAttackString(atkType, move);
  }


  public int getAttackDamage(int atkType, int move){
    return pokemon.getAttackDamage(atkType, move);
  }

  public double getAttackMultiplier(Pokemon p, int type){
    return pokemon.getAttackMultiplier(p,type);
  }

  public int getAttackBonus(int type){
    return pokemon.getAttackBonus(type);
  }/*{
    return pokemon.getAttackBonus(type);
  }*/
  public int getType(){
    return pokemon.getType();
  }
}