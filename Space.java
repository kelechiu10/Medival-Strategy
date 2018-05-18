/**
 * Space Abstract Class
 * @author Claudia Xue
 * @version 5/16/18
 */

public abstract Space
{
  protected Event[] events;
  //image
  private Unit unit;
  
  public Space(Event[] e)
  {
    events = e;
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
