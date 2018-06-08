package gui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Action;
import logic.Board;
import logic.Game;
import logic.Position;
import tiles.Space;
import units.Priest;
import units.Unit;

public class GameController {
	/** label showing the turn*/
	@FXML public Label turnLabel;
	@FXML public Text moveLeft;
	@FXML public GridPane guiBoard;
	@FXML public GridPane unitPane;
	@FXML public GridPane menuPane;
	@FXML public Button endButton;
	@FXML public Button loadButton;
	@FXML public Button randButton;
	@FXML public VBox redTeam;
	@FXML public HBox blueTeam;
	
	private final Position nullPos = new Position(-1,-1);
	private Game game;
	private Board board;
	private Position startPos;
	private final int LEN = 50;
	private InnerShadow highlightG;
	private InnerShadow highlightR;
	private ArrayList<Position> selection;
	private ArrayList<UnitButton> hpBars;
	private int moves;
	private final int maxMoves;
	private int turn;
	private String op; //operation for action
	private boolean running;
	public GameController()
	{
		startPos = new Position(-1,-1);
		highlightG = new InnerShadow();
		highlightG.setColor(Color.DARKCYAN);
		highlightG.setRadius(LEN/2);
		highlightR = new InnerShadow();
		highlightR.setColor(Color.RED);
		turn = 0;
		maxMoves = 3;
		moves = maxMoves;
		running = true;
		hpBars = new ArrayList<UnitButton>();
	}
	/**
	 * provides a method to end the turn of the current player
	 */
	public void endTurn()
	{
		turn++;
		turnLabel.setText("P" + getTurn() + " Turn");
		moves = maxMoves;
		moveLeft.setText(""+moves);
	}
	
	//get the current turn (1/2)
	private int getTurn()
	{
		return (turn % 2 + 1);
	}

	/**
	 * creates and loads a game with a random map
	 */
	public void initRandom()
	{
		game = new Game(5);
		init();
	} 
	
	/**
	 * creates and loads a game from a preset map
	 */
	public void initRegular()
	{
		game = new Game(1);
		init();
	} 
	
	/**
	 * gets the GUI ready for the first turn of the game
	 */
	private void init()
	{
		//get board and update containers and other GUI nodes
		board = game.getBoard();
		loadButton.setVisible(false);
		endButton.setDisable(false);
		randButton.setVisible(false);
		moveLeft.setText(""+moves);
		loadBoardWithBars(board);
		
		//load the buttons with move and attack actions
		for(int col = 0; col < 16; col++)
		{
			for(int row = 0; row < 16; row++)
			{
				ContextMenu cMenu;
				MenuButton mButton = new MenuButton();
				mButton.setPrefHeight(LEN);
				mButton.setPrefWidth(LEN);
				mButton.setOpacity(0);	
				ButtonItem item = new ButtonItem(mButton, "move");
				ButtonItem item2= new ButtonItem(mButton, "attack");
				item.setOnAction(this::location);
				item2.setOnAction(this::location);
				cMenu = new ContextMenu(item,item2);	
				mButton.setContextMenu(cMenu);
				mButton.getItems().addAll(item,item2);
				menuPane.add(mButton, col, row);
				cMenu.setOnShowing(e -> e.consume());
				mButton.setOnMousePressed(e ->
				{
					e.consume();
					showMenu(mButton);
				});
			}
		}
	}
	/**
	 * Mouse Event to get two positions on the gridpane
	 * @param e	event
	 */
	public void location(Event e)
	{
		resetHighlight();
		ButtonItem item = (ButtonItem) e.getSource();
		MenuButton cell = item.getMenuButton();
		int row = GridPane.getRowIndex(cell);
		int col = GridPane.getColumnIndex(cell);
		Node node = getNode(guiBoard,row,col);
		node.setEffect(highlightG);
		startPos.setPos(row, col);
		op = item.getText();
		selection = game.getValidAction(startPos, op);
		for(Position pos : selection)
		{
			Node view = getNode(guiBoard,pos.getX(),pos.getY());
			view.setEffect(highlightR);
		}
		//System.out.println(row + " " + col);
	}

