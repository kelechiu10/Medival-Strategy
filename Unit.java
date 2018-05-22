/**
 * Abstract Class Unit
 * @author Matthew Oh
 * @version 5/18/18
 */
public abstract class Unit
{
    protected int attack;
    protected int defence;
    protected int maxHealth;
    protected int health;
    protected int moveSpeed;
    protected Position position;
    
    /**
     * Constructor for unit
     * @param atk attack of the unit
     * @param hp max health of the unit
     * @param move range of movement of the unit
     */
    public Unit(int atk, int def, int hp, int move)
    {
        attack = atk;
        defence = def;
        maxHealth = hp;
        health = hp;
        moveSpeed = move;
    }
    
    /**
     * Basic attack on the unit at a certain positon
     * Precondition: space has a unit
     * @param target position of the unit to be attacked
     */
    public void attack(Position target)
    {
        Board.dealDamage(target, attack);
    }
    
    /**
     * Sets the position of the unit
     * @param area new position for the unit
     */
    public void move(Position area)
    {
        position = area;
    }
    
    /**
     * Damages the unit, returns status of unit
     * @param damage damage to be taken by this unit
     * @return true if unit has died, false if not
     */
    public boolean takeDamage(int attackPower)
    {
        health -= 10 * attackPower / defence;
        return health <= 0;
    }
    
    /**
     * Heals the unit
     * @param heal increases health of this unit up to max health
     */
    public void heal(int heal)
    {
        health += heal;
        if (health > maxHealth) health = maxHealth;
    }
    
    /**
     * Uses a unit's ability
     * @param num the ability number to be used
     */
    public abstract void useAbility(int num);
}
