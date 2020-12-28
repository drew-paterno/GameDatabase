
public interface GameADT {

  public boolean put(Game game);

  public Game get(String gameName);

  public String gameOfGenre(String genre); // return the full list of games of one genre

  public Game remove(String gameName); // return the Game object being removed, null if not found

  public int numberOfGames(); // return # of games in list

  public String allGames(); // String representation of all game names

  public int clear(); // remove all games from the list, return # of games, 0 if list is empty

}


