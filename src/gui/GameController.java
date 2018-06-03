package gui;

import logic.Position;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {
	public Label turnLabel;
	public GridPane guiBoard;
	public GridPane unitPane;
	public Button endButton;
	public Button loadButton;
	private Position startPos = new Position(-1,-1);
	private Position endPos = new Position(-1,-1);
	private ImageView oldView = null;
	public void endTurn()
	{
		if (turnLabel.getText().equals("P1 Turn"))
			turnLabel.setText("P2 Turn");
		else
			turnLabel.setText("P1 Turn");
	}
	
	public void init()
	{
		int num = 0;
		Image ground1 = new Image("ground1.png"); 
		Image ground2 = new Image("ground2.png"); 
		Image ground3 = new Image("ground3.png"); 
		
		
		for( Node node : guiBoard.getChildren())
		{
			if(node instanceof ImageView)
			{
				ImageView cell = (ImageView) node;
				num = (int) (Math.random() * 6);
				if(num < 2)
					cell.setImage(ground1);
				else
					if(num < 4)
						cell.setImage(ground2);
					else
						cell.setImage(ground3);
				//cell.setOnMouseClicked(this::location);
			}
		}
		for( Node node : unitPane.getChildren())
		{
			if(node instanceof ImageView)
			{
				ImageView unit = (ImageView) node;
				if(num < 32 || num > 240)
				{
					unit.setImage(new Image("KnightBLue.png"));
				}
				unit.setOnMouseClicked(this::location);
				num++;
			}
		}
	} 
	
	/**
	 * Mouse Event to get two positions on the gridpane
	 * @param e	event
	 */
	public void location(Event e)
	{
		
		ImageView cell = (ImageView) e.getSource();
		//GridPane pane = (GridPane) cell.getParent();
		int row = GridPane.getRowIndex(cell);
		int col = GridPane.getColumnIndex(cell);
		if(startPos.getX() < 0 && oldView == null && cell.getImage() != null)
		{
			startPos.setPos(row, col);
			oldView = cell;
		}
		else
			if(oldView != null && cell.getImage() == null)
			{
				endPos.setPos(row, col);
				moveUnit(cell);					
			}
		System.out.println(row + " " + col);
	}

	/**
	 * moves the unit across gridPane
	 * @param cell the image view where the image is to be move to
	 */
	private void moveUnit(ImageView cell)
	{
		cell.setImage(oldView.getImage());
		oldView.setImage(null);
		oldView = null;
		startPos.setPos(-1, -1);
	}
}
