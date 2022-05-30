import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
import java.util.*;
//import java.util.Collections;

class Main {
    static Scanner in = new Scanner(System.in);
    static int currentArea = 1;
    public static void main(String[] args) throws FileNotFoundException {
    //throws FileNotFoundException {

        String name;
        int firstPokemon;
        ArrayList<String> people = new ArrayList();
        people.add("\nMisty hits you with her bike, Watch it!");
        people.add("\nBrock gives you a potion\nYou might need this potion later ");
        people.add(
                "\nYou run into Officer Jenny.\nHey, I'll give you a pokeball if you keep an eye out for Team Rocket members.");
        people.add("\nIt’s Like My Rattata Is In The Top Percentage Of Rattatas.");
        people.add("\nBrock gives you a potion\nYou might need this potion later.");
        people.add(
                "\nYou run into Officer Jenny.\nHey, I'll give you a pokeball if you keep an eye out for Team Rocket members.");
        people.add("\nA team Rocket member steals a pokeball from you! Darn...");
        people.add("\nA pokeball hits you in the head!\n takes 3 damage, and gets a pokeball!");
        people.add("\nA pokeball hits you in the head!");
       // try{
          Map.getInstance().loadMap(currentArea);
                   // }catch( FileNotFoundException fnf ){
                     // System.out.println("File was not found");
                   // }
       // Map.getInstance().loadMap(currentArea);
        System.out.println("======================================================");
        System.out.println("\nProf. Oak: Hello there new trainer, what is your name?\n");
        name = in.nextLine();
        System.out.println("\nGreat to meet you, " + name);

        System.out.println("\n=== Choose your first Pokemon ===");
        System.out.println("1. Charmander");
        System.out.println("2. Bulbasaur");
        System.out.println("3. Squitrle");
        firstPokemon = getUserInt(3);
        System.out.println("\n======================================================");
        Pokemon first = null;
        switch (firstPokemon) {
            case 1:
              first = PokemonGenerator.getInstance().getPokemon("Charmander");
              break;
            case 2:
              first =PokemonGenerator.getInstance().getPokemon("Bulbasaur");
              break;
            case 3:
              first = PokemonGenerator.getInstance().getPokemon("Squirtle");
              break;
        }

        Trainer user = new Trainer(name, first);

        do {
            System.out.println(user.toString());
            char trigger = ' ';
            switch (mainMenu()) {
                case 1:
                    trigger = user.goNorth();
                    break;
                case 2:
                    trigger = user.goSouth();
                    break;
                case 3:
                    trigger = user.goEast();
                    break;
                case 4:
                    trigger = user.goWest();
                    break;
                case 5:
                    System.out.println("return");
                    return;

            }
            switch (trigger) {
                case 'w': // Wild PKMN battle
                    trainerAttack(user, PokemonGenerator.getInstance().generateRandomPokemon(0), false);
                    break;
                case 'n':
                    System.out.println("There's nothing here...");
                    break;
                case 'i':  // Item
                    Random rand = new Random();
                    int item = rand.nextInt(2);
                    if (item == 0)
                        user.receivePotion();
                    else
                        user.receivePokeball();

                    Map.getInstance().removeCharAtLoc(user.getLocation());
                    break;
                case 'c': // town / city
                    int choice;
                    System.out.print(
                            "You have entered a town, would you like to go to the Store or Pokemon Center?\n1. Store\n2. Pokemon Center\n");

                    choice = getUserInt(2);
                    if (choice == 1) {
                        Store(user);
                    } else if (choice == 2) {
                        user.healAllPokemon();
                    }
                    break;
                case 'p': // event
                    Random r = new Random();
                    int person = r.nextInt(people.size());
                    System.out.println(people.get(person));
                    Map.getInstance().removeCharAtLoc(user.getLocation());
                    break;
                case 'f': //End of map Gym Battle
                    trainerAttack (user, PokemonGenerator.getInstance().generateRandomPokemon(2), true);
                    //gymBattle(user, PokemonGenerator.getInstance().generateRandomPokemon(3), currentArea);
                    break;
                case 'q':
                    System.out.println("\nInvalid move!\n");
                    break;
            }
        } while (true);

    }

