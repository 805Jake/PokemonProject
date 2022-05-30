import java.util.Random;
class Water extends Pokemon{

  public Water(String n, int h, int m){
    super(n,h,m);
  }

  public String getAttackMenu(int atkType){
    if (atkType == 1)
      return "1. Slam\t2. Tackle\n3. Punch";
    else 
      return "1. Water Gun\t2. Bubble Beam\n3. Waterfall\n";

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
          return "Water Gun";
        case 2:
          return "Bubble Beam";
        default:
          return "Waterfall";
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
            return r.nextInt(4)+2;
          case 2: 
            return r.nextInt(3)+1;
          default:
            return r.nextInt(4)+1;
        }
    }
  }

  public double getAttackMultiplier(Pokemon p, int atkType){
    return battleTable[getType()][p.getType()];
  }

  public int getType() {
    return 1;
  }

}