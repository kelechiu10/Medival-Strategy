package logic;
import tiles.*;
import units.*;
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
    spaces[pos.getX()][pos.getY()].takeDamage(attack);
  }

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
}
