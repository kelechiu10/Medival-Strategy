package tiles;

/**
 * Grass Space Class
 * @author Matthew Oh
 * @version 5/31/18
 */
 public class Grass extends Space
 {
     public Grass()
    {
        super(true, "grass", "ground1.png");
        int num = (int)(Math.random() * 6);
        if(num == 5)
            setGraphic("ground3.png");
        else
            if(num == 4)
                setGraphic("ground2.png");
            else
                setGraphic("ground1.png");
    }
    
    public void takeDamage(int dmg)
    {
        if(getUnit() != null && getUnit().takeDamage(dmg))
          removeUnit();
    }
 }
