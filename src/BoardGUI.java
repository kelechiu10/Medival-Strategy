

 import javafx.application.Application;
 import javafx.scene.Scene; 
 import javafx.scene.control.*; 
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.layout.*;
 import javafx.stage.Stage; 
 import logic.*;
 import tiles.*;
/**
 * Write a description of JavaFX class Test here.
 *
 * @author Claudia Xue
 * @version 5/31/18
 */
public class BoardGUI extends Application
{
    private Board board = new Board(16);
    //private Board board;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        //Game game = new Game();
        //board = game.getBoard();
        
        Image pauseB = new Image("pause.png");
        //Image mt = new Image("Blank.png");
        
        Button pause = new Button("", new ImageView(pauseB));
        Label player = new Label("Player 1's Turn");
        Label moves = new Label("Moves Left: 3");
        HBox topBar = new HBox(300);
        topBar.getChildren().addAll(player, moves, pause);

        BorderPane border = new BorderPane();
        Scene scene = new Scene(border);
        
        border.setTop(topBar);
        GridPane gridPic = new GridPane();
        GridPane gridUnit = new GridPane();
        GridPane gridButton = new GridPane();
        
        for(int count = 0; count < 16; count++)
        {
            gridButton.getColumnConstraints().add(new ColumnConstraints(50));
            gridButton.getRowConstraints().add(new RowConstraints(50));
        }
        
        // puts in spaces from 
        for(int row = 0; row < 16; row++)
        {
            for(int col = 0; col < 16; col++)
            {
                String str = board.getSpace(new Position(row,col)).getGraphic();
                Menu menu = new Menu("",new ImageView(new Image("ArcherBlue3.png")));
                menu.setStyle("-fx-background-color: transparent;");
                menu.getItems().addAll(new MenuItem("Attack"));
                MenuBar mBar = new MenuBar(menu);
                mBar.setStyle("-fx-background-color: transparent;");
                Image temp = new Image(str + ".png");
                gridPic.add(new ImageView(temp), row, col);
                gridButton.add(mBar, row, col);
            }
        }
        
        for(int count = 0; count < 16; count++)
        {
            gridUnit.getColumnConstraints().add(new ColumnConstraints(50));
            gridUnit.getRowConstraints().add(new RowConstraints(50));
        }
        
        StackPane board = new StackPane();
        board.getChildren().addAll(gridPic, gridUnit, gridButton);
        border.setCenter(board);
        
        VBox characters = new VBox();
        characters.getChildren().add(new Button("End Turn"));
        border.setRight(characters);
        
        stage.setTitle("ImageView");
        stage.setWidth(1000);
        stage.setHeight(1000);
        stage.setScene(scene); 
        stage.sizeToScene(); 
        //game.runGame();
        stage.show(); 
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
