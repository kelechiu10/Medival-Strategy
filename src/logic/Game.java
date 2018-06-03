package logic;
import units.*;
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
   
   /**
    * the constructor for Game creates a standard game with two players, a 16 X 16 board and 10 units per player.
    *
    */
   public Game()
   {
      board = new Board(16);
      players = new Player[2];
      players[0] = new Player();
      players[1] = new Player();
      turnNumber = 0;
      board.placeUnits(players);
      runGame(Main.getIntegerInput(), Main.getActionInput()); //TO BE DELETED
   }
   
   /**
    * getBoard returns the board that the game uses
    * @return Board board the game refers to
    */
   public Board getBoard()
   {
       return board;
   }
   
   /**
    * runGame runs one turn of the game for the passed player and action
    * This method needs a player number so that the GUI team can choose the number of turns between player actions
    * @param Integer player number
    * @param Action action to execute
    */
   public boolean runGame(int p, Action act)
   {
          if(attackActionValid(act))
          {
              board.dealDamage(act.getTarget(), ((board.getSpace(act.getCurrent())).getUnit().getAttackValue()));
             
              if(p == 1)
              {
                  players[0].updateUnitList();
              }
              else
              {
                  players[1].updateUnitList();
              }
          }
          else
              if(moveActionValid(act))
              {
                 //switch the two in the board and then change it in the unit itself
                 (board.getSpace(act.getTarget())).setUnit((board.getSpace(act.getCurrent())).getUnit());
                 (board.getSpace(act.getCurrent())).setUnit(null);
                 (board.getSpace(act.getCurrent())).getUnit().move(act.getTarget());
              }
              else
                 if(healActionValid(act))
                 {
                    int healPower = ((Priest)(board.getSpace(act.getCurrent())).getUnit()).getAbilityPower();
                    (board.getSpace(act.getTarget())).getUnit().heal(healPower);
                 } 
           turnNumber++;
       return !isOver();
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
      if(!a.getOperation().equals("heal") || board.getSpace(a.getTarget()).getUnit() == null
            || board.getSpace(a.getCurrent()).getUnit() == null)
         return false;
        else
             if(Math.sqrt(Math.pow(a.getCurrent().getX() - a.getTarget().getX(), 2) 
                + Math.pow(a.getCurrent().getY() - a.getTarget().getY(), 2)) 
                <= board.getSpace(a.getCurrent()).getUnit().getRange())
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
   private boolean moveActionValid(Action a)
   {
      if(!a.getOperation().equals("move") || board.getSpace(a.getTarget()).getUnit() != null
            || board.getSpace(a.getCurrent()).getUnit() == null)
         return false;
      else
         if(board.getSpace(a.getTarget()).walkable() &&
            Math.sqrt(Math.pow(a.getCurrent().getX() - a.getTarget().getX(), 2) 
            + Math.pow(a.getCurrent().getY() - a.getTarget().getY(), 2)) 
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
      if(!a.getOperation().equals("attack") || board.getSpace(a.getTarget()).getUnit() == null
            || board.getSpace(a.getCurrent()).getUnit() == null)
          {System.out.println("attack invalid due to null or name"); return false;}
      else
         if(Math.sqrt(Math.pow(a.getCurrent().getX() - a.getTarget().getX(), 2) 
         + Math.pow(a.getCurrent().getY() - a.getTarget().getY(), 2)) 
         <= board.getSpace(a.getCurrent()).getUnit().getRange())
         {
               System.out.println("attack valid");
               return true;
         }
         else
            {System.out.println("attack invalid due to range "); return false;}
   }
                                                                         
   /**
    * isOver returns true if either player has 0 units left
    * @return boolean true if over
    */
   private boolean isOver()
   {
      return (players[0].isDead() || players[1].isDead());
   }
}
