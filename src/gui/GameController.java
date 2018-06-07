package gui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.Action;
import logic.Board;
import logic.Game;
import logic.Position;
import tiles.Space;
import units.Priest;
import units.Unit;

public class GameController {
	@FXML public Label turnLabel;
	@FXML public Text moveLeft;
	@FXML public GridPane guiBoard;
	@FXML public GridPane unitPane;
	@FXML public GridPane menuPane;
	@FXML public Button endButton;
	@FXML public Button loadButton;
	@FXML public Button randButton;
	@FXML public VBox redTeam;
	@FXML public VBox blueTeam;
	
	private ArrayList<Label> HPs;
	private Game game;
	private Board board;
	private Position startPos;
	private final int LEN = 50;
	private InnerShadow highlightG;
	private InnerShadow highlightR;
	private ArrayList<Position> selection;
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
	}
	public void endTurn()
	{
		turn++;
		turnLabel.setText("P" + getTurn() + " Turn");
		moves = maxMoves;
		moveLeft.setText(""+moves);
	}
	
	private int getTurn()
	{
		return (turn % 2 + 1);
	}

	public void initRandom()
	{
		game = new Game("random");
		init();
	} 
	public void initRegular()
	{
		game = new Game("regular");
		init();
	} 
	
	private void init()
	{
		board = game.getBoard();
		loadButton.setVisible(false);
		endButton.setDisable(false);
		randButton.setVisible(false);
		moveLeft.setText(""+moves);
		loadBoardWithBars(board);
		
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
		System.out.println(row + " " + col);
	}

	/**
	 * moves the unit across gridPane
	 * @param cell the image view where the image is to be move to
	 */
	private void doAction(Position pos, String op)
	{
		Action act = new Action(pos, startPos, op);
		running = game.runGame(getTurn(), act);
		resetHighlight();
		resetPos();		
		loadBoard(board);
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
		}
	}
	
	private void showMenu(MenuButton button)
	{
		Position pos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
		Unit unit = board.getSpace(pos).getUnit();
		if(!startPos.equals(new Position(-1,-1)) && selection != null && selection.contains(pos))
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
					System.out.println("shown");
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
	@FXML
	public void resetPos()
	{
		startPos.setPos(-1,-1);
		System.out.println("reset");
	}
	
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
	
	private void loadBoardWithBars(Board board)
	{
		UnitButton button;
		ArrayList<Unit> units = game.getUnitList();
		for(Unit unit : units)
		{
			if(unit.getTeam().equals("Red"))
			{
				button = new UnitButton(unit);
				
				
			}
		}
		loadBoard(board);
	}
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
	
	private void resetHighlight()
	{
		if(!startPos.equals(new Position(-1,-1)))
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
}