    public static int mainMenu() {
        int toReturn;
        System.out.println("\n===================== Main Menu =====================");
        System.out.println("1. Go North");
        System.out.println("2. Go South");
        System.out.println("3. Go East");
        System.out.println("4. Go West");
        System.out.println("5. Quit");
        toReturn = getUserInt(5);
        System.out.println("\n=====================================================\n");

        return toReturn;
    }

    public static Pokemon trainerAttack(Trainer t, Pokemon wild, boolean gym) {
     
      boolean fightOver = false;
        if (!gym) {
          System.out.println("A wild " + wild.getName() + " has appeared!\n");
          do {
              System.out.println(wild.toString());
              System.out.println("What would you like to do?\n1. Fight\n2. Use potion\n3. Throw Poke Ball\n4. Run Away ");
              
              int choice = getUserInt(4);
              int toFight = 1;
              switch (choice) {
                  case 1:
                      int menu;
                      System.out.println("Choose a pokemon");
                      System.out.print(t.getPokemonList());
                      toFight = getUserInt(t.getNumPokemon()) - 1;
                      System.out.println(t.getPokemon(toFight).getName() + ", I choose you!");
                      System.out.println(t.getPokemon(toFight));
                      System.out.println(t.getPokemon(toFight).getAttackTypeMenu());
                      menu = getUserInt(t.getPokemon(toFight).getNumAttackTypeMenuItems());
                      System.out.println(t.getPokemon(toFight).getAttackMenu(menu));
                      int mov = getUserInt(t.getPokemon(toFight).getNumAttackMenuItems(menu));
                      System.out.println(t.getPokemon(toFight).attack(wild, menu, mov));
                      break;
                  case 2:
                      System.out.println("Which pokemon would you like to heal?");
                      System.out.print(t.getPokemonList());
                      int toHealIndex = getUserInt(t.getNumPokemon()) -1;
                      t.usePotion(toHealIndex);
                      break;
                  case 3:
                      System.out.println(t.getName() + " threw a pokeball at " + wild.getName());
                      if (t.catchPokemon(wild)) {
                          fightOver = true;
                          System.out.println("You successfully caught " + wild.getName());

                          while (t.getNumPokemon() > 6) {
                            // select pokemon to remove from party
                            System.out.println("You have to many Pokemon!!\nSelect one to remove from party\n");
                            System.out.print(t.getPokemonList());
                            int pkmnRemover = getUserInt(t.getNumPokemon()) - 1;
                            t.removePokemon(pkmnRemover);
                            /*System.out.println("Are you sure you want to remove " + t.getPokemon(pkmnRemover).getName()
                                    + " from your party (y/n)??");
                            String confirm = in.nextLine().trim();
                            if (confirm.equalsIgnoreCase("y")) {
                                // remove pokemon from party
                                t.removePokemon(pkmnRemover);
                              }*/
                          }
                      } else {
                          System.out.println(wild.getName() + " evaded capture!");
                      }
                      break;
                  case 4:
                      fightOver = true;
                      System.out.println("You ran away Safely!");
                      break;
                }
              
              if (wild.getHP() > 0 && !fightOver) {
                  Random rand = new Random();
                  if (t.getPokemon(toFight).getHP() <= 0) {
                      t.takeDamage(rand.nextInt(5) + 1);
                  } else {
                      int atkType = rand.nextInt(wild.getNumAttackTypeMenuItems()) + 1;
                      int move = rand.nextInt(wild.getNumAttackMenuItems(atkType)) + 1;
                      System.out.println(wild.attack(t.getPokemon(toFight), atkType, move));

                  }

              } else if (wild.getHP() <= 0) {
                  fightOver = true;
                  Map.getInstance().removeCharAtLoc(t.getLocation());
            }
          } while (!fightOver);
          return null;
        } else {
          System.out.println("\n-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-");
	        System.out.println("You Found a gym and entered it.");
	        System.out.println("After sneaking around you found the Gym Leader!\nGet Ready for Battle!!");
          System.out.println("\n\nThe Gym Leader Sends out " + wild.getName() + "!!\n");
          do {
              System.out.println(wild.toString());
              System.out.println("What would you like to do?\n1. Fight\n2. Use potion\n");
              
              int choice = getUserInt(2);
              int toFight = 1;
              switch (choice) {
                  case 1:
                      int menu;
                      System.out.println("Choose a pokemon");
                      System.out.print(t.getPokemonList());
                      toFight = getUserInt(t.getNumPokemon()) - 1;
                      System.out.println(t.getPokemon(toFight).getName() + ", I choose you!");
                      System.out.println(t.getPokemon(toFight));
                      System.out.println(t.getPokemon(toFight).getAttackTypeMenu());
                      menu = getUserInt(t.getPokemon(toFight).getNumAttackTypeMenuItems());
                      System.out.println(t.getPokemon(toFight).getAttackMenu(menu));
                      int mov = getUserInt(t.getPokemon(toFight).getNumAttackMenuItems(menu));
                      System.out.println(t.getPokemon(toFight).attack(wild, menu, mov));
                      break;
                  case 2:
                      System.out.println("Which pokemon would you like to heal?");
                      System.out.print(t.getPokemonList());
                      int toHealIndex = getUserInt(t.getNumPokemon());
                      t.usePotion(toHealIndex);
                      break;
                }
              
              if (wild.getHP() > 0 && !fightOver) {
                  Random rand = new Random();
                  if (t.getPokemon(toFight).getHP() <= 0) {
                      t.takeDamage(rand.nextInt(5) + 1);
                  } else {
                      int atkType = rand.nextInt(wild.getNumAttackTypeMenuItems()) + 1;
                      int move = rand.nextInt(wild.getNumAttackMenuItems(atkType)) + 1;
                      System.out.println(wild.attack(t.getPokemon(toFight), atkType, move));

                  }

              } else if (wild.getHP() <= 0) {
                  fightOver = true;
                   try {
                  if (currentArea == 1) {
                  currentArea++;
                  Map.getInstance().loadMap(currentArea);
                }else if (currentArea == 2) {
                  currentArea++;
                  Map.getInstance().loadMap(currentArea);
                } else {
                  currentArea = 1;
                  Map.getInstance().loadMap(currentArea);
                }
                fightOver = true;
                }catch (Exception e) {
                  System.out.println("FileNotFound");
                }
              }
              int dead = 0;
              for (int i = 0; i < t.getNumPokemon(); i++) {
                if (t.getPokemon(i).getHP() <= 0) {
                  dead++;
                }
              }
              if (dead == t.getNumPokemon()) {
                System.out.println("\n\nYou are all out of Pokemon!\nCome back when your Pokemon are stronger!");
                System.out.println("\n-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-\n");
               
              }
                

          } while (!fightOver);
          return null;
        }
    }

