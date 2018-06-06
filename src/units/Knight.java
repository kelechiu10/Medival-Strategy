package units;
import logic.*;
/**
 * Knight Unit Class
 * Attack: 20
 * Defence: 20
 * Health: 65
 * Movement: 5
 * Abilities:
 * -(no abilities)
 *
 * @author Matthew Oh
 * @version 5/22/18
 */
public class Knight extends Unit
{
    /**
     * Constructor for Knight
     * @param t team of the unit
     */
    public Knight(String t)
    {
        super(20,20,65,5,t,"Knight");
    }
    
    /**
     * Gets Knight's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "attack");
    }
    
    /**
     * Returns the range of the Knight's ability
     * @return the range of the ability 
     */
    public double getRange()
    {
        return 1.5;
    }
    
    /**
     * Gets the power of the Knight's ability
     * @return the power of the Knight's ability
     */
    public int getAbilityPower()
    {
        return 20;
    }
}
