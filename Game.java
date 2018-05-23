/**
 * Game is the class that runs the game (and must be called into aciton from a main class)
 *
 * @author Aemilia Russ
 * @version 23 May, 2018
 */
public class Game
{
   private Board board;
   private int turnNumber;
   private Player[] players;
   //private Unit[] initiative;
   
   /**
    * the constructor for Game creates a standard game with two players, a 15 X 15 board and 10 units per player.
    *
    */
   public Game()
   {
      board = new Board(15);
      players = {new Player, new Player};
      turnNumber = 0;
      
      board.placeUnits(players);
      runGame();
   }
   
   /**
    * nextTurn gets the input from the user and sends this input back to the active method
    * 
    */
   private void nextTurn()
   {
      //SOMEHOW GETS INPUT FROM THE USER AND STORES IT IN AN ACTION OBJECT
      
      Action action = new Action();
      
      if(turnNumber % 2 == 0)
      {
          players[0].setAction(action)
      }
      else
          players[1].setAction(action)
   }
   
   /**
    * startGame starts the game (duhhhh)
    *
    */
   private void runGame()
   {
      Action tempAction;
      
      while(!isOver)
      {
          nextTurn();
          if(turnNumber % 2 == 0)
          {
              tempAction = players[0].getAction();
              if(tempAction.getOperation().equals("attack"))
              {
                  if(attackActionValid(tempAction))
                      board.dealDamage(tempAction.getTarget(), ((board.getSpace(tempAction.getTarget())));
              }
              else
                  if(tempAction.getOperation().equals("move"))
                  {
                      
                  }
          }
          
          
          
          turnNumber++;
      }
      
   }
   
   /**
    * attackActionValid checks if the action passed is an attack action which is within range
    *
    */
   private boolean attackActionValid(Action a)
   {
      if(!a.getOperation().equals("attack"))
          return false;
      else
          if(board.getSpace(a.getCurrent()).getUnit().getRange() //SOMEHOW IMPLEMENT RANGE
   }
   /**
    * isOver returns true if either player has 0 units left
    * @return boolean if over
    */
   private boolean isOver()
   {
      return (players[0].isDead() || players[1].isDead())
   }
}
