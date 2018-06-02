/**
 * Grass Space Class
 * @author Matthew Oh
 * @version 5/31/18
 */
 public class Grass extends Space
 {
     public Grass()
    {
        super(true, "grass", "ground1");
        int num = (int)(Math.random() * 6);
        if(num == 5)
            setGraphic("ground3");
        else
            if(num == 4)
                setGraphic("ground2");
            else
                setGraphic("ground1");
    }
    
    public void takeDamage(int dmg)
    {
        if(getUnit() != null && getUnit().takeDamage(dmg))
          removeUnit();
    }
 }
