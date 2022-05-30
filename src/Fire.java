import java.util.Random;
class Fire extends Pokemon{

  public Fire(String n, int h, int m){
    super (n,h,m);
  }

  public String getAttackMenu(int atkType){
  if (atkType == 1){
        return "1. Slam\t2. Tackle\n3. Punch";
  }
      else {
        return "1. Ember\t2. Fire Blast\n3. Fire Punch\n";

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
            return "Ember";
          case 2:
            return "Fire Blast";
          default:
            return "Fire Punch";
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
            return r.nextInt(5);
          case 2: 
            return r.nextInt(4)+2;
          default:
            return r.nextInt(4)+1;
        }
    }
  }

  public double getAttackMultiplier(Pokemon p, int atkType){
    if (atkType == 2)
      return battleTable[getType()][p.getType()]; 
    else 
      return 1;
  }
  public int getType() {
    return 0;
  }



}