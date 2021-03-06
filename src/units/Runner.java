package units;
import logic.*;
/**
 * Runner Unit Class
 * Attack: 15
 * Defence: 10
 * Health: 45
 * Movement: 4
 * Abilities:
 * -(no abilities)
 *
 * @author Matthew Oh
 * @version 5/24/18
 */
public class Runner extends Unit
{
    /**
     * Constructor for Runner
     * @param t team of the unit
     */
    public Runner(String t)
    {
        super(15,10,45,4,t,"Runner");
    }
    
    /**
     * Gets Runner's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "attack");
    }
    
    /**
     * Returns the range of the Runner's ability
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
        return 15;
    }
}
