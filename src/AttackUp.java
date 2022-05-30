import java.util.Random;

class AttackUp extends PokemonDecorator{
  public AttackUp(Pokemon p){
  //AttackUp – increases a pokémon’s damage by 1-2 and adds ‘+ATK’ to their name.
    super(p, " +ATK", 0);
  }
  public int getAttackBonus(int type){
    Random r = new Random();
    return r.nextInt(2)+1;
  }
}