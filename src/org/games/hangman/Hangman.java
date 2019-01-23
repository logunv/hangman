/*
 * Hangman game - Java console
 * (C) ..., 2019
 *  
 */

package org.games.hangman;

import static org.games.hangman.Constants.BYE;
import static org.games.hangman.Constants.DONE_PLAYING;
import static org.games.hangman.Constants.GAME_STATS;
import static org.games.hangman.Constants.HOPE_YOU_ENJOYED;
import static org.games.hangman.Constants.MAIN_MENU;
import static org.games.hangman.Constants.NEW_GAME;
import static org.games.hangman.Constants.SOMETHING_WRONG;
import static org.games.hangman.Constants.WANT_TO_PLAY;
import static org.games.hangman.IO.pause;
import static org.games.hangman.IO.printError;
import static org.games.hangman.IO.printInfo;
import static org.games.hangman.IO.readBoolean;
import static org.games.hangman.Picture.printHangman;

import org.games.hangman.Menu.MenuStatus;

public class Hangman {
	public static void main(String [] args) {
		try {
			// process command line arguments
			if(args.length > 0) {
				if(args[0].equals("-rt")) {
					IO.setRichText(true);
				} else {
//					Table.setRichText(false);
				}
			}
			// print hangman logo
			printHangman(Picture.LOGO);
			
			// ask if the palyer wants to play Hangman
			if(!readBoolean(WANT_TO_PLAY)) {
				printInfo(BYE);
				return;
			}
			
			// start the game
			(new Hangman()).start().end();
			
		} catch(Exception e) {
			printError(SOMETHING_WRONG + e.getMessage());
			e.printStackTrace();
		}
	}

	// start the game
	Hangman start() throws Exception {
		// show the main menu
		MenuItem quit = new MenuItem(DONE_PLAYING, new MenuAction() {
			@Override
			public MenuStatus called() {
				printInfo(HOPE_YOU_ENJOYED);
				return MenuStatus.DONE;
			}
		});

		MenuItem newGame = new MenuItem(NEW_GAME, new MenuAction() {
			@Override
			public MenuStatus called() {
				try {
					Game.startGame();
				} catch(Exception e) {
					printError(e.getMessage());
				}
				return MenuStatus.OK;
			}
		});

		MenuItem stats = new MenuItem(GAME_STATS, new MenuAction() {
			@Override
			public MenuStatus called() {
				Game.reportGameStats();
				return MenuStatus.OK;
			}
		});
		
		Menu menu = new Menu(MAIN_MENU);
		menu.addMenuItem(newGame);
		menu.addMenuItem(stats);
		menu.addMenuItem(quit);

		while(menu.run() == MenuStatus.OK) pause();
		
		return this;
	}

	void end() {
		// end the game
		Game.reportGameStats();
	}

}

/*

Remove special characters

*/
