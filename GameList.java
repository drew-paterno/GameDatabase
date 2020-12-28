
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * GameList is the back end class for RedBlackTree application with game searching system. The class
 * extends RedBlackTree and implements GameADT.
 * 
 */
public class GameList extends RedBlackTree<Game> implements GameADT {
  private int duplicate;
  private int size; // Size of the tree, number of games

  /**
   * constructor with input of fileName, store all games that originally in that file.
   * 
   * @param fileName the name of file with games
   */
  public GameList(String fileName) {
    this.size = 0;
    this.duplicate = 0;
    ArrayList<Game> gameInfo = new ArrayList<Game>();
    
    try {
      gameInfo = GameLoader.load(fileName);
    } catch (java.io.FileNotFoundException e) {
      System.out.println("File not found.");
    }

    for (Game i : gameInfo) {
      try {
        this.put(i);
      } catch (IllegalArgumentException e1) {
        duplicate++;
      }
    }
  }

  /**
   * constructor with 0 initial size, nothing added.
   */
  public GameList() {
    this.size = 0;
    this.duplicate = 0;
  }

  /**
   * Add a new game with object type Game
   * 
   * @param game the game to be stored
   * @return true if game is successfully added false if the game is already exist or the input is
   *         null
   * @throws NullPointerException if Game input is null
   */
  @Override
  public boolean put(Game game) throws NullPointerException {
    if (game == null) {
      throw new NullPointerException("Game should not be null");
    }
    super.insert(game);
    this.size++;
    return true;

  }

  /**
   * return the game names of the same input genre
   * 
   * @param genre name of genre
   * @return String list of games with same input genre
   */
  @Override
  public String gameOfGenre(String genre) {
    String result = gameOfGenreHelper(genre, root);
    if (result.isBlank()) {
      return "no game of this genre in the list";
    }
    result = result.replaceAll(", $", " ");
    return result;
  }

  /**
   * Helper for gameOfGenre(), find all games with the genre input by using recursion
   * 
   * @param genre
   * @param runner the node input to begin of recursion
   * @return String names of the games the input genre
   */
  private String gameOfGenreHelper(String genre, Node<Game> current) {
    String result = "";
    if (current != null) {
      result += gameOfGenreHelper(genre, current.leftChild);
      if (current.data.getGenre().equals(genre))
        result += current.data.getName() + ", ";
      result += gameOfGenreHelper(genre, current.rightChild);
    }
    return result;
  }

  /**
   * Total number of the games in the list
   * 
   * @return int size of the tree
   */
  @Override
  public int numberOfGames() {
    return this.size;
  }

  /**
   * return a String with all games' names in the list
   * 
   * @return String of all names
   */

  @Override
  public String allGames() {
    String result = "";
    result = allGamesHelper(this.root);
    result = result.replaceAll(", $", "");
    return result;
  }

  /**
   * private helper for allGames, recursively concatenate a String contains all names
   * 
   * @param current the current node
   * @return the String of all names
   */
  private String allGamesHelper(Node<Game> current) {
    String result = "";
    if (current != null) {
      result += allGamesHelper(current.leftChild);
      result += current.data.getName() + ", ";
      result += allGamesHelper(current.rightChild);
    }

    return result;
  }

  /**
   * return the Game by the name given by user.
   * 
   * @param name the name of the game
   * @return Game wanted, null if the game name is null
   * @throws NoSuchElementException if game is not exist
   */

  @Override
  public Game get(String name){
    if (name == null) {
      System.out.print("Name cannot be null");
      return null;
    }
    Node<Game> game = getNode(name);
    if (game == null) {
      return new Game();
    }
    return game.data;
  }

  /**
   * Using game name get its node contains game information
   * 
   * @param name name of the game
   * @return Game wanted, null if the game does not exist
   */
  public Node<Game> getNode(String name) {
    return getNodeHelper(name, root);
  }

  /**
   * display allGames organized
   * 
   * @author Jiaming
   */
  public void display(String target) {
    String string;
    if (target.contentEquals("all")) {
      string = allGames();
    } else {
      string = gameOfGenre(target);
    }

    String[] display = string.split(",");
    for (int i = 0; i < display.length; ++i) {

      if (i == display.length - 1) {
        System.out.println(display[i].trim() + "\n");
      } else if (i == 0 || i % 4 != 0) {
        System.out.print(display[i].trim() + " ----- ");
      } else if ((i != 0 && i % 4 == 0)) {
        System.out.println(display[i].trim() + "\n");
      }
    }
  }

