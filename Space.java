/**
 * Space Abstract Class
 * @author Claudia Xue, Matthew Oh
 * @version 5/24/18
 */
public abstract class Space
{
    protected String terrain;
    protected Unit unit;
    protected boolean walkable;
  
    /**
     * Constuctor for Space
     * @param walk if the space can be walked on
     * @param ter terrain type
     */
    public Space(boolean walk, String ter)
    {
        walkable = walk;
        terrain = ter;
    }
    
    /**
     * Returns space's ability to be walked on
     * @return space's ability to be walked on
     */
    public boolean walkable()
    {
        return walkable;
    }
  
    /**
     * Sets the unit
     * @param u unit to be set onto space
     */
    public void setUnit(Unit u)
    {
        unit = u;
    }
  
    /**
     * Removes the unit on this tile
     * 
     */
    public void removeUnit()
    {
        unit = null;
    }
    
    /**
     * Returns the unit on the space
     * @return the unit on the space
     */
    public Unit getUnit()
    {
        return unit;
    }
}
