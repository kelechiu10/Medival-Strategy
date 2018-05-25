/*
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
   private final Item[] DEFAULTITEMS;
   
   /**
    * the constructor for Game creates a standard game with two players, a 15 X 15 board and 10 units per player.
    *
    */
   public Game()
   {
       DEFAULTITEMS = new Item[4];
      DEFAULTITEMS[0] = new Item(10, 3, HealPotion);
      DEFAULTITEMS[1] = new Item(1, 2, RangePotion); 
      DEFAULTITEMS[2] = new Item(5, 2, AttckPotion);
      DEFAULTITEMS[3] = new Item(1, 2, MovePotion);
      board = new Board(16);
      players = new Player[2];
      players[0] = new Player();
      players[1] = new Player();
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
      boolean nextPlayer = true;
      while(!isOver)
      {
          nextTurn();
          if(turnNumber % 4 == 0)
              nextPlayer = !nextPlayer;
          
          if(nextPlayer)
          {
              tempAction = players[0].getAction();
          }
          else
          {
              tempAction = players[1].getAction();
          }
          
          if(attackActionValid(tempAction))
          {
              board.dealDamage(tempAction.getTarget(), ((board.getSpace(tempAction.getTarget()))));
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
                    int healPower = ((Priest)((board.getSpace(tempAction.getTarget())).getUnit()).getAbilityPower());;
                    (board.getSpace(tempAction.getTarget())).getUnit().heal(healPower);
                 } 
                 else
                    if(itemActionValid(tempAction))
                    {
                       if(tempAction.getOperation().indexOf("HealPotion") > -1)
                       {
                          int healPower = (players[tempAction.getCurrent().getX()].useItem(tempAction.getCurrent().getY())).useItem();
                          board.getSpace(tempAction.getTarget()).getUnit().heal(healPower);
                       }
                       else
                           if(tempAction.getOperation().indexOf("MovePotion") > -1)
                           {
                              int movePower = (players[tempAction.getCurrent().getX()].useItem(tempAction.getCurrent().getY())).useItem();
                              board.getSpace(tempAction.getTarget()).getUnit().addMove(movePower);
                           }
                           else
                              if(tempAction.getOperation().indexOf("RangePotion") > -1)
                              {
                                 int rangePower = (players[tempAction.getCurrent().getX()].useItem(tempAction.getCurrent().getY())).useItem();
                                 board.getSpace(tempAction.getTarget()).getUnit().addRange(rangePower);
                              }
                              else
                                 if(tempAction.getOperation().indexOf("AttackPotion") > -1)
                                 {
                                    int attackPower = (players[tempAction.getCurrent().getX()].useItem(tempAction.getCurrent().getY())).useItem();
                                    board.getSpace(tempAction.getTarget()).getUnit().addAttack(attackPower);
                                 }
                    }
                    else
                       if(pickUpItemValid(tempAction))
                       {
                          
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
          players[0].setAction(action);
      else
          players[1].setAction(action);
   }
   
   /**
    * pickUpItemValid checks if the Aciton passed is a valid pick up item action
    *
    * the Action object will be formatted as follows for picking up items
    *    current x: index on default items array
    *    current y: player number
    *    target x: index to be replaced on player's item array
    *    target y: 0
    *    operation: name of item to be used
    *
    * @param Action object
    * @return boolean if action is valid
    */
   private boolean pickUpItemValid(Action a)
   {
      if(a.getOperation().indexOf("Pick up Item:") == -1)
         return false;
      else
         if(a.getOperation().indexOf("HealPotion") > -1 
            && DEFAULTITEMS[a.getCurrent().getX()].getName().equals("HealPotion"))
         {
            return true;
         }
         else
            if(a.getOperation().indexOf("MovePotion") > -1 
               && DEFAULTITEMS[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("MovePotion"))
            {
               return true;
            }
            else
               if(a.getOperation().indexOf("RangePotion") > -1 
                  && DEFAULTITEMS[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("RangePotion"))
               {
                  return true;
               }
               else
                  if(a.getOperation().indexOf("AttackPotion") > -1 
                     && DEFAULTITEMS[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("AttackPotion"))
                  {
                     return true;
                  }
                  else
                     return false;
   }
   /**
    * itemActionValid checks if the Action passed is a valid item action
    * 
    * the Action object will be formatted as follows for using items
    *    current x: player number
    *    current y: index on player's item array
    *    target x: target x position
    *    target y: target y position
    *    operation: "Use Item: " + Item name
    *
    * @param Action object
    * @return boolean if action is valid
    */
   private boolean itemActionValid(Action a)
   {
      if(a.getOperation().indexOf("Use Item:") == -1)
         return false;
      else
         if(a.getOperation().indexOf("HealPotion") > -1 
            && players[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("HealPotion"))
         {
            return true;
         }
         else
            if(a.getOperation().indexOf("MovePotion") > -1 
               && players[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("MovePotion"))
            {
               return true;
            }
            else
               if(a.getOperation().indexOf("RangePotion") > -1 
                  && players[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("RangePotion"))
               {
                  return true;
               }
               else
                  if(a.getOperation().indexOf("AttackPotion") > -1 
                     && players[a.getCurrent().getX()].getItem(a.getCurrent().getY()).getName().equals("AttackPotion"))
                  {
                     return true;
                  }
                  else
                     return false;
   }
                                         
   /**
    * healActionValid checks if the Action passed is a valid heal action
    * this only checks if the Action passed is of operation "heal" and is within 4 range
    *
    * the Action object will be formatted as follows for using a heal action
    *    current x: priest unit's x
    *    current y: priest unit's y
    *    target x: target x position
    *    target y: target y position
    *    operation: "heal"
    *
    * @param Action object
    * @return boolean if action is valid
    */
   private boolean healActionValid(Action a)
   {
      if(!a.getOperation().equals("heal"))
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
    *
    * the Action object will be formatted as follows for using a move action
    *    current x: current unit's x
    *    current y: current unit's y
    *    target x: target x position
    *    target y: target y position
    *    operation: "move"
    *
    * @return boolean if action is valid
    */
   private boolean moveAcitonValid(Action a)
   {
      if(!a.getOperation().equals("move"))
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
    *
    * the Action object will be formatted as follows for using a attack action
    *    current x: current unit's x
    *    current y: current unit's y
    *    target x: target x position
    *    target y: target y position
    *    operation: "attack"
    *
    * @param Action object
    * @return boolean if attack is valid
    */
   private boolean attackActionValid(Action a)
   {
      int rangeTemp;
      if(!a.getOperation().equals("attack"))
          return false;
      else //applies distance formula and then checks if it is less than the range value
         if(Math.sqrt(Math.pow(a.getCurrent().getX + a.getTarget().getX, 2) + Math.pow(a.getCurrent().getY() + a.getTarget().getY(), 2)) <= board.getSpace(a.getCurrent()).getUnit().getRange())
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
      return (players[0].isDead() || players[1].isDead());
   }
}
