
import java.util.Scanner;
import java.lang.IllegalArgumentException;

public class GameFrontEnd {


  /*
   * Helper method for the main method, simply so that I'm not clogging up as much space with it.
   */
  private static void printMenu() {
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("            Game Database Menu:              ");
    System.out.println("      Enter a number to give a command       ");
    System.out.println("1) List the names of all games");
    System.out.println("2) List the name of all games from a specified genre");
    System.out.println("3) Add a game to the database");
    System.out.println("4) Find a game");
    System.out.println("5) Clear the list of games");
    System.out.println("6) Remove a game");
    System.out.println("7) List number of games");
    System.out.println("8) Exit Program");
  }

  /*
   * Helper method for the main method. This simply gets a yes or no answer based on the first
   * character from a string.
   * 
   */
  private static boolean getBooleanFromString(String answerString) throws IllegalArgumentException {
    char answerChar = answerString.toLowerCase().charAt(0);
    switch (answerChar) {
      case ('y'):
        return true;
      case ('n'):
        return false;
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * This main method presents a basic UI for the user to interact with. It will print a menu and
   * ask the user for a command 1-8. It will then read that command and execute the given action. If
   * additional information is necessary then the program will prompt the user for the necessary
   * information.
   * @param args unused
   */
  public static void main(String[] args) {
    // create a gamelist with all of the lists games in it.
    Scanner sc = new Scanner(System.in);
    GameList gameList;
    System.out.println("Would you like a premade list of games, or a blank list? (Type \"premade\" or \"blank\")");
    
    //THIS FILEPATH MAY NEED TO BE CHANGED!!!! FIXME
    if(sc.nextLine().toLowerCase().equals("premade"))
      gameList = new GameList("src/games.csv");
    else
      gameList = new GameList();

    boolean exitCondition = false;

    do {
      printMenu();

      int command = 0;
      while (command < 1 || command > 8) {
        Character temp = sc.next().charAt(0);
        while(!Character.isDigit(temp) || ((int)temp - 48 < 1 || (int)temp - 48 > 8)) {
            System.out.println("Please input a number 1-8");
            temp = sc.next().charAt(0);
        }
        command = (int)temp - 48;
        sc.nextLine();
      }

      switch (command) {
        
     // List names of all games
        case 1:
          gameList.display("all");
          break;
          
       // List the name of all games from a certain genre.
        case 2:
          System.out.println(
              "Which Genre would you like to list? (pick from \"NonGame\", \"Indie\", \"Action\", \"Adventure\", \"Casual\", \"Strategy\",\r\n"
                  + "      \"RPG\", \"Simulation\", \"EarlyAccess\", \"FreeToPlay\", \"Sports\", \"Racing\", \"MassivelyMultiplayer\"");
          String chosenGenre = sc.nextLine();
          System.out.println("Games from genre " + chosenGenre + ": ");
          gameList.display(chosenGenre);
          break;
          
       // Add a game to the database
        case 3:
          Game game = new Game();

          System.out.println("What is the games name?");
          game.setName(sc.nextLine());

          System.out.println("What is its release date? (Input as mm/dd/yyyy)");
          game.setReleaseDate(sc.nextLine());

          while (true) {
            System.out.println("How many people own this game? (Enter an integer number)");
            try {
              game.setNumOwners(sc.nextInt());
              sc.nextLine();
              break;
            } catch (java.util.InputMismatchException e) {
              sc.nextLine();
              continue;
            }
          }

          while (true) {
            System.out.println("How many people are currently playing this game?");
            try {
              game.setNumOwners(sc.nextInt());
              sc.nextLine();
              break;
            } catch (java.util.InputMismatchException e) {
              sc.nextLine();
              continue;
            }
          }

          // Is it free?
          while (true) { // This will break once the string entered starts with a y or n.
            System.out.println("Is this game free? (Type y/n)");
            String answer = sc.nextLine();
            try {
              game.setIsFree(getBooleanFromString(answer));
              break;
            } catch (IllegalArgumentException e) {
              continue;
            }
          }


          // Is the game cooperative?
          while (true) { // This will break once the string entered starts with a y or n.
            System.out.println("Is this game cooperative? (Type y/n)");
            String answer = sc.nextLine();
            try {
              game.setIsCOOP(getBooleanFromString(answer));
              break;
            } catch (IllegalArgumentException e) {
              continue;
            }
          }


          // Is it an mmo?
          while (true) { // This will break once the string entered starts with a y or n.
            System.out.println("Is this game an MMO? (Type y/n)");
            String answer = sc.nextLine();
            try {
              game.setIsMMO(getBooleanFromString(answer));
              break;
            } catch (IllegalArgumentException e) {
              continue;
            }
          }


          // Is the game VR compatible?
          while (true) { // This will break once the string entered starts with a y or n.
            System.out.println("Is this game VR compatible? (Type y/n)");
            String answer = sc.nextLine();
            try {
              game.setIsVRCompatible(getBooleanFromString(answer));
              break;
            } catch (IllegalArgumentException e) {
              continue;
            }
          }

          // What is the genre?
          System.out.println("What is the games genre?");
          game.setGenre(sc.nextLine());

          gameList.put(game);
          System.out.println("Game succesfully added!");
          break;
          
        //Find a game
        case 4: 
          System.out.println("What is the name of the game you're looking for?");
          String name = sc.nextLine();
          if (gameList.get(name).getName().equals("")) {
            System.out.println("Could not find the game you're looking for!");
            break;
          }
          System.out.println(gameList.get(name));
          break;
          
       // Clear the list of games.
        case 5: 
          System.out.println("Cleared " + gameList.clear() + " items.");
          break;
          
       // Remove a game.
        case 6:
          System.out.println("What is the name of the game you'd like to remove?");
          String gameName = sc.nextLine();
          if (gameList.remove(gameName) != null) {
            System.out.println("Succesfully removed " + gameName + ".");
            break;
          }
          System.out.println("Could not remove " + gameName + ".");
          break;
          
          //Print the number of games.
        case 7:
          System.out.println("Number of games: " + gameList.numberOfGames());
          break;
          
          //Exit program
        case 8:
          System.out.println("Goodbye!");
          exitCondition = true;
          continue;
      }
      System.out.println("Press enter to continue");
      sc.nextLine();
      
    //End of loop
    } while (!exitCondition);
    sc.close();
  }
}
