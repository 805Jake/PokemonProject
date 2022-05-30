import java.util.Random;
public abstract class Entity {

  private String name;
  private int hp;
  private int maxHP;

  public Entity(String n, int h, int m) {
   
    this.name = n;
    this.hp = h;
    this.maxHP = m;
    

  }

  public int getHP() {
    return hp;
  }

  public int getMaxHP() {
    return maxHP;
  }
  public void takeDamage(int d) {
    if (this.hp- d < 0) {
      this.hp = 0;
    } else {
      this.hp  = this.hp - d;
    }

  }

  public void heal() {
    this.hp = this.maxHP;
  }
  public String getName() {
    return name;
  }

  public String toString() {
    return new String (name + " HP: " + hp + "/" + maxHP);
  }
}