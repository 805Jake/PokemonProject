//import java.util.Random;
class HpUp extends PokemonDecorator{

  public HpUp(Pokemon p){
    //Random r = new Random();
    //super(p, "+Hp", r.nextInt(2)+1);
     super(p, " +HP", +1);
  }

}