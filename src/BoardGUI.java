import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList; 


/**
 * Write a description of JavaFX class Test here.
 *
 * @author Claudia Xue
 * @version 5/31/18
 */
public class BoardGUI extends Application
{
    private Board board;
    private Game game;
    private Label turnLabel = new Label("P1 Turn");
    private Button endTurn = new Button("End Turn");
    private VBox characters1 = new VBox();
    private VBox characters2 = new VBox();
    private BorderPane border = new BorderPane();
    private GridPane gridPic = new GridPane();
    private GridPane gridUnit = new GridPane();
    private GridPane gridMenu = new GridPane();
    private ImageView[] unitImg = new ImageView[20];
    private int moveNum = 5;
    Label moves = new Label("Moves Left: " + moveNum);

    @Override
    public void start(Stage stage) throws Exception
    {
        game = new Game();
        board = game.getBoard();
        
        int index = 0;
        
        Image mt = new Image("Blank.png");

        
        HBox topBar = new HBox(775);
        topBar.getChildren().addAll(turnLabel, moves);

        Scene scene = new Scene(border);
        border.setTop(topBar);

        endTurn.setOnAction(e -> endTurn());

        // puts in spaces from 
        for(int row = 0; row < 16; row++)
        {
            for(int col = 0; col < 16; col++)
            {
                Position pos = new Position(row,col);
                //puts menus in
                MenuButton mBar = new MenuButton("",new ImageView(mt));
                mBar.setOpacity(0);
                gridMenu.add(mBar, row, col);
                //puts background in
                String str = board.getSpace(pos).getGraphic();
                Image temp = new Image(str + ".png");
                gridPic.add(new ImageView(temp), col, row);
                //puts units in
                if(board.getSpace(pos).getUnit() != null)
                {
                    String unit = board.getSpace(new Position(row,col)).getUnit().getGraphic();
                    unitImg[index] = new ImageView(new Image(unit));
                    gridUnit.add(unitImg[index], row, col);
                    index++;
                    
                    //sidebar stuff
                    UnitButton uButton = new UnitButton(board.getSpace(new Position(row,col)).getUnit(), index - 1);
                    InnerShadow highlightR = new InnerShadow();
                    InnerShadow highlightG = new InnerShadow(),highlightY = new InnerShadow();
                    highlightR.setColor(Color.RED);
                    highlightG.setColor(Color.LIME);
                    highlightY.setColor(Color.YELLOW);

                    uButton.setOnAction(e ->
                        {
                            clear();
                            Position startPos = new Position(uButton.getUnit().getPosition().getY(),uButton.getUnit().getPosition().getX());
                            ArrayList canMove = game.getValidAction(startPos,"move");
                            ArrayList canAttack = game.getValidAction(startPos,"attack");
                            for(int count = 0; count < canMove.size(); count++)
                            {
                                Position movPos = (Position)canMove.get(count);
                                Node moveble = getNode(gridPic,movPos.getY(),movPos.getX());
                                MenuButton moveButton = (MenuButton)getNode(gridMenu,movPos.getY(),movPos.getX());
                                MenuItem move = new MenuItem("move");
                                move.setOnAction(f -> doAction(movPos,uButton.getUnit(),"move",uButton.getIndex()));
                                moveButton.getItems().add(move);
                                moveble.setEffect(highlightG);
                            }
                            for(int count = 0; count < canAttack.size(); count++)
                            {
                                Position attPos = (Position)canAttack.get(count);
                                Node moveble = getNode(gridPic,attPos.getY(),attPos.getX());
                                moveble.setEffect(highlightR);
                            }

                            if(uButton.getUnit() instanceof Priest)
                            {
                                ArrayList canHeal = game.getValidAction(startPos,"heal");
                                for(int count = 0; count < canHeal.size(); count++)
                                {
                                    Position healPos = (Position)canHeal.get(count);
                                    Node moveble = getNode(gridPic,healPos.getY(),healPos.getX());
                                    moveble.setEffect(highlightY);
                                }
                            }
                        }
                    );
                    if(board.getSpace(new Position(row,col)).getUnit().getTeam().equals("red"))
                    {
                        characters1.getChildren().add(uButton);
                    }
                    else
                        characters2.getChildren().add(uButton);

                }
            }
        }

        for(int count = 0; count < 16; count++)
        {
            gridUnit.getColumnConstraints().add(new ColumnConstraints(50));
            gridUnit.getRowConstraints().add(new RowConstraints(50));
            gridMenu.getColumnConstraints().add(new ColumnConstraints(50));
            gridMenu.getRowConstraints().add(new RowConstraints(50));
        }

        StackPane board = new StackPane();
        board.getChildren().addAll(gridPic, gridUnit, gridMenu);
        border.setCenter(board);

        characters1.getChildren().add(endTurn);
        border.setRight(characters1);

        stage.setTitle("ImageView");
        stage.setWidth(1000);
        stage.setHeight(1000);
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show(); 
    }

    private void endTurn()
    {
        {
            if (turnLabel.getText().equals("P1 Turn"))
            {
                clear();
                turnLabel.setText("P2 Turn");
                border.setRight(characters2);
                characters2.getChildren().add(endTurn);
            }
            else
            {
                clear();
                turnLabel.setText("P1 Turn");
                border.setRight(characters1);
                characters1.getChildren().add(endTurn);
            }
        }   
    }

    private Node getNode(GridPane pane, int row, int col)
    {
        Node result = null;
        for(Node cell: pane.getChildren())
        {
            if(GridPane.getRowIndex(cell) == row && GridPane.getColumnIndex(cell) == col)
            {
                result = cell;
                break;
            }
        }
        return result;
    }

    private void doAction(Position tar, Unit u, String op, int index)
    {
        Action act = new Action(tar, u.getPosition(), op);
        if(u.getTeam().equals("red"))
            game.runGame(1, act);
        else
            game.runGame(2, act);
        clear();
        gridUnit.add(unitImg[index], tar.getX(), tar.getY());
        //cell.setImage(oldView.getImage());
        //oldView.setImage(null);
        //resetPos();		
        //loadBoard(board);
        moveNum--;
        moves.setText("Moves Left: " + moveNum);
        if(moveNum == 0)
        {
            endTurn();
        }
    }

    private void clear()
    {
        for(int row = 0; row < 16; row++)
        {
            for(int col = 0; col < 16; col++)
            {
                Node nodePic = getNode(gridPic,row,col);
                Node nodeMenu = getNode(gridMenu,row,col);
                nodePic.setEffect(null);
                MenuButton temp = (MenuButton)nodeMenu;
                temp.getItems().clear();
            }
        }
    }

    public static void main(String[] args) 
    {
        boolean end = false;

        Application.launch(args);
        while(end == false)
        {
            //end = game.runGame();
        }

    }

}
