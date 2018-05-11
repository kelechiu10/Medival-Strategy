/**
 * Abstract Class Piece
 * @author Matthew Oh
 * @version 6/11/18
 */
public abstract class Piece
{
    protected int attack;
    protected int maxHealth;
    protected int health;
    protected int move;
    protected Ability[] abilities;
    
    /**
     * Constructor for piece
     * @param a attack of the piece
     * @param h max health of the piece
     * @param m movement of the piece
     */
    public Piece(int a, int h, int m)
    {
        attack = a;
        maxHealth = h;
        health = h;
        move = m;
    }
    
    /**
     * Damages the piece, returns status of piece
     * @param damage damage to be taken by this piece
     * @return true if piece has died, false if not
     */
    public boolean takeDamage(int damage)
    {
        health -= damage;
        return health > 0;
    }
    
    /**
     * Heals the piece
     * @param heal increases health of this piece up to max health
     */
    public void takeHeal(int heal)
    {
        health += heal;
        if (health > maxHealth) health = maxHealth;
    }
}
