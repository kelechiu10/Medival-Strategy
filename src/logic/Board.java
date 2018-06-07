
package logic;
import tiles.*;
import units.*;
import java.util.ArrayList;
import chn.util.FileInput;
/**
 * Board Class
 * the Board class has an array of spaces that hold the board for the game
 * @author Claudia Xue
 * @version 5/16/18
 */
public class Board
{
  private Space[][] spaces;
  private final int SIZE;//15 for now
  
  /**
   * The constructor for class Board creates a board of the edges the size of the passed integer
   * @param integer size
   */
  public Board(int size, String s)
  {
    SIZE = size;
    spaces = new Space[SIZE][SIZE];
    if(s.equals("regular"))
	fillBoard();
    else
	fillBoardRandom();
  }
  
	
  /**
   * fillBoardRandom randomly fills the board (the initial unit spots are guaranteed grass)
   * 
   */
  private void fillBoardRandom()
  {
    //fill entire board with grass
    for(Space[] spc: spaces)
    {
        for(int k = 0; k < spc.length; k++)
        {
            spc[k] = new Grass();
        }
    }

    for(int k = 0; k < 5; k++)
    {
	spaces[(int)(Math.random() * 14)][(int)(Math.random() * 14)] = new GrassFort();
    }
	  
    for(int k = 0; k < 5; k++)
    {
	int r = (int)(Math.random() * 12);
	int c = (int)(Math.random() * 12); 
	if(!(spaces[r][c] instanceof GrassFort))
	    spaces[r][c] = new Water();
	if(!(spaces[r + 1][c] instanceof GrassFort))
	    spaces[r + 1][c] = new Water();
	if(!(spaces[r + 2][c] instanceof GrassFort))
	    spaces[r + 2][c] = new Water();
	if(!(spaces[r + 1][c - 1] instanceof GrassFort))
	    spaces[r + 1][c - 1] = new Water();
	if(!(spaces[r + 1][c + 1] instanceof GrassFort))
	    spaces[r + 1][c + 1] = new Water();
    }
	  
    //ensure that the units stand on grass
    for(int row = 0; row < 5; row++)
    {
      for(int col = 0; col < 2; col++)
      {
        spaces[row][col] = new Grass();
      }
    }
    for(int row = SIZE - 1; row > SIZE - 6; row--)
    {
      for(int col = SIZE - 1; col > SIZE - 3; col--)
      {
        spaces[row][col] = new Grass();
      }
    }
  }
	
  /**
   * fillBoard fills the board with spaces from the map.txt file
   *
   */
  private void fillBoard()
  {
    FileInput inFile = new FileInput("map.txt");
    for(Space[] spc: spaces)
    {
        for(int k = 0; k < spc.length; k++)
        {
            int tile = inFile.readInt();
            if (tile == 0)
                spc[k] = new Grass();
            else
                if (tile == 1)
                    spc[k] = new Water();
                else
                    spc[k] = new GrassFort();
        }
              
    }
  }
  
  /**
   * placeUnits places the units from the player arrays in a 2 column by number of units / 2 rows in the top
   * left and lower right of the board
   * @param Player array of players to place units (precondition: Player array is of length 2)
   */
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
  
  /**
   * getSpace returns the space at the position of the passed Position object
   * @param Position pos (precondition: x,y valid index on the board array)
   * @return Space object on the Board array
   */
  public Space getSpace(Position pos)
  {
    return spaces[pos.getX()][pos.getY()];
  }
  
  /**
   * dealDamage deals the passed integer damage to the space at the passed position
   * @param Position pos (precondition: x,y valid index on the board array)
   * @param integer attack amount
   */
  public void dealDamage(Position pos, int attack)
  {
    spaces[pos.getX()][pos.getY()].takeDamage(attack);
    if(spaces[pos.getX()][pos.getY()] instanceof GrassFort && ( (GrassFort)(spaces[pos.getX()][pos.getY()])).fortDown())
       spaces[pos.getX()][pos.getY()].setGraphic("towerDown.png");
  }

  /**
   * printBoard prints the board to the console
   *
   *
  public void printBoard()
  {
      for (int index = 0; index < SIZE; index++)
      {
          System.out.print(index % 10 + " ");
      }
      System.out.println();
      for (int row = 0; row < SIZE; row++)
      {
          for (int column = 0; column < SIZE; column++)
          {
              System.out.print(spaces[row][column].getName() + " ");
          }
          System.out.println(row);
      }
  }
  */
  public int getSize()
  {
	  return SIZE;
  }
}
