/**
 * Board Class
 * @author Claudia
 * @version 5/16/18
 */

public class Board
{
  private Space[][] spaces;
  private final SIZE;
  
  public Board(int size)
  {
    SIZE = size;
    spaces = new Space[SIZE][SIZE];
    fillBoard();
  }
  
  public void fillBoard()
  {
    for(Space terrain: spaces)
    {
      terrain = new Space();
    }
    
  }
  
  public void placePieces()
  {
    //???
  }
}
