package gui;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * an implementation of a MenuItem that knows tits own MenuButton
 * @author Kelechi Uhegbu
 * @version June 8, 2018
 *
 */
public class ButtonItem extends MenuItem{

	private MenuButton button;
	/**
	 * creates
	 * @param b the MenuButton that will hold the ButtonItem
	 */
	public ButtonItem(MenuButton b)
	{
		super();
		button = b;
	}
	
	/**
	 * 
	 * @param b the MenuButton that will hold the ButtonItem
	 * @param text the label for the item
	 */
	public ButtonItem(MenuButton b, String text)
	{
		super(text);
		button = b;
	}
	/**
	 * provides a method for obtaining the ButtonItem's MenuButton that it is being contained in
	 * @return the MenuButton it is tied to
	 */
	public MenuButton getMenuButton()
	{
		return button;
	}
}
