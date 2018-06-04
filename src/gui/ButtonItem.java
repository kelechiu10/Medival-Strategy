package gui;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class ButtonItem extends MenuItem{

	private MenuButton button;
	public ButtonItem(MenuButton b)
	{
		super();
		button = b;
	}
	public ButtonItem(MenuButton b, String text)
	{
		super(text);
		button = b;
	}
	public MenuButton getMenuButton()
	{
		return button;
	}
}
