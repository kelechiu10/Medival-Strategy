import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import units.Unit;

/**
 * Write a description of JavaFX class UnitButton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UnitButton extends Button
{
    private Unit myUnit;
    private int imageNum;
    
    /**
     * 
     */
    public UnitButton(Unit unit)
    {
        super(unit.getHealth() + "", new ImageView(new Image(unit.getGraphic())));
        myUnit = unit;
        this.textProperty().bind(new SimpleIntegerProperty((Integer)unit.getHealth()).asString());
    }
    
    public Unit getUnit()
    {
        return myUnit;
    }
    
    public int getIndex()
    {
        return imageNum;
    }
}
