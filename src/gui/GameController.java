package gui;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.Board;
import logic.Game;
import logic.Position;
import units.Unit;
import tiles.Space;

public class GameController {
	@FXML public Label turnLabel;
	@FXML public GridPane guiBoard;
	@FXML public GridPane unitPane;
	@FXML public GridPane menuPane;
	@FXML public Button endButton;
	@FXML public Button loadButton;
	
	private Game game;
	private Position startPos;
	private Position endPos;
	private ImageView oldView;
	private final int LEN = 50;
	private InnerShadow highlight;
	private ArrayList<Node> selection;
	public GameController()
	{
		game = new Game();
		startPos = new Position(-1,-1);
		endPos = new Position(-1,-1);
		oldView = null;
		highlight = new InnerShadow();
		highlight.setColor(Color.DARKCYAN);
		highlight.setRadius(20);
	}
	public void endTurn()
	{
		if (turnLabel.getText().equals("P1 Turn"))
			turnLabel.setText("P2 Turn");
		else
			turnLabel.setText("P1 Turn");
	}
	
	public void init()
	{
		Board board = game.getBoard();
		loadButton.setVisible(false);
		endButton.setDisable(false);
		int num = 0;
		
		for (int row = 0; row < board.getSize(); row++)
		{
			for(int col = 0; col < board.getSize(); col++)
			{
				ImageView cell = (ImageView) getNode(guiBoard, row, col);
				ImageView unitCell = (ImageView) getNode(unitPane, row, col);
				Space space = board.getSpace(new Position(row, col));
				cell.setImage(new Image(space.getGraphic()));
				Unit unit = space.getUnit();
				if(unit != null)
				{
					unitCell.setImage(new Image(unit.getGraphic()));
				}
				
			}
		}
		
		/*
		for( Node node : guiBoard.getChildren())
		{
			if(node instanceof ImageView)
			{
				ImageView cell = (ImageView) node;
				Space space = board.getSpace(new Position(r,c));
				cell.setImage(new Image(space.getGraphic()));
				c++;
				if (c > 15)
				{
					c = 0;
					r++;
				}
				/*
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
		} */
		num = 0;
		//ArrayList<Unit> units0 = game.getBoard().get
		/*for( Node node : unitPane.getChildren())
		{
			if(node instanceof ImageView)
			{
				ImageView unit = (ImageView) node;
				
				if(num < 10 || num > 246)
				{
					unit.setImage(new Image("KnightBlue.png"));
				}
				else
				{
					unit.setImage(null);
				}
				unit.setOnMouseClicked(this::location);
				num++;
			}
		} */
		num = 0;
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
				item.setOnAction(this::location);
				cMenu = new ContextMenu(item);
				
				mButton.setContextMenu(cMenu);
				mButton.getItems().addAll(item);
				menuPane.add(mButton, col, row);
				cMenu.setOnShowing(e -> e.consume());
				mButton.setOnContextMenuRequested(e ->
				{
					e.consume();
					mButton.hide();
					System.out.print("something");
					//ContextMenu menu = (ContextMenu)e.getSource();
					//showMenu((ButtonItem)(menu.getItems().get(0)));
				});
				mButton.setOnMousePressed(e ->
				{
					e.consume();
					showMenu((ButtonItem)mButton.getItems().get(0));
				});
				mButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, e ->
				{
					e.consume();
					System.out.println("handled");
				});
			}
		}
		//menuPane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> e.consume());
	} 
	
	/**
	 * Mouse Event to get two positions on the gridpane
	 * @param e	event
	 */
	public void location(Event e)
	{
		
		ButtonItem item = (ButtonItem) e.getSource();
		MenuButton cell = item.getMenuButton();
		//GridPane pane = (GridPane) cell.getParent();
		int row = GridPane.getRowIndex(cell);
		int col = GridPane.getColumnIndex(cell);
		Node node = getNode(guiBoard,row,col);
		node.setEffect(highlight);
		//if(startPos.getX() < 0 && oldView == null && cell.getImage() != null)
			startPos.setPos(row, col);
			oldView = (ImageView)getNode(unitPane,row,col);/*
		}
		else
			if(oldView != null && cell.getImage() == null)
			{
				endPos.setPos(row, col);
				moveUnit(cell);					
			} */
		System.out.println(row + " " + col);
	}

	/**
	 * moves the unit across gridPane
	 * @param cell the image view where the image is to be move to
	 */
	private void moveUnit(ImageView cell)
	{
		cell.setImage(oldView.getImage());
		oldView.setImage(null);
		Node bg = getNode(guiBoard,startPos.getX(),startPos.getY());
		bg.setEffect(null);
		oldView = null;
		startPos.setPos(-1, -1);
		resetPos();		
		//for(Node node: selection)
		//{
		//	node.setEffect(null);
		//}
		
	}
	
	private void showMenu(ButtonItem item)
	{
		MenuButton button = item.getMenuButton();
		Position pos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
		//Position pos = new Position(row, col);
		ImageView view = (ImageView)getNode(unitPane,pos.getX(),pos.getY());
		if(view.getImage() != null)
		{			
			System.out.println("shown");
			//button.getItems().addAll(arg0)
			//MenuButton button = (MenuButton)(getNode(menuPane,row,col));
			button.show();
		}
		else
		{
			if(startPos.getX() != -1)
			{
				moveUnit(view);
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
}
