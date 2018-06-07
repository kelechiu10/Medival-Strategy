package tiles;

/**
 * Grass Space Class
 * @author Aemilia Russ
 * @version 5/30/18
 */
 public class GrassFort extends Space
 {
    private int fortHealth;
    
    public GrassFort()
    {
        super(true, "grass", "tower.png");
        fortHealth = 20;
    }
    
    public void takeDamage(int dmg)
    {
        if(fortHealth > 0)
          fortHealth -= (dmg * 10) / 5;
        else
           if(getUnit() != null && getUnit().takeDamage(dmg))
             removeUnit();
    }
    
    public String getName()
    {
        if(getUnit() != null)
            return getUnit().toString();
        else
            return "E";
    }
    
    /**
     * A method to obtain the current status of the fort
     * @return a boolean for wether the fort is down
     */
    public boolean fortDown()
    {
    	return fortHealth <= 0;
    }
 }
