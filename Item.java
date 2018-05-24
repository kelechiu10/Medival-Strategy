/**
 * Class Item is the superclass for all of the item classes
 *
 */
public abstract class Item
{
    protected int usesRemaining;
    protected String name;
    
    public int useItem();
    
    public int getUsesRemaining()
    {
        return usesRemaining;
    }
}
