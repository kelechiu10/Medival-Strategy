package logic;
import units.*;
import java.util.ArrayList;
/**
 * Player class
 * holds an array of Units and an array of Items and can do actions
 * @author Aemilia Russ
 * @version 21 May, 2018
 */
 public class Player
 {
 
    private ArrayList<Unit> units;
    //private Item[] items;
    private Action action;
    
    /**
     * the default constructor for Player creates a player with standard items and standard units
     * (standard items: two healing potions, one attack potion)
     * (standard units: one of each, four soldiers)
     */
    public Player(String t)
    {
        units = new ArrayList<Unit>(10);
        
        for(int k = 0; k < 4; k++)
          units.add(new Soldier(t));
        units.add(new Priest(t));
        units.add(new Mage(t));
        units.add(new General(t));
        units.add(new Archer(t));
        units.add(new Knight(t));
        units.add(new Runner(t));
        
        /*
        items = new Item[3];
        items[0] = new Item(10, 3, "HealPotion");
        items[1] = new Item(10, 3, "HealPotion");
        items[2] = new Item(10, 3, "HealPotion");
        */
    }
    
    /**
     * getAction() returns the action the player will do next
     *
     * 
     */
    public Action getAction()
    {
        return action;
    }
    
    /**
     * setAction() sets the next action for the player
     *
     * 
     */
    public void setAction(Action act)
    {
        action = act;
    }
    
    /**
     * updates the unit list for the player
     */
    public void updateUnitList()
    {
     for(int u = 0; u < units.size(); u++)
        {
           if(units.get(u).takeDamage(0))
           {
              units.remove(u);
              u--;
           }
        }
    }
  
    /**
     * getUnit returns a reference to the unit at the passed index
     * @param integer index
     * @return Unit unit
     */
    public Unit getUnit(int index)
    {
        return units.get(index);
    }
    
    public ArrayList<Unit> getUnits()
    {
        return units;
    }
    
    /**
     * isDead() returns true if the player is dead, otherwise, returns false
     * @return boolean if Dead
     * 
     */
    public boolean isDead()
    {
        boolean result = true;
        for(Unit u : units)
        {
           if(u instanceof General)
               result = false;
        }
        return result;
    }
 }
