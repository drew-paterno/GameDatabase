
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite for GameList class, tests all the public methods
 * 
 * @author Jiaming Zhang
 *
 */
public class TestSuite {
  GameList testList;

  @BeforeEach
  /**
   * create an empty list
   */
  public void createList() {
    testList = new GameList();
  }

  @Test
  /**
   * test case for put() tests inserting regular key, null key, duplicate key, also makes sure size
   * is updated correctly
   */
  public void testPut() {
    // add null
    try {
      testList.put(null);
      fail("null game added");
    } catch (Exception e1) {

    }
    // add game with null name
    try {
      testList.put(null);
      fail("null game name added");
    } catch (Exception e1) {

    }
    // add a new game
    if (!testList.put(
        new Game("Counter-Strike", "", 0, 0, false, false, false, false, false, false, "", "")))
      fail("cannot add new game");
    assertEquals(testList.numberOfGames(), 1);// check size
    // add a duplicated game
    try {
      if (testList.put(
          new Game("Counter-Strike", "", 0, 0, false, false, false, false, false, false, "", "")))
        testList.allGames();
      fail("dupicated game added");
    } catch (IllegalArgumentException e1) {

    }

    // add 1000 new games
    for (int i = 0; i < 1000; ++i) {
      if (!testList
          .put(new Game("" + i, "", 0, 0, false, false, false, false, false, false, "", "")))
        fail("fail to add 1000 games");
    }
    assertEquals(testList.numberOfGames(), 1001);// check size
  }

  @Test
  /**
   * test case for get() tests getting regular key, nonexistent key, null key
   */
  public void testGet() {
    testList.put(
        new Game("Counter-Strike", "", 0, 0, false, false, false, false, false, false, "", ""));
    // get an existing game
    if (!testList.get("Counter-Strike").getName().equals("Counter-Strike"))
      fail("get() returns the wrong object");
    // get null name
    if (testList.get(null) != null)
      fail("null massage not shown");
    // get nonexistent game
    if(!testList.get("alalal").getName().equals(""))
      fail("non-existed game is returned");
  }


  @Test
  /**
   * test case for remove() tests deleting regular key, null key, and nonexistent key, also makes
   * sure size is updated correctly
   */
  public void testRemove() {
    testList.put(
        new Game("Counter-Strike", "", 0, 0, false, false, false, false, false, false, "", ""));
    // remove null name
    if (testList.remove(null) != null)
      fail("null name not handled");
    // remove a nonexistent game
    if (testList.remove("alalala") != null)
      fail("nonexistent game is removed");
    // remove an existing game
    if (!testList.remove("Counter-Strike").getName().equals("Counter-Strike"))
      fail("remove() returns the wrong object");
    // remove 15 games
    for (int i = 0; i < 15; ++i) {
      testList.put(new Game("" + i, "", 0, 0, false, false, false, false, false, false, "", ""));
    }
    for (int i = 0; i < 15; ++i) {
      assertEquals(testList.remove("" + i).getName(), "" + i);
    }
    assertEquals(testList.numberOfGames(), 0); // make sure remove() updates size correctly
  }

  @Test
  /**
   * test case for gameOfGenre() tests 2 regular genres and a genre with no games
   */
  public void testGameOfGenre() {
    // test 100 Action games
    for (int i = 0; i < 100; ++i) {
      testList.put(new Game("" + (char) i, "", 0, 0, false, false, false, false, false, false,
          "Action", ""));
    }
    String actionList = testList.gameOfGenre("Action");
    // System.out.println(actionList);
    for (int i = 0; i < 100; ++i) {
      if (!actionList.contains("" + (char) i))
        fail("gameOfGenre(Action) doesn't contain all Action games"); // make sure all 100 unique
                                                                      // chars are in the list
    }

    // test 100 games of another genre
    for (int i = 100; i < 200; ++i) {
      testList.put(
          new Game("" + (char) i, "", 0, 0, false, false, false, false, false, false, "lol", ""));
    }
    String lolList = testList.gameOfGenre("lol");
    for (int i = 100; i < 200; ++i) {
      if (!lolList.contains("" + (char) i))
        fail("gameOfGenre(lol) doesn't contain all lol games");
    }

    // test empty genre
    assertEquals(testList.gameOfGenre("hehe"), "no game of this genre in the list");


  }

  @Test
  /**
   * test case for numberOfGames() makes sure the size is correctly returned at any time
   */
  public void testNumberOfGames() {
    // empty tree
    assertEquals(testList.numberOfGames(), 0);
    // add 10 games
    for (int i = 0; i < 10; ++i) {
      testList.put(new Game("" + i, "", 0, 0, false, false, false, false, false, false, "", ""));
    }
    assertEquals(testList.numberOfGames(), 10);
    // add another 10 games
    for (int i = 10; i < 20; ++i) {
      testList.put(new Game("" + i, "", 0, 0, false, false, false, false, false, false, "", ""));
    }
    assertEquals(testList.numberOfGames(), 20);
    // remove 5 games
    for (int i = 0; i < 5; ++i) {
      testList.remove("" + i);
    }
    assertEquals(testList.numberOfGames(), 15);
    // remove another 15 games
    for (int i = 5; i < 20; ++i) {
      testList.remove("" + i);
    }
    assertEquals(testList.numberOfGames(), 0);
  }

  @Test
  /**
   * test case for allGames() makes sure the String returned by allGames() contains all the games
   */
  public void testAllGames() {
    // test 100 games
    for (int i = 0; i < 100; ++i) {
      testList
          .put(new Game("" + (char) i, "", 0, 0, false, false, false, false, false, false, "", ""));
    }
    String gameList = testList.allGames();
    for (int i = 0; i < 100; ++i) {
      if (!gameList.contains("" + (char) i))
        fail("Not all games printed");
    }
    // test 100 additional games
    for (int i = 100; i < 200; ++i) {
      testList.put(
          new Game("" + (char) i, "", 0, 0, false, false, false, false, false, false, "lol", ""));
    }
    gameList = testList.allGames();
    for (int i = 100; i < 200; ++i) {
      if (!gameList.contains("" + (char) i))
        fail();
    }

  }

  @Test
  /**
   * test case for clear() test clearing an empty list, and a list with 1000 games
   */
  public void testClear() {
    // clear a list with 1000 games
    for (int i = 0; i < 1000; ++i) {
      testList.put(new Game("" + i, "", 0, 0, false, false, false, false, false, false, "", ""));
    }
    assertEquals(testList.clear(), 1000);
    assertEquals(testList.numberOfGames(), 0);
    // clear an empty list
    assertEquals(testList.clear(), 0);

  }

}
