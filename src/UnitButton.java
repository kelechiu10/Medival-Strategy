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

	board = game.getBoard();
			loadButton.setVisible(false);
			endButton.setDisable(false);
			randButton.setVisible(false);
			moveLeft.setText(""+moves);
			loadBoard(board);
			
			for(int col = 0; col < 16; col++)
			{
				for(int row = 0; row < 16; row++)
				{
					ContextMenu cMenu;
					MenuButton mButton = new MenuButton();
					mButton.setPrefHeight(LEN);
					mButton.setPrefWidth(LEN);
					mButton.setOpacity(0);	
					ButtonItem item = new ButtonItem(mButton, "move");
					ButtonItem item2= new ButtonItem(mButton, "attack");
					item.setOnAction(this::location);
					item2.setOnAction(this::location);
					cMenu = new ContextMenu(item,item2);	
					mButton.setContextMenu(cMenu);
					mButton.getItems().addAll(item,item2);
					menuPane.add(mButton, col, row);
					cMenu.setOnShowing(e -> e.consume());
					mButton.setOnMousePressed(e ->
					{
						e.consume();
						showMenu(mButton);
					});
				}
			}
}
