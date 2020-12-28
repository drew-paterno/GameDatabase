
/**
 * This object represents a Game to be inserted into the RBT. It contains details relevant to an
 * individual game.
 * 
 * @author Drew Paterno
 *
 */
public class Game implements Comparable<Game> {

  private String name;
  private String releaseDate;
  private int numOwners;
  private int numPlayers;
  private boolean isFree;
  private boolean isSinglePlayer;
  private boolean isMultiPlayer;
  private boolean isCOOP;
  private boolean isMMO;
  private boolean isVRCompatible;
  private String genre;
  private String about;

  /**
   * Default Constructor. All fields are initialized to their sentinel values, except the Strings,
   * which are instead initialized as empty Strings. Contains a corresponding mutator and accessor
   * method for every instance field.
   */
  public Game() {
    this("", "", 0, 0, false, false, false, false, false, false, "", "");
  }

  /**
   * Constructor initializes all fields as default, except the Name of the game.
   * 
   * @param name Reference to the name of the game to be stored in this object.
   */
  public Game(String name) {
    this(name, "", 0, 0, false, false, false, false, false, false, "", "");
  }

  /**
   * Constructor which initializes all fields with their corresponding parameters.
   * 
   * @param name           Name of the game object.
   * @param releaseDate    Release date of the game.
   * @param numOwners      Number of people who own this game.
   * @param numPlayers     Number of people who play this game.
   * @param isFree         Boolean representing whether this game is free or not.
   * @param isSinglePlayer Boolean representing whether this game is Single Player.
   * @param isMultiPlayer  Boolean representing whether this game is Multi-Player.
   * @param isCOOP         Boolean representing whether this game is COOP.
   * @param isMMO          Boolean representing whether this game is an MMO.
   * @param isVRCompatible Boolean representing whether this game is VR Compatible.
   * @param genre          Genre of this game.
   * @param about          Description of this game.
   */
  public Game(String name, String releaseDate, int numOwners, int numPlayers, boolean isFree,
      boolean isSinglePlayer, boolean isMultiPlayer, boolean isCOOP, boolean isMMO,
      boolean isVRCompatible, String genre, String about) {
    this.name = name;
    this.releaseDate = releaseDate;
    this.numOwners = numOwners;
    this.numPlayers = numPlayers;
    this.isFree = isFree;
    this.isSinglePlayer = isSinglePlayer;
    this.isMultiPlayer = isMultiPlayer;
    this.isCOOP = isCOOP;
    this.isMMO = isMMO;
    this.isVRCompatible = isVRCompatible;
    this.genre = genre;
    this.about = about;
  }

  /**
   * Gets the name of this game.
   * 
   * @return Name of this game.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this game.
   * 
   * @param name Updated value for the name of this game.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the release date of this game.
   * 
   * @return The release date of this game.
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Sets the release date of this game.
   * 
   * @param releaseDate Updated value for the release date of this game.
   */
  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  /**
   * Gets the number of people who own this game.
   * 
   * @return The number of people who own this game.
   */
  public int getNumOwners() {
    return numOwners;
  }

  /**
   * Sets the number of people who own this game.
   * 
   * @param numOwners Updated value for number of people who own this game.
   */
  public void setNumOwners(int numOwners) {
    this.numOwners = numOwners;
  }

  /**
   * Gets the number of people who play this game.
   * 
   * @return The number of people who play this game.
   */
  public int getNumPlayers() {
    return numPlayers;
  }

  /**
   * Sets the number of people who play this game.
   * 
   * @param numPlayers Updated value for number of people who play this game.
   */
  public void setNumPlayers(int numPlayers) {
    this.numPlayers = numPlayers;
  }

  /**
   * Gets whether this game is free or not.
   * 
   * @return True if the game is free, false otherwise.
   */
  public boolean getIsFree() {
    return isFree;
  }

  /**
   * Sets whether this game is free or not.
   * 
   * @param isFree Boolean value for whether this game is free (true) or not (false).
   */
  public void setIsFree(boolean isFree) {
    this.isFree = isFree;
  }

