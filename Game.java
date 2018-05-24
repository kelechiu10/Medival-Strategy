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
    * runGame starts the game (duhhhh)
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
              if(attackActionValid(tempAction))
              {
                  board.dealDamage(tempAction.getTarget(), ((board.getSpace(tempAction.getTarget())));
              }
              else
                  if(moveActionValid(tempAction))
                  {
                     //switch the two in the board and then change it in the unit itself
                     (board.getSpace(tempAction.getTarget())).setUnit((board.getSpace(tempAction.getCurrent())).getUnit());
                     (board.getSpace(tempAciton.getCurrent())).setUnit(null);
                     (board.getSpace(tempAction.getCurrent())).getUnit().move(tempAction.getTarget());
                  }
                  else
                     if(healActionValid(tempAciton))
                     {
                        int healPower = ((Priest)((board.getSpace(tempAction.getTarget())).getUnit()).getAbilityPower();
                        (board.getSpace(tempAction.getTarget())).getUnit().heal(healPower);
                     } 
                     if(itemActionValid(tempAction))
                     {
                        if(a.getOperation().indexOf("HealingPotion") > -1)
                        {
                           int healPower = (players[a.getCurrent().getX()].useItem(a.getCurrent().getY())).useItem();
                           board.getSpace(tempAction.getTarget()).getUnit().heal(healPower);
                        }
                        else
                           if(a.getOperation().indexOf("movePotion") > -1)
                           {
                              int movePower = (players[a.getCurrent().getX()].useItem(a.getCurrent().getY())).useItem();
                              board.getSpace(tempAction.getTarget()).getUnit().addMove(movePower);
                           }
                           else
                              if(a.getOperation().indexOf("rangePotion") > -1)
                              {
                                 int rangePower = (players[a.getCurrent().getX()].useItem(a.getCurrent().getY())).useItem();
                                 board.getSpace(tempAction.getTarget()).getUnit().addRange(rangePower);
                              }
                              else
                                 if(a.getOperation().indexOf("attackPotion") > -1)
                                 {
                                    int attackPower = (players[a.getCurrent().getX()].useItem(a.getCurrent().getY())).useItem();
                                    board.getSpace(tempAction.getTarget()).getUnit().addAttack(attackPower);
                                 }
                     }
          }
          turnNumber++;
      }
      
   }
                                         
   /**
    * nextTurn gets the input from the user and sends this input back to the active method
    * 
    */
   private void nextTurn()
   {
      //SOMEHOW GETS INPUT FROM THE USER AND STORES IN THE THREE VARIABLES BELOW
      
      Position current = new Position(1,2);
      Position target = new Position(2,3);
      String operation = "Temp";
      
      Action action = new Action(target, current, operation);
      
      if(turnNumber % 2 == 0)
      {
          players[0].setAction(action)
      }
      else
          players[1].setAction(action)
   }
   
   /**
    * itemActionValid checks if the Action passed is a valid item action
    * 
    * @param Action object
    * @return boolean if action is valid
    */
   private boolean itemActionValid(Action a)
   {
      if(a.getOperation().indexOf("Use Item:") == -1)
         return false;
      else
         if(a.getOperation().indexOf("HealingPotion") > -1)
         {
            
         }
         else
            if(a.getOperation().indexOf("movePotion") > -1)
            {
               
            }
            else
               if(a.getOperation().indexOf("rangePotion") > -1)
               {
                  
               }
               else
                  if(a.getOperation().indexOf("attackPotion") > -1)
                  {
                     
                  }
   }
   /**
    * healActionValid checks if the Action passed is a valid heal action
    * this only checks if the Action passed is of operation "heal" and is within 4 range
    * @param Action object
    * @return boolean if action is valid
    */
   private boolean healActionValid(Action a)
   {
      if(!tempAction.getOperation().equals("heal"))
         return false;
      else
         if(Math.sqrt(Math.pow(a.getCurrent().getX + a.getTarget().getX, 2) 
            + Math.pow(a.getCurrent().getY() + a.getTarget().getY(), 2)) 
            <= 4)
         {
            return true;
         }
         else
            return false;
   }
            
   /**
    * moveActionValid checks if the action passed is a valid move action
    * @param Action object
    * @return boolean if action is valid
    */
   private boolean moveAcitonValid(Action a)
   {
      if(!tempAction.getOperation().equals("move"))
         return false;
      else
         if(Math.sqrt(Math.pow(a.getCurrent().getX + a.getTarget().getX, 2) 
            + Math.pow(a.getCurrent().getY() + a.getTarget().getY(), 2)) 
            <= board.getSpace(a.getCurrent()).getUnit().getMoveSpeed())
         {
            return true;
         }
         else
            return false;
   }
   /**
    * attackActionValid checks if the action passed is an attack action which is within range
    * @param Action object
    * @return boolean if attack is valid
    */
   private boolean attackActionValid(Action a)
   {
      int rangeTemp;
      if(!a.getOperation().equals("attack"))
          return false;
      else //applies distance formula and then checks if it is less than the range value
         if(Math.sqrt(Math.pow(a.getCurrent().getX + a.getTarget().getX, 2) 
            + Math.pow(a.getCurrent().getY() + a.getTarget().getY(), 2)) 
            <= board.getSpace(a.getCurrent()).getUnit().getRange())
         {
               return true;
         }
         else
            return false;
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
