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
    private Item[] items;
    private Action action;
    
    /**
     * the default constructor for Player creates a player with standard items and standard units
     * (standard items: two healing potions, one attack potion)
     * (standard units: one of each, four soldiers)
     */
    public Player()
    {
        units = new ArrayList<Unit>(10);
        
        for(int k = 0; k < 4; k++)
          units.add(new Soldier());
        units.add(new Priest());
        units.add(new Mage());
        units.add(new General());
        units.add(new Archer());
        units.add(new Knight());
        units.add(new Runner());
        
        items = new Item[3];
        items[0] = new Item(10, 3, "HealPotion");
        items[1] = new Item(10, 3, "HealPotion");
        items[2] = new Item(10, 3, "HealPotion");
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
    
    public ArrayList getUnits()
    {
        return units;
    }
    
    /**
     * isDead() returns true if the player has no more units, otherwise, returns false
     * @return boolean if Dead
     * 
     */
    public boolean isDead()
    {
        if(units.size() == 0)
          return true;
        return false;
    }
    
    /**
     * useItem uses the item at the passed index on the target position
     * @param integer index on Item array, Position target to use item
     *
     */
    public Item useItem(int index)
    {
        return items[index];
    }
    
    public Item getItem(int index)
    {
        return items[index];
    }
    /**
     * pickUpItem overwrites the item at index on the item array
     * @param Item picked up item, integer index on Item array
     */
    public void pickUpItem(Item newItem, int index)
    {
        items[index] = newItem;
    }
    
    /**
     * getItemList returns a String representation of the item array
     * @return String list of Items
     */
    public String getItemList()
    {
        return "1: " + items[0] + " 2: " + items[1] + " 3: " + items[3];
    }
    
 }