    /* public static Pokemon gymBattle(Trainer t, Pokemon wild) {

        boolean fightOver = false;
        System.out.println("You Found a gym and entered it.");
        System.out.println("After sneaking around you found the Gym Leader!\nGet Ready for Battle!!");
        System.out.println("\n\nThe Gym Leader Sends out " + wild.getName() + "!!\n");
        do {
            System.out.println(wild.toString());
            System.out.println("What would you like to do?\n1. Fight\n2. Use potion\n3. Throw Poke Ball\n4. Run Away ");

            int choice = getUserInt(4);
            int toFight = 1;
            switch (choice) {
                case 1:
                    int menu;
                    System.out.println("Choose a pokemon");
                    System.out.print(t.getPokemonList());
                    toFight = getUserInt(t.getNumPokemon()) - 1;
                    System.out.println(t.getPokemon(toFight).getName() + ", I choose you!");
                    System.out.println(t.getPokemon(toFight));
                    System.out.println(t.getPokemon(toFight).getAttackTypeMenu());
                    menu = getUserInt(t.getPokemon(toFight).getNumAttackTypeMenuItems());
                    System.out.println(t.getPokemon(toFight).getAttackMenu(menu));
                    int mov = getUserInt(t.getPokemon(toFight).getNumAttackMenuItems(menu));
                    t.getPokemon(toFight).attack(wild, menu, mov);
                    break;
                case 2:
                    System.out.println("Which pokemon would you like to heal?");
                    System.out.print(t.getPokemonList());
                    int toHealIndex = getUserInt(t.getNumPokemon());
                    t.usePotion(toHealIndex);
                    break;
                case 3:
                    System.out.println("You cannot steal people's pokemons!!");
                    break;
                case 4:
                    System.out.println("You cannot run away from a trainer battle...");
            }
            if (wild.getHP() > 0 && !fightOver) {
                Random rand = new Random();
                if (t.getPokemon(toFight).getHP() <= 0) {
                    int deadpokemon = 0; // counts how much fainted pokemons in party
                    for (int i = 0; i < getUserInt(t.getNumPokemon()); i++) {
                        if (t.getPokemon(i).getHP() <= 0) {
                            deadpokemon++;
                        }
                    }
                    if (deadpokemon == getUserInt(t.getNumPokemon())) { // if the amount of fainted pokeons is
                                                                        // equivelant to how many pokemmons are in the
                                                                        // party, then the battle is over and the
                                                                        // trainer looses.
                        System.out
                                .println("\n\nYou are all out of Pokemon!\nCome back when your Pokemon are stronger!");
                        fightOver = true;
                        // Map.getInstance().removeCharAtLoc(t.getLocation());
                        // Map.getInstance().loadMap(currentArea);
                        return 0;
                    }
                    t.takeDamage(rand.nextInt(5) + 1);
                } else {
                    int atkType = rand.nextInt(wild.getNumAttackTypeMenuItems()) + 1;
                    int move = rand.nextInt(wild.getNumAttackMenuItems(atkType)) + 1;
                    wild.attack(t.getPokemon(toFight), atkType, move);

                }

            } else if (wild.getHP() <= 0) {
                fightOver = true;
                Map.getInstance().removeCharAtLoc(t.getLocation());
            }
        } while (!fightOver);
        return 1;
    } */