  /**
   * Helper to search for the node with the game name
   * 
   * @param name name of the game
   * @return Game wanted, null if the game does not exist
   */
  private Node<Game> getNodeHelper(String name, Node<Game> runner) {
    if (runner != null) {
      int compare = name.compareTo(runner.data.getName());
      if (compare == 0) {
        return runner;
      }
      if (compare < 0) {
        return getNodeHelper(name, runner.leftChild);
      }
      if (compare > 0) {
        return getNodeHelper(name, runner.rightChild);
      }
      return null;
    } else {
      return null;
    }
  }

  /**
   * Empty the tree, size set to 0, root to null
   * 
   * @return the size before clearing
   * 
   */
  @Override
  public int clear() {
    int before = this.size;
    this.size = 0;
    root = null;
    return before;
  }

  /**
   * @author Jiaming Zhang
   * 
   * 
   *         private helper, helps to find the replacement for the deleted node
   * @param delete
   * @return the replacement, null if the deleted node has no children
   */
  private Node<Game> findReplace(Node<Game> delete) {
    Node<Game> replace = null;
    if (delete.leftChild == null && delete.rightChild != null) {
      replace = delete.rightChild;
    }

    if (delete.leftChild != null && delete.rightChild == null) {
      replace = delete.leftChild;
    }

    if (delete.leftChild != null && delete.rightChild != null) {// find in order predecessor
      replace = delete.leftChild;
      while (replace.rightChild != null) {
        replace = replace.rightChild;
      }
      delete.data = replace.data; // copy the data in in-order predecessor into the deleted node,
                                  // then delete the
      // in-order predecessor
      delete = replace;
      replace = findReplace(delete);
    }
    return replace;
  }

  /**
   * 
   * @author Jiaming Zhang
   * 
   *
   *         remove the specified node from the tree
   * @param data the game name
   * @return the deleted Game object
   */

  @Override
  public Game remove(String data) {
    if (data == null || getNode(data) == null)
      return null;
    Node<Game> delete = this.getNode(data);

    Game temp =
        new Game(delete.data.getName(), delete.data.getReleaseDate(), delete.data.getNumOwners(),
            delete.data.getNumPlayers(), delete.data.getIsFree(), delete.data.getIsSinglePlayer(),
            delete.data.getIsMultiPlayer(), delete.data.getIsCOOP(), delete.data.getIsMMO(),
            delete.data.getIsVRCompatible(), delete.data.getGenre(), delete.data.getAbout()); // java
                                                                                              // doesn't
                                                                                              // have
                                                                                              // copy
                                                                                              // c-tor,
                                                                                              // soooooo

    Node<Game> replace = findReplace(delete);// this will also replace the content of delete with
                                             // the content of in order predecessor if applicable

    if (delete.leftChild != null && delete.rightChild != null) {// if the content of delete is
                                                                // replaced by that of in order
                                                                // predecessor, delete the IOP
      delete = delete.leftChild;
      while (delete.rightChild != null) {
        delete = delete.rightChild;
      }
    }

    if (delete.equals(this.root) && delete.leftChild == null && delete.rightChild == null) {// if
                                                                                            // delete
                                                                                            // is
                                                                                            // the
                                                                                            // root,
                                                                                            // and
                                                                                            // is
                                                                                            // the
                                                                                            // only
                                                                                            // element
      root = null;
      size--;
      return temp;
    }

    removeHelper(delete, replace);

    // the following part actually delete the node and replace it
    if (replace == null) { // replace is null
      if (delete.isLeftChild()) {
        delete.parent.leftChild = null;
      } else {
        delete.parent.rightChild = null;
      }
    } else if (delete.equals(root)) { // delete is root
      replace.parent = null;
      root = replace;
    } else if (delete.isLeftChild()) { // delete is left child
      replace.parent = delete.parent;
      delete.parent.leftChild = replace;
    } else { // delete is right child
      replace.parent = delete.parent;
      delete.parent.rightChild = replace;
    }
    size--;
    return temp;
  }

