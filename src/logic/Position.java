package logic;
/**
 * Position Object
 * because for some reason we can't just use point2d
 * Holds the position of a unit on the Board object
 * @author Matthew Oh
 * @version 5/17/18
 */
 public class Position
 {
    private int xPos;
    private int yPos;
    
    /**
     * Constructor for Position
     * @param x x position
     * @param y y position
     */
    public Position(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    /**
     * Returns the x position
     * @return the x position
     */
    public int getX()
    {
        return xPos;
    }
    
    /**
     * Returns the y position
     * @return the y position
     */
    public int getY()
    {
        return yPos;
    }
    
    /**
     * Sets the position
     * @param x new x position
     * @param y new y position
     */
    public void setPos(int x, int y)
    {
        xPos = x;
        yPos = y;
    }
    
    @Override
    public boolean equals(Object other)
    {
    	boolean result = false;
    	if(other instanceof Position)
    	{
    		Position pos = (Position)other;
    		if(getX() == pos.getX() && getY() == pos.getY())
    			result = true;
    	}
    	return result;
    }
 }
