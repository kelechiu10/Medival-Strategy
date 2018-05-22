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
     * @param num number of ability to be used
     */
    public Action getAbility(Position target, int num)
    {
        return attack();
    }
}
