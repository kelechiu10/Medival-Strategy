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
	public GridPane unitPane;
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
				if(num < 32 || num > 240)
				{
					ImageView unit = (ImageView) node;
					unit.setImage(new Image("KnightBLue.png"));
					unit.setOnMouseClicked(this::location);
				}
				num++;
			}
		}
	} 
	
	public void location(Event e)
	{
		Node cell = (Node) e.getSource();
		//GridPane pane = (GridPane) cell.getParent();
		int row = GridPane.getRowIndex(cell);
		int col = GridPane.getColumnIndex(cell);
		System.out.println(row + " " + col);
	}

}
