
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TestGameSuite {
  // The next two test methods are for Data Wrangler
  @Test
  /**
   * This method aims to test whether the load() method in the GameLoader class can function
   * correctly.
   * 
   */
  public void testLoad() {
    ArrayList<Game> testList = new ArrayList<Game>();
    try {
      testList = GameLoader.load("src/games.csv");
    } catch (Exception e) {

    }
    // test whether all games are loaded
    if (testList.size() != 13357) {
      fail("The load() is not functioning correctly!");
    }
  }

  @Test
  /**
   * This method aims to test whether the Game class can function correctly.
   * 
   */
  public void testGame() {
    // test the default constructor with toString()
    Game testGame1 = new Game();
    if (!testGame1.getName().equals("") || !testGame1.getReleaseDate().equals("")
        || testGame1.getNumOwners() != 0 || testGame1.getNumPlayers() != 0
        || testGame1.getIsFree() != false || testGame1.getIsSinglePlayer() != false
        || testGame1.getIsMultiPlayer() != false || testGame1.getIsCOOP() != false
        || testGame1.getIsMMO() != false || testGame1.getIsVRCompatible() != false
        || !testGame1.getGenre().equals("") || !testGame1.getAbout().equals("")) {
      fail("The default constructor of Game is not functioning properly!");
    }
    // test the constructor with name as input
    Game testGame2 = new Game("Fall Guys!");
    if (!testGame2.getName().equals("Fall Guys!") || !testGame2.getReleaseDate().equals("")
        || testGame2.getNumOwners() != 0 || testGame2.getNumPlayers() != 0
        || testGame2.getIsFree() != false || testGame2.getIsSinglePlayer() != false
        || testGame2.getIsMultiPlayer() != false || testGame2.getIsCOOP() != false
        || testGame2.getIsMMO() != false || testGame2.getIsVRCompatible() != false
        || !testGame2.getGenre().equals("") || !testGame2.getAbout().equals("")) {
      fail("The constructor of Game with name as input is not functioning properly!");
    }
    // test the constructor with all parameters given
    Game testGame3 = new Game("Dota 2", "Jul 9 2013", 90687580, 90687580, true, false, true, true,
        false, false, "Action",
        "Dota is a competitive game of action and strategy played both professionally and casually by millions of passionate fans worldwide. Players pick from a pool of over a hundred heroes forming two teams of five players. Radiant heroes then battle their Dire counterparts to control a gorgeous fantasy landscape waging campaigns of cunning stealth and outright warfare.Irresistibly colorful on the surface Dota is a game of infinite depth and complexity. Every hero has an array of skills and abilities that combine with the skills of their allies in unexpected ways to ensure that no game is ever remotely alike. This is one of the reasons that the Dota phenomenon has continued to grow. Originating as a fan-made Warcraft 3 modification Dota was an instant underground hit. After coming to Valve the original community developers have bridged the gap to a more inclusive audience so that the rest of the world can experience the same core gameplay but with the level of polish that only Valve can provide.Get a taste of the game that has enthralled millions.");
    if (!testGame3.getName().equals("Dota 2") || !testGame3.getReleaseDate().equals("Jul 9 2013")
        || testGame3.getNumOwners() != 90687580 || testGame3.getNumPlayers() != 90687580
        || testGame3.getIsFree() != true || testGame3.getIsSinglePlayer() != false
        || testGame3.getIsMultiPlayer() != true || testGame3.getIsCOOP() != true
        || testGame3.getIsMMO() != false || testGame3.getIsVRCompatible() != false
        || !testGame3.getGenre().equals("Action") || !testGame3.getAbout().equals(
            "Dota is a competitive game of action and strategy played both professionally and casually by millions of passionate fans worldwide. Players pick from a pool of over a hundred heroes forming two teams of five players. Radiant heroes then battle their Dire counterparts to control a gorgeous fantasy landscape waging campaigns of cunning stealth and outright warfare.Irresistibly colorful on the surface Dota is a game of infinite depth and complexity. Every hero has an array of skills and abilities that combine with the skills of their allies in unexpected ways to ensure that no game is ever remotely alike. This is one of the reasons that the Dota phenomenon has continued to grow. Originating as a fan-made Warcraft 3 modification Dota was an instant underground hit. After coming to Valve the original community developers have bridged the gap to a more inclusive audience so that the rest of the world can experience the same core gameplay but with the level of polish that only Valve can provide.Get a taste of the game that has enthralled millions.")) {
      fail("The constructor of Game with all parameter as inputs is not functioning properly!");
    }
    // test the mutator methods
    Game testGame4 = new Game();
    testGame4.setName("Game King");
    testGame4.setReleaseDate("July 2020");
    testGame4.setNumOwners(50000);
    testGame4.setNumPlayers(50000);
    testGame4.setIsFree(true);
    testGame4.setIsSinglePlayer(true);
    testGame4.setIsMultiPlayer(true);
    testGame4.setIsCOOP(true);
    testGame4.setIsMMO(true);
    testGame4.setIsVRCompatible(true);
    testGame4.setGenre("NonGame");
    testGame4.setAbout("This game does not exist! Lol!");
    if (!testGame4.getName().equals("Game King") || !testGame4.getReleaseDate().equals("July 2020")
        || testGame4.getNumOwners() != 50000 || testGame4.getNumPlayers() != 50000
        || testGame4.getIsFree() != true || testGame4.getIsSinglePlayer() != true
        || testGame4.getIsMultiPlayer() != true || testGame4.getIsCOOP() != true
        || testGame4.getIsMMO() != true || testGame4.getIsVRCompatible() != true
        || !testGame4.getGenre().equals("NonGame")
        || !testGame4.getAbout().equals("This game does not exist! Lol!")) {
      fail("The constructor of Game with all parameter as inputs is not functioning properly!");
    }
  }

  // The next seven test methods are for Back End test
  @Test
  /**
   * This method aims to test whether the put() method can function correctly.
   * 
   */
  public void testPut() {
    // situation 1: input for put() is null
    GameList testList1 = new GameList("src/games.csv");
    Game test = new Game(null);
    // sub-situation 1: the input is null
    try {
      testList1.put(null);
      fail("The method does not throw NullPointerException when the input is null!");
    } catch (NullPointerException e1) {
    }
    // sub-situation 2: a parameter of the input is null
    try {
      testList1.put(test);
      fail("The method does not throw NullPointerException when a parameter of the input is null!");
    } catch (NullPointerException e2) {
    }
    // situation 2: input for put() is not null
    GameList testList2 = new GameList("src/games.csv");
    Game testGame = new Game("This Game does not exist!");
    testList2.put(testGame);
    if (!testList2.get("This Game does not exist!").equals(testGame)) {
      fail("The game is not added correctly!");
    }
  }

  @Test
  /**
   * This method aims to test whether the get() method can function correctly.
   * 
   */
  public void testGet() {
    GameList testList1 = new GameList("src/games.csv");
    Game test = new Game("Dota 2", "Jul 9 2013", 90687580, 90687580, true, false, true, true, false,
        false, "Action",
        "Dota is a competitive game of action and strategy played both professionally and casually by millions of passionate fans worldwide. Players pick from a pool of over a hundred heroes forming two teams of five players. Radiant heroes then battle their Dire counterparts to control a gorgeous fantasy landscape waging campaigns of cunning stealth and outright warfare.Irresistibly colorful on the surface Dota is a game of infinite depth and complexity. Every hero has an array of skills and abilities that combine with the skills of their allies in unexpected ways to ensure that no game is ever remotely alike. This is one of the reasons that the Dota phenomenon has continued to grow. Originating as a fan-made Warcraft 3 modification Dota was an instant underground hit. After coming to Valve the original community developers have bridged the gap to a more inclusive audience so that the rest of the world can experience the same core gameplay but with the level of polish that only Valve can provide.Get a taste of the game that has enthralled millions.");
    // situation 1: try to get a null from the tree

    if (testList1.get(null) != null)
      fail("The method does not throw NoSuchElementException when the input is null!");

    // situation 2: try to get a existing game from the tree
    if (!testList1.get("Dota 2").toString().equals(test.toString())) {
      fail("The get() fails to get target game from the tree!");
    }
  }

  @Test
  /**
   * This method aims to test whether the gameOfGenre() method can function correctly.
   * 
   */
  public void testGameOfGenre() {
    // create a new GameList for testing
    GameList testList = new GameList();
    // create nine distinct games for insertion
    Game one =
        new Game("Fall Guys!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game two =
        new Game("Call of Duty", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game three =
        new Game("Heavy Rain", "", 0, 0, false, false, false, false, false, false, "Adventure", "");
    Game four =
        new Game("Among Us!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game five =
        new Game("Death Strand", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game six = new Game("Fallout 4", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game seven = new Game("Devil May Cry 5", "", 0, 0, false, false, false, false, false, false,
        "Action", "");
    Game eight =
        new Game("Dishonored 2", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game nine =
        new Game("Animal Party", "", 0, 0, false, false, false, false, false, false, "Action", "");
    // make a GameList with nine different games for test gameOfGenre()
    testList.put(one);
    testList.put(two);
    testList.put(three);
    testList.put(four);
    testList.put(five);
    testList.put(six);
    testList.put(seven);
    testList.put(eight);
    testList.put(nine);
    // test whether the gameOfGenre() can return the desired games
    if (!testList.gameOfGenre("Action")
        .equals("Animal Party, Call of Duty, Devil May Cry 5, Dishonored 2 ")) {
      fail("The gameOfGenre() does not work properly!");
    }
  }

  @Test
  /**
   * This method aims to test whether the remove() method can function correctly.
   * 
   */
  public void testRemove() {
    // create a new GameList for testing
    GameList testList = new GameList();
    // create nine distinct games for insertion
    Game one =
        new Game("Fall Guys!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game two =
        new Game("Call of Duty", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game three =
        new Game("Heavy Rain", "", 0, 0, false, false, false, false, false, false, "Adventure", "");
    Game four =
        new Game("Among Us!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game five =
        new Game("Death Strand", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game six = new Game("Fallout 4", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game seven = new Game("Devil May Cry 5", "", 0, 0, false, false, false, false, false, false,
        "Action", "");
    Game eight =
        new Game("Dishonored 2", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game nine =
        new Game("Animal Party", "", 0, 0, false, false, false, false, false, false, "Action", "");
    // make a GameList with nine different games and then remove two games to test remove()
    testList.put(one);
    testList.put(two);
    testList.put(three);
    testList.put(four);
    testList.put(five);
    testList.put(six);
    testList.put(seven);
    testList.put(eight);
    testList.put(nine);
    testList.remove("Fall Guys!");
    testList.remove("Death Strand");
    // check whether the target games are removed
    if (!testList.allGames().equals(
        "Among Us!, Animal Party, Call of Duty, Devil May Cry 5, Dishonored 2, Fallout 4, Heavy Rain")) {
      fail("Something wrong with remove()!");
    }
    // check whether the number of games after remove is correct
    if (testList.numberOfGames() != 7) {
      fail("After removing, the number of games are wrong!");
    }
  }

  @Test
  /**
   * This method aims to test whether the numberOfGames() method can function correctly.
   * 
   */
  public void testNumberOfGames() {
    // create a new GameList for testing
    GameList testList = new GameList();
    // create nine distinct games for insertion
    Game one =
        new Game("Fall Guys!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game two =
        new Game("Call of Duty", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game three =
        new Game("Heavy Rain", "", 0, 0, false, false, false, false, false, false, "Adventure", "");
    Game four =
        new Game("Among Us!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game five =
        new Game("Death Strand", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game six = new Game("Fallout 4", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game seven = new Game("Devil May Cry 5", "", 0, 0, false, false, false, false, false, false,
        "Action", "");
    Game eight =
        new Game("Dishonored 2", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game nine =
        new Game("Animal Party", "", 0, 0, false, false, false, false, false, false, "Action", "");
    // make a GameList with nine different games
    testList.put(one);
    testList.put(two);
    testList.put(three);
    testList.put(four);
    testList.put(five);
    testList.put(six);
    testList.put(seven);
    testList.put(eight);
    testList.put(nine);
    // check whether numberOfGames() returns correct result
    if (testList.numberOfGames() != 9) {
      fail("The numberOfGames() fails to return the correct result!");
    }
  }

  @Test
  /**
   * This method aims to test whether the allGames() method can function correctly.
   * 
   */
  public void testAllGames() {
    // create a new GameList for testing
    GameList testList = new GameList();
    // create nine distinct games for insertion
    Game one =
        new Game("Fall Guys!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game two =
        new Game("Call of Duty", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game three =
        new Game("Heavy Rain", "", 0, 0, false, false, false, false, false, false, "Adventure", "");
    Game four =
        new Game("Among Us!", "", 0, 0, false, false, false, false, false, false, "Casual", "");
    Game five =
        new Game("Death Strand", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game six = new Game("Fallout 4", "", 0, 0, false, false, false, false, false, false, "RPG", "");
    Game seven = new Game("Devil May Cry 5", "", 0, 0, false, false, false, false, false, false,
        "Action", "");
    Game eight =
        new Game("Dishonored 2", "", 0, 0, false, false, false, false, false, false, "Action", "");
    Game nine =
        new Game("Animal Party", "", 0, 0, false, false, false, false, false, false, "Action", "");
    // make a GameList with nine different games
    testList.put(one);
    testList.put(two);
    testList.put(three);
    testList.put(four);
    testList.put(five);
    testList.put(six);
    testList.put(seven);
    testList.put(eight);
    testList.put(nine);
    // check whether allGames() returns the correct result
    if (!testList.allGames().equals(
        "Among Us!, Animal Party, Call of Duty, Death Strand, Devil May Cry 5, Dishonored 2, Fall Guys!, Fallout 4, Heavy Rain")) {
      fail("The allGames() is not working properly.");
    }
  }

  @Test
  /**
   * This method aims to test whether the clear() method can function correctly.
   * 
   */
  public void testClear() {
    // create a new GameList and load games.csv
    GameList testList1 = new GameList("src/games.csv");
    testList1.clear();
    // check whether the root is null and number of games is 0 after check()
    if (testList1.root != null || testList1.numberOfGames() != 0) {
      fail("Something wrong with the clear()!");
    }
    // make sure no game is in the list by utilizing get()
    if (!testList1.get("Dota 2").getName().equals(""))
      fail("Something wrong with the clear()!!!");
  }
}
