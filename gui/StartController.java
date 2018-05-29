package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartController {
	public AnchorPane rootPane;
	public Pane pane;
	public Label label;
	public Button button;
	public void startGame()
	{
		Stage window;
		Stage oldStage;
		try {
			oldStage = (Stage) button.getScene().getWindow();
			oldStage.close();
			//Pane newPane = FXMLLoader.load(getClass().getResource("/gui/GameScreen.fxml"));
			//rootPane.getChildren().setAll(pane);
			Parent root = FXMLLoader.load(getClass().getResource("/gui/GameScreen.fxml"));
			window = new Stage();
			window.setScene(new Scene(root));
			window.setResizable(false);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
