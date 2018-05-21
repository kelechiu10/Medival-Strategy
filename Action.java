/**
 * Action Class
 * Holds information about an unit's action
 * This class is passed to board to do the action
 * @author Matthew Oh
 * @version 5/21/18
 */
public class Action
{
    private Position target; //the position of the unit or space to be targeted in the operation
    private int power; //the power of the action (ignored when operation is move)
    
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
    public Action(Position pos, int pwr, String op)
    {
        position = pos;
        power = pwr;
        operation = op;
    }
    
    /**
     * Returns the position of the action
     * @return the position of the action
     */
    public Position getPosition()
    {
        return position;
    }
    
    /**
     * Returns the power of the action
     * @return the power of the action
     */
     public int getPower()
     {
        return power;
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
