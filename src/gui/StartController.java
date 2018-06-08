package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartController {
	public AnchorPane rootPane;
	public Pane pane;
	public Button button;
	public TextArea text;
	
	public void startGame()
	{
		Stage oldStage;
		try 
		{
			oldStage = (Stage) button.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/GameScreen.fxml"));
			oldStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
