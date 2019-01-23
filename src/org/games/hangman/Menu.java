/*
 * Hangman - Menu Handler
 * 
 * (C) ..., 2019
 *  
 */

package org.games.hangman;

import static org.games.hangman.Constants.appName;
import static org.games.hangman.IO.*;

import java.util.Vector;

import org.games.hangman.Menu.MenuStatus;

public class Menu {
	String title;
	public Menu(String title) {
		this.title = title;
	}
	
	Vector<MenuItem> menuItems = new Vector<MenuItem>();
	
	void addMenuItem(MenuItem item) {
		menuItems.add(item);
	}

	// run menu and return the menu action status
	MenuStatus run() throws Exception {
		clearScreen();
		// display menu
		out.printf(appName + ": " + title + "\n\n");
		int idx = 1;
		for(MenuItem menu: menuItems) {
			out.printf("%4d. %s\n", idx++, menu.help);
		}
		int opt = readInt("\n  Enter option", 1, menuItems.size());
		
		// when an option is selected, run it's action handler
		return menuItems.get(opt-1).menuAction.called();
	}
	
	enum MenuStatus {
		DONE,
		OK,
		ERROR
	}
}

interface MenuAction {
	MenuStatus called();
}

class MenuItem {
	public MenuItem(String help, MenuAction menuAction) {
		this.help = help;
		this.menuAction = menuAction;
	}
	String help;
	MenuAction menuAction;
	public void setTitle(String help) {
		this.help = help;
	}
}
