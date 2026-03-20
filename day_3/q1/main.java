interface Actionable {
  void attack(GameCharacter opponent);

  void defend(int damage);
}

interface Consumable {
  void consume(GameCharacter character);
}

abstract class GameCharacter implements Actionable {
  String name;
  String characterClass;
  private int baseHealth;
  private int health;
  private int attackPower;
  private float attackMultiplier;
  private float defenceReduction;
  private boolean isAlive;

  GameCharacter(String name, String characterClass, int health, int baseHealth, int attackPower) {
    this.name = name;
    this.characterClass = characterClass;
    this.baseHealth = baseHealth;
    this.health = baseHealth;
    this.attackPower = attackPower;
    this.attackMultiplier = 1;
    this.defenceReduction = 0;
    this.isAlive = true;
  }

  /**
   * @return int
   */
  int getBaseHealth() {
    return baseHealth;
  }

  /**
   * @return int
   */
  int getHealth() {
    return health;
  }

  /**
   * @return boolean
   */
  boolean getIsAlive() {
    return isAlive;
  }

  /**
   * @param attackMultiplier
   */
  void setAttackMultiplier(float attackMultiplier) {
    this.attackMultiplier = attackMultiplier;
  }

  /**
   * calculate actual damage by considering damage reduction multiplier and inflicts it
   *
   * @param damage
   */
  public void defend(int damage) {
    float takableDamage = damage - (damage * defenceReduction);

    health -= takableDamage;
    if (health < 0) {
      health = 0;
      isAlive = false;
    }
  }

  /**
   * Heals health upto max health
   *
   * @param amount
   */
  void heal(int amount) {
    if (health + amount >= baseHealth) {
      health = baseHealth;
    } else {
      health += baseHealth;
    }
  }

  /**
   * Inflict damage to opponent character by considering the attack multiplier and opponent's damage
   * reduction
   *
   * @param character
   */
  public void attack(GameCharacter character) {
    float totalPower = attackPower * attackMultiplier;
    float takableDamage = totalPower - character.defenceReduction;

    if (takableDamage >= character.health) {
      character.health = 0;
      character.isAlive = false;
    } else {
      character.health -= takableDamage;
    }
  }

  void upgradeCharacter() {
    baseHealth *= 2;
    attackPower *= 2;
  }

  /**
   * comsume item to boosts stats
   *
   * @param item
   */
  public void useItem(Consumable item) {
    item.consume(this);
  }

  /**
   * prints details about the object
   *
   * @return String
   */
  @Override
  public String toString() {
    return """
    Character Details:
      - Name: %s
      - Class: %s
      - Base Health: %s
      - Health: %s
      - Attack Power: %s
      - Is Alive: %s
    """
        .formatted(name, characterClass, baseHealth, health, attackPower, isAlive);
  }
}

class Warrior extends GameCharacter {
  Warrior(String name) {
    super(name, "warrior", 100, 100, 10);
  }
}

class Mage extends GameCharacter {
  Mage(String name) {
    super(name, "mage", 75, 75, 15);
  }
}

class PowerElixer implements Consumable {
  private int attackMultiplier;

  PowerElixer(int attackMultiplier) {
    this.attackMultiplier = attackMultiplier;
  }

  /**
   * @param consumes elixer to increase attack multiplier
   */
  public void consume(GameCharacter character) {
    character.setAttackMultiplier(attackMultiplier);
  }
}

class HealingPortion implements Consumable {
  private int healAmount;

  HealingPortion(int healAmount) {
    this.healAmount = healAmount;
  }

  /**
   * @param consumes portion to restore health
   */
  public void consume(GameCharacter character) {
    character.heal(healAmount);
  }
}

class GameLauncher {
  public static void main(String[] args) {
    Warrior w1 = new Warrior("w1");
    Mage m1 = new Mage("m1");

    PowerElixer pe = new PowerElixer(2);
    HealingPortion he = new HealingPortion(20);

    System.out.println("Initial Details: ");
    System.out.println(w1);
    System.out.println(m1);

    // simulate the combat mechanism
    int rounds = 0;
    while (rounds <= 3 && w1.getIsAlive() && m1.getIsAlive()) {
      int attacker = (int) Math.round(Math.random());

      if (attacker == 1) {
        w1.attack(m1);

        if (m1.getIsAlive()) {
          m1.attack(w1);
        }

        int getStatBoostType = (int) Math.round(Math.random());
        Consumable portion = getStatBoostType == 0 ? pe : he;
        int portionGetter = (int) Math.round(Math.random() * 100);
        if (portionGetter < 60) {
          m1.useItem(portion);
        } else if (portionGetter < 75) {
          w1.useItem(portion);
        }
      } else {
        m1.attack(w1);

        if (w1.getIsAlive()) {
          w1.attack(m1);
        }

        int getStatBoostType = (int) Math.round(Math.random());
        Consumable portion = getStatBoostType == 0 ? pe : he;
        int portionGetter = (int) Math.round(Math.random() * 100);
        if (portionGetter < 60) {
          w1.useItem(portion);
        } else if (portionGetter < 75) {
          m1.useItem(portion);
        }
      }

      System.out.println("Details at the end of round: ");
      System.out.println(w1);
      System.out.println(m1);
      rounds++;
    }

    if (w1.getIsAlive()) {
      System.out.println("Winner: m1");
    } else if (m1.getIsAlive()) {
      System.out.println("Winner m1");
    } else {
      System.out.println(
          "Winner: "
              + (w1.getHealth() > m1.getHealth()
                  ? "w1"
                  : w1.getHealth() < m1.getHealth() ? "m1" : "None"));
    }
  }
}

/*
  - random attacker
  - random portion usage
  - limited rounds
*/

/*
   - use game loop to make the character move
   - a game loop is like while(running) like thingy
   - if no game loop program just runs once and exits
   - in gameloop for every second refresh the game frmae
*/

/*
   - to find aoe damage
   - make the character position as center
   - calculate euclidean distance
   - if distance <= radius of the attck
   - do aoe damage
   - else no damage

   - in distance formula, rather than taking sqrt, just leave it and square the radius
   - rather than scanning the entire field, use concept of grid to scan effectively
*/

/*
   - use team id or so for not damaging the tema members
*/
