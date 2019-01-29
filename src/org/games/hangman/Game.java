/*
 * Hangman game - Java console
 * (C) ..., 2019
 *  
 */

package org.games.hangman;

import static org.games.hangman.IO.*;
import static org.games.hangman.IO.printError;
import static org.games.hangman.IO.printInfo;
import static org.games.hangman.IO.printRed;
import static org.games.hangman.IO.readString;
import static org.games.hangman.Picture.printHangman;

import org.games.hangman.Menu.MenuStatus;

public class Game {
	// start a new game
	public static void startGame() throws Exception {
		(new Game()).run();
	}

	// show game stats
	public static void reportGameStats() {
		Table t = new Table(false, ":-24", ":8");
		t.print("Number of games played", numGames);
		t.print("Number of games you won", gamesWon);
		if (numGames > 0) {
			t.print("Win %", String.format("%.2f%%", (float) gamesWon / (float) numGames * 100));
		}
		t.end();
	}

	static final boolean debug = false;

	// given the word, generate the ------ line to display
	// handle puncturation marks, and numbers.
	// replace only the letters
	private String getGuessLine(String word) {
		char[] lines = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (Character.isLetter(ch)) {
				lines[i] = '-';
			} else {
				lines[i] = ch;
			}
		}

		return String.valueOf(lines);
	}

	private int max(int ...values) {
		int ret = values[0];
        for (int v : values) {
            if (v > ret) {
                ret = v;
            }
        }
		return ret;
	}

	// show the game board
	private void showGameBoard(String cat, int numTries, String word, String lines, String tries) throws Exception {
		int len = max(word.length() * 2, lines.length() * 2, tries.length() * 2, cat.length() * 2);
		
		Table t = new Table(false, ":-20", ":-" + len);
		t.print("Category", cat);
		t.print("Your Guess", lines);
		t.print("Letters guessed", tries);
		t.end();
	}


	private Category cat;

	private void run() throws Exception {
		cat = Category.getCategory();
		if (cat == null) {
			return;
		}

		MenuItem goBack = new MenuItem("Go Back", new MenuAction() {
			@Override
			public MenuStatus called() {
				return MenuStatus.DONE;
			}
		});
		final MenuItem help = new MenuItem(
				Constants.HELP, new MenuAction() {
			@Override
			public MenuStatus called() {
				showHelp();
				return MenuStatus.OK;
			}
		});
		final MenuItem sameCategory = new MenuItem(
				"Play again in the '" + cat.getName() + "' category", 
				new MenuAction() {
			@Override
			public MenuStatus called() {
				return MenuStatus.OK;
			}
		});
		MenuItem newCategory = new MenuItem("Change category", new MenuAction() {
			@Override
			public MenuStatus called() {
				try {
					cat = Category.getCategory();
					sameCategory.setTitle("Play again in the '" + cat.getName() + "' category");
				} catch (Exception e) {
					printError(e.getMessage());
				}
				return MenuStatus.OK;
			}
		});

		Menu menu = new Menu("New Game");
		menu.addMenuItem(sameCategory);
		menu.addMenuItem(newCategory);
		//menu.addMenuItem(help);
		menu.addMenuItem(goBack);

		do {
			runGame(cat);
			reportGameStats();
			pause();
		} while (menu.run() == MenuStatus.OK);
	}

	private void runGame(Category cat) throws Exception {

		String word = cat.getRandomWord();

		numGames++;
		String tries = "";
		String lines = new String(new char[word.length()]).replace('\0', '-');
		lines = replaceLetter(word, lines, ' ');

		lines = getGuessLine(word);

		int numTries = 0;
		String catName = cat.getName();
		while (numTries < MAX_TRIES) {
			printHangman(numTries);
			showGameBoard(catName, numTries, word, lines, tries);

			if (debug) {
				printInfo(word);
			}

			String answer = readString("Enter a letter or word");
			if(answer.equals("?")) {
				answer = getClue(word, lines);
				numTries += 2;
			}
			char [] chars = answer.toCharArray();
			for(char letter: chars) {
				if (!Character.isLetter(letter)) {
					pause("Enter Letters only please.");
					continue;
				}
				letter = Character.toUpperCase(letter);
				if (tries.indexOf(letter) >= 0) {
//					pause("You entered " + letter + " already");
//					continue;
				}
				tries += letter + " ";
				if (word.indexOf(letter) < 0) {
					numTries++;
				} else {
					lines = replaceLetter(word, lines, letter);
					if (lines.equals(word)) {
						printHangman(Picture.WON);
						showGameBoard(catName, numTries, word, lines, tries);
						printInfo("Congratulations! You won!");
						gamesWon++;
						return;
					}
				}
			}
		}
		printHangman(Picture.LOST);
		showGameBoard(catName, numTries, word, lines, tries);
		printRed("Well, try next time. The word was: " + word);
	}

	String getClue(String word, String line) {
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '-') return word.charAt(i) + "";
		}
		return null;
	}


	// given the actual word and the guessed workds, replace the given letter
	private String replaceLetter(String word, String line, char letter) throws Exception {
		char[] a = line.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				a[i] = letter;
			}
		}
		return String.valueOf(a);
	}

	final int MAX_TRIES = 6;

	private static int numGames = 0, gamesWon = 0;
	void showHelp() {
		out.println(
			"You can enter one or more letters\n"
			+ "Enter ? to get a clue. This will take 2 points\n"
		);
	}

}
