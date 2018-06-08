package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOver {
	
	static boolean result;
	public static boolean displayEnd(int p)
	{
		Stage window = new Stage();
		Scene scene;
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game Over!");
		window.setMinWidth(500);
		
		Label label = new Label();
		label.setText("Player " + p + "wins!");
		Button closeButton = new Button("Exit");
		closeButton.setOnAction(e -> 
		{
			result = true;
			window.close();
		});
		Button playButton = new Button("Play Again");
		playButton.setOnAction(e ->
		{
			result = false;
			window.close();
		});
		
		VBox box = new VBox(10);
		box.getChildren().addAll(label,closeButton,playButton);
		scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
		return result;
	}
}
