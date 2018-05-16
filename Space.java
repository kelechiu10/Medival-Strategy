/**
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
  
  public void addUnit(Unit u)
  {
    Unit = u;
  }
 
  
}
