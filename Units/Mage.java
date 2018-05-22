/**
 * Mage Unit Class
 * Attack: 12
 * Defence: 15
 * Health: 40
 * Movement: 4
 * Abilities:
 * -#1 Fireball: Damages unit at a distance
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
        return new Action(target, 17, "attack");
    }
}
