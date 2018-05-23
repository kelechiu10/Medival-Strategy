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
     */
    public Knight()
    {
        super(20,20,65,5);
    }
    
    /**
     * Gets Knight's ability
     * Knight has no abilities for now
     * @param target target of the ability
     * @param num number of ability to be used
     */
    public Action getAbility(Position target, int num)
    {
        return attack();
    }
    
    /**
     * Returns target restrictions for Knight's Abilities
     * 0: adjacent spaces
     * 1: anywhere
     * @param num number of ability 
     */
    public int abilityRestriction(int num)
    {
        return 0;
    }
}