	public static Pokemon gymBattle(Trainer t, Pokemon wild, int currentArea){
    
     boolean fightOver = false;
      System.out.println("\n-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-");
	  System.out.println("You Found a gym and entered it.");
	  System.out.println("After sneaking around you found the Gym Leader!\nGet Ready for Battle!!");
      System.out.println("\n\nThe Gym Leader Sends out " + wild.getName() + "!!\n");
      do {  
        System.out.println(wild.toString());
        System.out.println("What would you like to do?\n1. Fight\n2. Use potion\n3. Throw Poke Ball\n4. Run Away ");
        
        int choice = getUserInt(4);
        int toFight = 1;
        switch (choice){
          case 1:      
            int menu;
            System.out.println("Choose a pokemon");
            System.out.print(t.getPokemonList());
            toFight = getUserInt(t.getNumPokemon())-1;      
            System.out.println(t.getPokemon(toFight).getName() + ", I choose you!");
            System.out.println(t.getPokemon(toFight));
            System.out.println(t.getPokemon(toFight).getAttackTypeMenu());
            menu = getUserInt(t.getPokemon(toFight).getNumAttackTypeMenuItems());
            System.out.println(t.getPokemon(toFight).getAttackMenu(menu));
            int mov = getUserInt(t.getPokemon(toFight).getNumAttackMenuItems(menu));
            t.getPokemon(toFight).attack (wild, menu, mov);
            break;
          case 2:
            System.out.println("Which pokemon would you like to heal?");
            System.out.print(t.getPokemonList());
            int toHealIndex = getUserInt(t.getNumPokemon());
            t.usePotion(toHealIndex);
            break;
          case 3:
            System.out.println("You cannot steal people's pokemons!!");
            break;
          case 4: 
            System.out.println("You cannot run away from a trainer battle...");
            break;
        }
        
        if (wild.getHP() > 0 && !fightOver ) { 
            Random rand = new Random();
            if (t.getPokemon(toFight).getHP() <= 0) {
                int deadpokemon = 0; // counts how much fainted pokemons in party
                for(int i = 0; i < getUserInt(t.getNumPokemon()); i++) {
                    if(t.getPokemon(i).getHP() <= 0){
                        deadpokemon++;
                    }
                }
                if (deadpokemon == getUserInt(t.getNumPokemon())){ //if the amount of fainted pokeons is equivelant to how many pokemmons are in the party, then the battle is over and the trainer looses.
                    System.out.println("\n\nYou are all out of Pokemon!\nCome back when your Pokemon are stronger!");
                    System.out.println("\n-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-\n");
                    fightOver = true;
                    //Map.getInstance().removeCharAtLoc(t.getLocation());
                    try{
                    Map.getInstance().loadMap(currentArea);
                    }catch( FileNotFoundException fnf ){
                      System.out.println("File was not found");
                    }
                    return null;
                }
            t.takeDamage(rand.nextInt(5)+1);
            }else {
            int atkType = rand.nextInt(wild.getNumAttackTypeMenuItems())+1;
            int move = rand.nextInt(wild.getNumAttackMenuItems(atkType))+1;
            wild.attack(t.getPokemon(toFight),atkType,move); 
            }

        } else if (wild.getHP() <= 0) {
          fightOver = true;
          Map.getInstance().removeCharAtLoc(t.getLocation());
        }
        }while (!fightOver);
	    switch(currentArea){
            case 1:
            currentArea++;
            try{
            Map.getInstance().loadMap(currentArea);
            }catch( FileNotFoundException fnf){
              System.out.println("File was not found");
            }
            break;
            case 2:
            currentArea++;
            try{
            Map.getInstance().loadMap(currentArea);
            }catch( FileNotFoundException fnf){
              System.out.println("File was not found");
            }
            break;
            case 3:
            currentArea = 1;
            try{
            Map.getInstance().loadMap(currentArea);
            }catch( FileNotFoundException fnf){
              System.out.println("File was not found");
            }
            break;
			//Map.getInstance().reveal(t.getLocation());
        }
        System.out.println("\n-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-");
      return null;
    }


    public static void Store(Trainer t) {

        int choice;
        do {
            System.out.println("= Welcome to the Store! What would you like to purchase? =");
            System.out.print("1. potions: $5\t2. PokeBalls $3\n3. Exit\n");

            choice = getUserInt(3);

            if (choice == 1) {
                if (t.getMoney() >= 5) {
                    t.receivePotion();
                    t.spendMoney(5);
                    System.out.println("= Here's your Potion =");
                } else {
                    t.spendMoney(5);
                }
            }
            if (choice == 2) {
                if (t.getMoney() >= 3) {
                    t.receivePokeball();
                    t.spendMoney(3);
                    System.out.println("= Here's your Pokeball =");
                } else {
                    t.spendMoney(3);
                }
            }
        } while (choice != 3);
        System.out.println("================= Thank You, Come Again! =================");
    }

    private static int getUserInt(int bound) {
        do {
            try {
                int toReturn = in.nextInt();
                if (toReturn <= bound && toReturn > 0)
                    return toReturn;
            } catch (Exception e) {
            }
            System.out.println("xx Invalid input xx");
        } while (true);
    }
}