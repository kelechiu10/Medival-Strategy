package tiles;

import java.util.NoSuchElementException;
/**
 * Grass Space Class
 * @author Aemilia Russ
 * @version 5/24/18
 */
 public class Water extends Space
 {
    public Water()
    {
        super(false, "water", "water.png");
    }
    
    public void takeDamage(int dmg)
    {
       throw new NoSuchElementException("what are you doing?!");
    }
    
    public String getName()
    {
        return "w";
    }
 }
