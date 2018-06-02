/**
 * Space Abstract Class
 * @author Claudia Xue, Matthew Oh
 * @version 5/31/18
 */
public abstract class Space
{
    protected String terrain;
    protected Unit unit;
    protected boolean walkable;
    protected String graphic;
  
    /**
     * Constuctor for Space
     * @param walk if the space can be walked on
     * @param ter terrain type
     */
    public Space(boolean walk, String ter, String graph)
    {
        walkable = walk;
        terrain = ter;
        graphic = graph;
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
    
    public void setGraphic(String str)
    {
        graphic = str;
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
    
    /**
     * Returns a string of the .png file's name
     * @return String of graphic file's name
     */
    public String getGraphic()
    {
        return graphic;
    }
    
    /**
     * Deals damage to whatever is on the space
     * (implemented in each sublclass)
     */
    public abstract void takeDamage(int dmg);
}
