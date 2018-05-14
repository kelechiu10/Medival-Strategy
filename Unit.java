/**
 * Abstract Class Unit
 * @author Matthew Oh
 * @version 6/11/18
 */
public abstract class Unit
{
    protected int attack;
    protected int maxHealth;
    protected int health;
    protected int moveSpeed;
    protected Ability[] abilities;
    
    /**
     * Constructor for unit
     * @param a attack of the unit
     * @param h max health of the unit
     * @param m movement of the unit
     */
    public Unit(int a, int h, int m)
    {
        attack = a;
        maxHealth = h;
        health = h;
        moveSpeed = m;
    }
    
    /**
     * Damages the unit, returns status of unit
     * @param damage damage to be taken by this unit
     * @return true if unit has died, false if not
     */
    public boolean takeDamage(int damage)
    {
        health -= damage;
        return health > 0;
    }
    
    /**
     * Heals the unit
     * @param heal increases health of this unit up to max health
     */
    public void takeHeal(int heal)
    {
        health += heal;
        if (health > maxHealth) health = maxHealth;
    }
}
