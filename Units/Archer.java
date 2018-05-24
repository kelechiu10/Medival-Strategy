/**
 * Archer Unit Class
 * Attack: 10
 * Defence: 10
 * Health: 45
 * Movement: 5
 * Abilities:
 * -#1 Shoot: Damages unit at a distance
 *
 * @author Matthew Oh
 * @version 5/24/18
 */
public class Archer extends Unit
{
    /**
     * Constructor for Archer
     */
    public Archer()
    {
        super(10,10,45,5);
    }
    
    /**
     * Gets Archer's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "attack");
    }
    
    /**
     * Returns the range of the Archers's ability
     * @return the range of the ability 
     */
    public double getRange()
    {
        return 8.0;
    }
    
    /**
     * Gets the power of the Archers's ability
     * @return the power of the Archer's ability
     */
    public int getAbilityPower()
    {
        return 13;
    }
}