	/**
	 * moves the unit across gridPane
	 * @param cell the image view where the image is to be move to
	 */
	private void doAction(Position pos, String op)
	{
		boolean end;
		Action act = new Action(pos, startPos, op);
		running = game.runGame(getTurn(), act);
		resetHighlight();
		resetPos();		
		loadBoard(board);
		setHP();
		if(running)
		{
			moves--;
			if(moves == 0)
				endTurn();
			else
				moveLeft.setText(""+moves);
		}
		else
		{
			System.out.println("Game over");
			end = GameOver.displayEnd(getTurn());
			if(end)
				( (Stage)guiBoard.getScene().getWindow() ).close();
			else
				resetGame();
		}
	}
	
	/**
	 * Show the Menu of possible actions, or getting click of the target of the action
	 * @param button that was pressed
	 */
	private void showMenu(MenuButton button)
	{
		Position pos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
		Unit unit = board.getSpace(pos).getUnit();
		if(!startPos.equals(nullPos) && selection != null && selection.contains(pos))
		{
			doAction(pos, op);
		}
		else
			if(unit != null)
			{
				String color = unit.getTeam();
				if( (color.equalsIgnoreCase("red") && getTurn() == 1) || (color.equalsIgnoreCase("blue") && getTurn() == 2) )
				{
					ObservableList<MenuItem> items = button.getItems();
					//System.out.println("shown");
					if(unit instanceof Priest)
					{
						if(items.size() < 3)
						{
							ButtonItem heal = new ButtonItem(button, "heal");
							heal.setOnAction(this::location);
							items.add(2,heal);
						}
					}
					else
						if(items.size() > 2)
						{
							items.remove(2);
						}
					button.show();
				}
			}
		
	}
	//reset start position
	private void resetPos()
	{
		startPos.setPos(-1,-1);
	}
	
	/**
	 * provides a method to get a Node from a GridPane
	 * @param pane the GridPane that contains the node
	 * @param row row location of the Node
	 * @param col column location of the Node
	 * @return the Node at that position
	 */
	private Node getNode(GridPane pane, int row, int col)
	{
		Node result = null;
		ObservableList<Node> nodes = pane.getChildren();
		for(int i = 0; i < nodes.size(); i++)
		{
			Node cell = nodes.get(i);
			if(GridPane.getRowIndex(cell) == row && GridPane.getColumnIndex(cell) == col)
			{
				result = cell;
				i = nodes.size();
			}
		}
		return result;
	}
	
	/**
	 * load the board and set up the Health bars
	 * @param board
	 */
	private void loadBoardWithBars(Board board)
	{
		UnitButton button;
		ArrayList<Unit> units = game.getUnitList();
		for(Unit unit : units)
		{
			button = new UnitButton(unit);
			button.setDisable(true);
			if(unit.getTeam().equals("red"))
			{
				redTeam.getChildren().add(button);
			}
			else
			{
				blueTeam.getChildren().add(button);
			}
			hpBars.add(button);
		}
		loadBoard(board);
	}
	/**
	 * load the current state of the game
	 * @param board game board to load GUI from
	 */
	private void loadBoard(Board board)
	{
		for (int col = 0; col < board.getSize(); col++)
		{
			for(int row = 0; row < board.getSize(); row++)
			{
				ImageView cell = (ImageView) getNode(guiBoard, row, col);
				ImageView unitCell = (ImageView) getNode(unitPane, row, col);
				Space space = board.getSpace(new Position(row, col));
				cell.setImage(new Image(space.getGraphic()));
				Unit unit = space.getUnit();
				if(unit != null)
					unitCell.setImage(new Image(unit.getGraphic()));
				else
					unitCell.setImage(null);
			}
		}
	}
	
	//reset shadow effects to null
	private void resetHighlight()
	{
		if(!startPos.equals(nullPos))
		{
			Node bg = getNode(guiBoard,startPos.getX(),startPos.getY());
			bg.setEffect(null);
		}
		if(selection != null)
		{
			for(Position position : selection)
			{
				Node view = getNode(guiBoard,position.getX(),position.getY());
				view.setEffect(null);
			}
		}
	}
	
	/**
	 * updates the HP values for the units
	 */
	private void setHP()
	{
		for(UnitButton b : hpBars)
		{
			b.setLabel(""+b.getUnit().getHealth());
		}
	}
	
	private void resetGame()
	{
		try 
		{
			Stage oldStage = (Stage) guiBoard.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/GameScreen.fxml"));
			oldStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
