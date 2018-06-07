import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    public UnitButton(Unit unit, int index)
    {
        super(unit.getHealth() + "", new ImageView(new Image(unit.getGraphic())));
        myUnit = unit;
        imageNum = index;
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
