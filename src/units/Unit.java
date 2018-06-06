package units;
import logic.Action;
import logic.Position;
/**
 * Abstract Class Unit
 * @author Matthew Oh
 * @version 5/24/18
 */
public abstract class Unit
{
    protected int attack;
    protected int defence;
    protected int maxHealth;
    protected int health;
    protected double moveSpeed;
    protected Position current;
    protected String team;
    protected String name;
    
    /**
     * Constructor for unit
     * @param atk attack of the unit
     * @param def defence of the unit
     * @param hp max health of the unit
     * @param move range of movement of the unit
     * @param t team of the unit ("Red"/"Blue")
     * @param n name of the unit
     */
    public Unit(int atk, int def, int hp, double move, String t, String n)
    {
        attack = atk;
        defence = def;
        maxHealth = hp;
        health = hp;
        moveSpeed = move;
        team = t;
        name = n;
    }
    
    public int getAttackValue()
    {
        return attack;
    }
    
    /**
     * Sets the position of the unit
     * @param area new position for the unit
     */
    public void move(Position area)
    {
        current = area;
    }
    
    /**
     * Returns the unit's move speed
     * @return the unit's move speed
     */
    public double getMoveSpeed()
    {
        return moveSpeed;
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
     * Returns the name of the corresponding graphic image of the unit
     * @return name of the image file of the unit
     */
    public String getGraphic()
    {
        return name + team + ".png";
    }
    
    /**
     * Gets a unit's ability
     * @param target target of the ability
     */
    public abstract Action getAbility(Position target);
    
    /**
     * Returns the range of the unit's ability
     * @return the range of the ability 
     */
    public abstract double getRange();
    
    /**
     * Gets the power of the unit's ability
     * @return the power of the unit's ability
     */
    public abstract int getAbilityPower();
}
