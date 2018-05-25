/**
 * Priest Unit Class
 * Attack: 10
 * Defence: 14
 * Health: 40
 * Movement: 5
 * Abilities:
 * -#1 Heal: Heals a unit at a distance
 *
 * @author Matthew Oh
 * @version 5/25/18
 */
public class Priest extends Unit
{
    /**
     * Constructor for Priest
     */
    public Priest()
    {
        super(10,14,40,5);
    }
    
    /**
     * Gets Priest's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "heal");
    }
    
    /**
     * Returns the range of the Archers's ability
     * @return the range of the ability 
     */
    public double getRange()
    {
        return 4.0;
    }
    
    /**
     * Gets the power of the Archers's ability
     * @return the power of the Archer's ability
     */
    public int getAbilityPower()
    {
        return 10;
    }
}
