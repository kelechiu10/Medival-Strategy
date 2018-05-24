/**
 * Class Item is the superclass for all of the item classes
 *
 */
public abstract class Item
{
    protected int usesRemaining;
    protected int potency;
    protected String name;
    
    public int useItem();
    {
        usesRemaining--;
        return potency;
    }
    
    public int getUsesRemaining()
    {
        return usesRemaining;
    }
}
