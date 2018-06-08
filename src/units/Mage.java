package units;
import logic.*;
/**
 * Mage Unit Class
 * Attack: 12
 * Defence: 15
 * Health: 40
 * Movement: 2
 * Abilities:
 * -#1 Fireball: Damages unit at a distance
 *
 * @author Matthew Oh
 * @version 5/22/18
 */
public class Mage extends Unit
{
    /**
     * Constructor for Mage
     * @param t team of the unit
     */
    public Mage(String t)
    {
        super(12,15,40,2,t,"Wizard");
    }
    
    /**
     * Gets Mage's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "attack");
    }
    
    /**
     * Returns range for Mage's Ability
     * @return range of the ability
     */
    public double getRange()
    {
        return 3.0;
    }
    
    /**
     * Gets the power of the unit's ability
     * @return the power of the unit's ability
     */
    public int getAbilityPower()
    {
        return 16;
    }
}
