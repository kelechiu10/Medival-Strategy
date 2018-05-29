package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameController {
	public Label turnLabel;
	public GridPane guiBoard;
	
	public void endTurn()
	{
		if (turnLabel.getText().equals("P1 Turn"))
			turnLabel.setText("P2 Turn");
		else
			turnLabel.setText("P1 Turn");
	}
}
