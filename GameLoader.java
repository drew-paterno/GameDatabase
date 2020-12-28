
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Path;

/**
 * This class is used to convert a .csv File containing a list of games into a ArrayList object. It
 * contains one public static method, which executes the operation described.
 * 
 * @author Drew Paterno
 *
 */
public class GameLoader {

  private static String[] genres = {"NonGame", "Indie", "Action", "Adventure", "Casual", "Strategy",
      "RPG", "Simulation", "EarlyAccess", "FreeToPlay", "Sports", "Racing", "MassivelyMultiplayer"};
  // Array containing String representations of all the game genres represented in the file.

  /**
   * This private helper method assists the load method in converting boolean values pertaining to a
   * game's genre to a single String value.
   * 
   * @param line Reference to the line of the File containing information about games.
   * @return String representation of game's genre.
   */
  private static String getGenre(String[] line) {
    for (int i = 43; i < 56; i++) { // Iterates through the isGenre values of the line.
      if (line[i].equalsIgnoreCase("true"))
        return genres[i - 43]; // Returns the genre given the provided list of genres.
    }
    return ""; // Returns an empty String if all boolean values are false.
  }

//  /**
//   * Creates an ArrayList of game objects given a reference to a .csv file.
//   * 
//   * @param songs String representation of File path.
//   * @return ArrayList containing all Files represented in the provided .csv file.
//   */
//  public static ArrayList<Game> load(String songs) {
//    ArrayList<Game> list = new ArrayList<Game>(); // Initializes ArrayList of games.
//    try {
//      // Initializes file reading mechanism.
//      File file = new File(songs);
//      FileReader fr = new FileReader(file);
//      BufferedReader br = new BufferedReader(fr);
//      String line = "";
//      String[] lineContents;
//      Game game;
//
//      line = br.readLine(); // Skip first line.
//      line = br.readLine(); // Iterate to first line of content
//
//      while (line != null) { // Checks to see if iteration reached the end of the file.
//        lineContents = line.split(","); // Converts String representation of the line to an Array
//                                        // containing individual data values.
//
//        // Matches indices of lineContents array to data values to be added to a Game object.
//        String title = lineContents[2];
//        String releaseDate = lineContents[4];
//        int numOwners = Integer.parseInt(lineContents[15]);
//        int numPlayers = Integer.parseInt(lineContents[17]);
//        boolean isFree = Boolean.parseBoolean(lineContents[22]);
//        boolean isSinglePlayer = Boolean.parseBoolean(lineContents[35]);
//        boolean isMultiPlayer = Boolean.parseBoolean(lineContents[36]);
//        boolean isCOOP = Boolean.parseBoolean(lineContents[37]);
//        boolean isMMO = Boolean.parseBoolean(lineContents[38]);
//        boolean isVRCompatible = Boolean.parseBoolean(lineContents[42]);
//        String genre = getGenre(lineContents);
//        String about = lineContents[61];
//
//        // Initializes and adds game to ArrayList.
//        game = new Game(title, releaseDate, numOwners, numPlayers, isFree, isSinglePlayer,
//            isMultiPlayer, isCOOP, isMMO, isVRCompatible, genre, about);
//        list.add(game);
//
//        line = br.readLine(); // Reads next line of file.
//      }
//      br.close(); // Closes Buffered Reader.
//    } catch (IOException e) {
//      e.printStackTrace(); // To satisfy compiler.
//    }
//    return list;
//  }
  
  public static ArrayList<Game> load(String path) throws FileNotFoundException{
    try {
      return new ArrayList<Game>(java.nio.file.Files.lines(new File(path).toPath()).skip(1)
          .map(new java.util.function.Function<String, Game>() {
            
            public Game apply(String line) {
              String[] lineContents = line.split(","); // Converts String representation of the line to an
                                              // Array
              // containing individual data values.

              // Matches indices of lineContents array to data values to be added to a Game object.
              String title = lineContents[2];
              String releaseDate = lineContents[4];
              int numOwners = Integer.parseInt(lineContents[15]);
              int numPlayers = Integer.parseInt(lineContents[17]);
              boolean isFree = Boolean.parseBoolean(lineContents[22]);
              boolean isSinglePlayer = Boolean.parseBoolean(lineContents[35]);
              boolean isMultiPlayer = Boolean.parseBoolean(lineContents[36]);
              boolean isCOOP = Boolean.parseBoolean(lineContents[37]);
              boolean isMMO = Boolean.parseBoolean(lineContents[38]);
              boolean isVRCompatible = Boolean.parseBoolean(lineContents[42]);
              String genre = getGenre(lineContents);
              String about = lineContents[61];
              // Initializes and adds game to ArrayList.
              return new Game(title, releaseDate, numOwners, numPlayers, isFree, isSinglePlayer,
                  isMultiPlayer, isCOOP, isMMO, isVRCompatible, genre, about);
            }

          }).collect(Collectors.toList()));
    } catch (Exception e) {
      throw new FileNotFoundException("File not Found.");
    }
  }
}
