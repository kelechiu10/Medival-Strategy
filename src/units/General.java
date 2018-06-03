package units;
import logic.*;
/**
 * General Unit Class
 * Attack: 12
 * Defence: 18
 * Health: 70
 * Movement: 3
 * Abilities:
 * -(no abilities)
 *
 * @author Matthew Oh
 * @version 5/22/18
 */
public class General extends Unit
{
    /**
     * Constructor for General
     */
    public General()
    {
        super(12,18,70,3);
    }
    
    /**
     * Gets General's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        new Action(target, current, "attack");
    }
    
    /**
     * Returns the range of the unit's ability
     * @return the range of the ability 
     */
    public double getRange()
    {
        return 1.5;
    }
    
    /**
     * Gets the power of the General's ability
     * @return the power of the General's ability
     */
    public int getAbilityPower()
    {
        return 12;
    }
}
