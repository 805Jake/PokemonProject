//import java.util.Random;
class HpDown extends PokemonDecorator{

public HpDown(Pokemon p){
  //– decreases a pokémon’s hp and maxHp by 1 and adds ‘-HP’ to their name.
  super(p, " -HP", -1);
}

}