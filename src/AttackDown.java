import java.util.Random;
class AttackDown extends PokemonDecorator{

  public AttackDown(Pokemon p){
  //AttackDown – decreases the pokémon’s damage by 1 and adds a ‘-ATK’ to their name.
    super(p, " -ATK", 0);
  }

  public int getAttackBonus(int type){
    Random r = new Random();
    return r.nextInt(2)-1;
    //return (int) super. -1;
  }

}