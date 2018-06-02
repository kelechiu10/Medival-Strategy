package logic;
/**
 * Action Class
 * Holds information about an unit's action
 * This class is passed to game to do the action
 * @author Matthew Oh
 * @version 5/23/18
 */
public class Action
{
    private Position target; //the position of the unit or space to be targeted in the operation
    private Position current; //the position of the unit doing the action
    
    /* String operation
     * This string dictates what the action does
     * "attack": deals damage to the unit at position with an amount of power
     * "heal": heals the unit at position with an amount of healing power
     * "move": moves the unit to the position (power is ignored)
     */
    private String operation;
    
    /**
     * Constructor for Action
     * @param pos position of the unit to be targeted
     * @param pwr power of the action 
     * @param op what the action does
     */
    public Action(Position tar, Position cur, String op)
    {
        target = tar;
        current = cur;
        operation = op;
    }
    
    /**
     * Returns the target of the action
     * @return the target of the action
     */
    public Position getTarget()
    {
        return target;
    }
    
    /**
     * Returns the position of the unit doing the action
     * @return the position of the unit doing the action
     */
    public Position getCurrent()
    {
        return current;
    }
     
     /**
      * Returns the operation of the action
      * "attack": deals damage to the unit at position with an amount of power
      * "heal": heals the unit at position with an amount of healing power
      * "move": moves the unit to the position (power is ignored)
      * @returns the operation of the action
      */
     public String getOperation()
     {
        return operation;
     }
}
