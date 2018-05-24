/**
 * Space Abstract Class
 * @author Claudia Xue
 * @version 5/24/18
 */

public abstract Space
{
    //protected Event[] events;
    protected String terrain;
    protected Unit unit;
    protected boolean walkable;
  
    public Space(boolean walk/* ,Event[] e*/)
    {
        walkable = walk;
        //events = e;
    }
    
    public boolean walkable()
    {
        return walkable;
    }
  
    public void setUnit(Unit u)
    {
        unit = u;
    }
  
    public Unit getUnit()
    {
        return unit;
    }
}