  /**
   * Gets whether this game is Single Player or not.
   * 
   * @return True if the game is Single Player, false otherwise.
   */
  public boolean getIsSinglePlayer() {
    return isSinglePlayer;
  }

  /**
   * Sets whether this game is Single Player or not.
   * 
   * @param isFree Boolean value for whether this game is Single Player (true) or not (false).
   */
  public void setIsSinglePlayer(boolean isSinglePlayer) {
    this.isSinglePlayer = isSinglePlayer;
  }

  /**
   * Gets whether this game is Multi-Player or not.
   * 
   * @return True if the game is Multi-Player, false otherwise.
   */
  public boolean getIsMultiPlayer() {
    return isMultiPlayer;
  }

  /**
   * Sets whether this game is Multi-Player or not.
   * 
   * @param isFree Boolean value for whether this game is Multi-Player (true) or not (false).
   */
  public void setIsMultiPlayer(boolean isMultiPlayer) {
    this.isMultiPlayer = isMultiPlayer;
  }

  /**
   * Gets whether this game is COOP or not.
   * 
   * @return True if the game is COOP, false otherwise.
   */
  public boolean getIsCOOP() {
    return isCOOP;
  }

  /**
   * Sets whether this game is COOP or not.
   * 
   * @param isFree Boolean value for whether this game is COOP (true) or not (false).
   */
  public void setIsCOOP(boolean isCOOP) {
    this.isCOOP = isCOOP;
  }

  /**
   * Gets whether this game is an MMO or not.
   * 
   * @return True if the game is an MMO, false otherwise.
   */
  public boolean getIsMMO() {
    return isMMO;
  }

  /**
   * Sets whether this game is an MMO or not.
   * 
   * @param isFree Boolean value for whether this game is an MMO (true) or not (false).
   */
  public void setIsMMO(boolean isMMO) {
    this.isMMO = isMMO;
  }

  /**
   * Gets whether this game is VR Compatible or not.
   * 
   * @return True if the game is VR Compatible, false otherwise.
   */
  public boolean getIsVRCompatible() {
    return isVRCompatible;
  }

  /**
   * Sets whether this game is VR Compatible or not.
   * 
   * @param isFree Boolean value for whether this game is VR Compatible (true) or not (false).
   */
  public void setIsVRCompatible(boolean isVRCompatible) {
    this.isVRCompatible = isVRCompatible;
  }

  /**
   * Gets the Genre of this game.
   * 
   * @return Genre of this game.
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets the Genre of this game.
   * 
   * @param name Updated value for the Genre of this game.
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Gets a description of this game.
   * 
   * @return A description of this game.
   */
  public String getAbout() {
    return about;
  }

  /**
   * Sets a description of this game.
   * 
   * @param about Updated description of this game.
   */
  public void setAbout(String about) {
    this.about = about;
  }

  private static String toStringHelper(boolean x) {
    if (x)
      return "Yes";
    else
      return "No";
  }

  /**
   * Returns a String representation of this game.
   * 
   * @return A String representation of this game.
   */
  @Override
  public String toString() {
    String line1 = "Name: " + name + ", Released: " + releaseDate + ".\n";
    String line2 = "Owned by: " + numOwners + ", Played by: " + numPlayers + ".\n";
    String line3 = "Single Player: " + toStringHelper(isSinglePlayer) + ", Multiplayer: "
        + toStringHelper(isMultiPlayer) + ", COOP: " + toStringHelper(isCOOP) + ", MMO: "
        + toStringHelper(isMMO) + ".\n";
    String line4 = "Genre: " + genre + ", Free: " + toStringHelper(isFree) + ", VR Compatible: "
        + toStringHelper(isVRCompatible) + ".\n";
    String line5 = "Description: " + about;
    return line1 + line2 + line3 + line4 + line5;
  }

  /**
   * Determines whether this object is less than, greater than, or equal to another game object.
   * 
   * @param other Game to compare to this object.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *         or greater than the specified object.
   */
  @Override
  public int compareTo(Game other) {
    return this.name.compareTo(other.getName()); // Compares games by comparing their names
                                                 // (Strings).
  }

}
