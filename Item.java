/**
 * Class Item is the class for all items
 * @author Aemilia Russ
 * @version 24 May, 2018
 */
public class Item
{
    protected int usesRemaining;
    protected int potency;
    protected String name;
    
    public Item(int power, int uses, String title)
    {
        usesRemaining = uses;
        potency = power;
        name = title;
    }
    
    /**
     * useItem returns the potency of the item and decreases it's uses remaining by one
     * @return integer potency
     */
    public int useItem();
    {
        usesRemaining--;
        return potency;
    }
    
    /**
     * getUsesRemaining returns the remaining uses of the Item
     * @return integer remaining uses
     */
    public int getUsesRemaining()
    {
        return usesRemaining;
    }
}