  /**
   * @author Jiaming Zhang
   * 
   * 
   *         private helper, helps to do the rotation and recoloring, and cascading fix
   * @param the node to delete
   * @param the replacement node
   */
  public void removeHelper(Node<Game> delete, Node<Game> replace) {
    if (delete.equals(root))
      return;
    if (delete.isBlack && (replace == null || replace.isBlack)) { // case 2, both delete and replace
                                                                  // are black
      if (delete.isLeftChild()
          && (delete.parent.rightChild == null || delete.parent.rightChild.isBlack)) { // case 2.1,
                                                                                       // sibling is
                                                                                       // black and
                                                                                       // has at
                                                                                       // least one
                                                                                       // red child
        if (delete.parent.rightChild.leftChild != null
            && !delete.parent.rightChild.leftChild.isBlack) {
          rotate(delete.parent.rightChild.leftChild, delete.parent.rightChild);
          rotate(delete.parent.rightChild, delete.parent);
          if (delete.parent.isBlack) { // case 2.1, parent is black
            delete.parent.parent.isBlack = true;
          } else { // 2.1, parent is red
            delete.parent.isBlack = false;
            delete.parent.parent.isBlack = true;
            delete.parent.parent.rightChild.isBlack = false;
            if (delete.parent.parent.rightChild.rightChild != null
                && !delete.parent.parent.rightChild.rightChild.isBlack) {// sibling has two red
                                                                         // children
              super.enforceRBTreePropertiesAfterInsert(delete.parent.parent.rightChild.rightChild);
            }
          }
        } else if (delete.parent.rightChild.rightChild != null
            && !delete.parent.rightChild.rightChild.isBlack) {
          rotate(delete.parent.rightChild, delete.parent);
          if (delete.parent.isBlack)
            delete.parent.parent.rightChild.isBlack = true;
        } else { // 2.2, sibling is black and has two black children
          if (!delete.parent.isBlack) {
            delete.parent.isBlack = true;
            delete.parent.rightChild.isBlack = false;
          } else {
            removeHelper(delete.parent, delete.parent.rightChild);
            delete.parent.rightChild.isBlack = false;
          }

        }

      } else if (!delete.isLeftChild()
          && (delete.parent.leftChild == null || delete.parent.leftChild.isBlack)) { // case 2.1,
                                                                                     // when delete
                                                                                     // is right
        if (delete.parent.leftChild.rightChild != null
            && !delete.parent.leftChild.rightChild.isBlack) {
          rotate(delete.parent.leftChild.rightChild, delete.parent.leftChild);
          rotate(delete.parent.leftChild, delete.parent);
          if (delete.parent.isBlack) { // case 2.1, parent is black
            delete.parent.parent.isBlack = true;
          } else {
            delete.parent.isBlack = false;
            delete.parent.parent.isBlack = true;
            delete.parent.parent.leftChild.isBlack = false;
            if (delete.parent.parent.leftChild.leftChild != null
                && !delete.parent.parent.leftChild.leftChild.isBlack) {// sibling has two red
                                                                       // children
              super.enforceRBTreePropertiesAfterInsert(delete.parent.parent.leftChild.leftChild);
            }
          }
        } else if (delete.parent.leftChild.leftChild != null
            && !delete.parent.leftChild.leftChild.isBlack) {
          rotate(delete.parent.leftChild, delete.parent);
          if (delete.parent.isBlack)
            delete.parent.parent.leftChild.isBlack = true;
        } else { // 2.2, sibling is black and has two black children
          if (!delete.parent.isBlack) {
            delete.parent.isBlack = true;
            delete.parent.leftChild.isBlack = false;
          } else {
            removeHelper(delete.parent, delete.parent.leftChild);
            delete.parent.leftChild.isBlack = false;
          }

        }
      } else if (delete.isLeftChild() && !delete.parent.rightChild.isBlack) {// case 2.3
        rotate(delete.parent.rightChild, delete.parent);
        delete.parent.isBlack = false;
        delete.parent.parent.isBlack = true;
        removeHelper(delete, delete.parent.rightChild);
      } else if (!delete.isLeftChild() && !delete.parent.leftChild.isBlack) {// case 2.3
        rotate(delete.parent.leftChild, delete.parent);
        delete.parent.isBlack = false;
        delete.parent.parent.isBlack = true;
        removeHelper(delete, delete.parent.leftChild);
      }

    } else { // case 1
      if (replace != null)
        replace.isBlack = true;
    }
  }
}