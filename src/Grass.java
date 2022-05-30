import java.util.Random;
class Grass extends Pokemon{

  public Grass(String n, int h, int m){
    super (n,h,m);
  }

  public String getAttackMenu(int atkType){
  if (atkType == 1){
        return "1. Slam\t2. Tackle\n3. Punch";
  }
      else {
        return "1. Vine Whip\t2. Razor Leaf\n3. Solar Beam\n";

    }
  }

  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  public String getAttackString(int atkType, int move){
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
        switch (move) {
          case 1:
            return "Vine Whip";
          case 2:
            return "Razor Leaf";
          default:
            return "Solar Beam";
        }
      }
  }

  public int getAttackDamage(int atkType, int move){
    Random r = new Random();
    switch (atkType) {
      case 1:
        switch (move) {
          case 1:
            return r.nextInt(6);
          case 2: 
            return r.nextInt(2)+2;
          default:
            return r.nextInt(4)+1;
        }
      default:
        switch (move) {
          case 1:
            return r.nextInt(3)+1;
          case 2: 
            return r.nextInt(2)+1;
          default:
            return r.nextInt(6);
        }
    }
  }

  public double getAttackMultiplier(Pokemon p, int atkType){
    return battleTable[getType()][p.getType()];
  }

  public int getType() {
    return 2;
  }

}