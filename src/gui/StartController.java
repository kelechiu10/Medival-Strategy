package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Used in conjunction with the Start.fxml file to provide the menu before starting the game
 * @author Kelechi Uhegbu
 * @version June 8, 2018
 *
 */
public class StartController {
	public AnchorPane rootPane;
	@FXML public Pane pane;
	@FXML public Button button;
	public TextArea text;
	
	/**
	 * changes screens and starts the game
	 */
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
