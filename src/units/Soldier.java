package units;
import logic.*;

/**
 * Soldier Unit Class
 * Attack: 12
 * Defence: 12
 * Health: 45
 * Movement: 3
 * Abilities:
 * -(no abilities)
 *
 * @author Matthew Oh
 * @version 5/22/18
 */
public class Soldier extends Unit
{
    /**
     * Constructor for Soldier
     * @param t team of the unit
     */
    public Soldier(String t)
    {
        super(12,12,45,3,t,"Soldier");
    }
    
    /**
     * Gets Soldier's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "attack");
    }
    
    /**
     * Returns the range of the Soldier's ability
     * @return the range of the ability 
     */
    public double getRange()
    {
        return 1.5;
    }
    
    /**
     * Gets the power of the Soldier's ability
     * @return the power of the Soldier's ability
     */
    public int getAbilityPower()
    {
        return 12;
    }
}
