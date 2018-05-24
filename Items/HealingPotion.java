/**
 * class HealingPotion is a healing potion item that heals for 10hp
 *
 */
public class HealingPotion extends Item
{

    private int healthPower;
    
    public HealingPotion()
    {
        usesRemaining = 3;
        healthPower = 10;
    }
    
    /**
     * getPower returns the potency of the healing potion
     * @return int healing power
     */
    public int getPower()
    {
        return healthPower();
    }
}
