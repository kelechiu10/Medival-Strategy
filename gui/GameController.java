package gui;

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
	public Button endButton;
	public Button loadButton;
	
	public void endTurn()
	{
		if (turnLabel.getText().equals("P1 Turn"))
			turnLabel.setText("P2 Turn");
		else
			turnLabel.setText("P1 Turn");
	}
	
	public void init()
	{
		int num;
		Image ground1 = new Image("assets/ground1.png"); 
		Image ground2 = new Image("assets/ground2.png"); 
		Image ground3 = new Image("assets/ground3.png"); 
		
		
		for( Node node : guiBoard.getChildren())
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
			cell.setOnMouseClicked(this::location);
		}
	} 
	
	public void location(Event e)
	{
		Node cell = (Node) e.getSource();
		GridPane pane = (GridPane) cell.getParent();
		int row = pane.getRowIndex(cell);
		int col = pane.getColumnIndex(cell);
		System.out.println(row + " " + col);
	}

}
