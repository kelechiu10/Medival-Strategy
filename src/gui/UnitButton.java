package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import units.Unit;

/**
 * UnitButton is an child of a Button that holds a unit (used as a health bar)
 *
 * @author Claudia Xue
 * @author Kelechi Uhegbu
 * @version 6/7/18
 */
public class UnitButton extends Button
{
    private Unit myUnit; //unit that it is "binded" to
    
    /**
     * 
     */
    public UnitButton(Unit unit)
    {
        super(unit.getHealth() + "", new ImageView(new Image(unit.getGraphic())));
        myUnit = unit;
        textProperty().set("" + unit.getHealth());
    }
    
    public Unit getUnit()
    {
        return myUnit;
    }
    
    public void setLabel(String s)
    {
    	textProperty().set(s);
    }
}
