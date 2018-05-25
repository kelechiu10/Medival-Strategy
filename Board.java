import java.util.ArrayList;
/**
 * Board Class
 * @author Claudia Xue
 * @version 5/16/18
 */
public class Board
{
  private Space[][] spaces;
  private final int SIZE;//15 for now
  
  public Board(int size)
  {
    SIZE = size;
    spaces = new Space[SIZE][SIZE];
    fillBoard();
  }
  
  public void fillBoard()
  {
    for(Space[] spc: spaces)
    {
        for(int k = 0; k < spc.length; k++)
        {
            spc[k] = new Grass();
        }
    }
    
    
  }
  
  public void placeUnits(Player[] players)
  {
    ArrayList<Unit> unit0 = players[0].getUnits();
    ArrayList<Unit> unit1 = players[1].getUnits();
    int unitNum = 0;
    for(int row = 0; row < unit0.size()/2; row++)
    {
      for(int col = 0; col < 2; col++)
      {
        spaces[row][col].setUnit(unit0.get(unitNum));
        unit0.get(unitNum).move(new Position(row, col));
        unitNum++;
      }
    }
    unitNum = 0;
    for(int row = SIZE - 1; row > SIZE - unit0.size()/2 - 1; row--)
    {
      for(int col = SIZE - 1; col > SIZE - 3; col--)
      {
        spaces[row][col].setUnit(unit1.get(unitNum));
        unit1.get(unitNum).move(new Position(row, col));
        unitNum++;
      }
    }
  }
  
  public Space getSpace(Position pos)
  {
    return spaces[pos.getX()][pos.getY()];
  }
  
  public void dealDamage(Position pos, int attack)
  {
    if(spaces[pos.getX()][pos.getY()].getUnit().takeDamage(attack))
      spaces[pos.getX()][pos.getY()].removeUnit();
  }
}
