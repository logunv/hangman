/*
 *  Draw the beautilful hangman picture
 */


package org.games.hangman;

import static org.games.hangman.Constants.*;
import static org.games.hangman.IO.*;

public class Picture {

	public static void printHangman(int picNum) throws Exception {
		if(picNum < 0 || picNum >= pictures.length) {
			printError(INCORRECT_NUM_ERRORS);
			return;
		}
		clearScreen();
		out.print(empty + pictures[picNum] + empty);
	}

	static final String empty =  "\n";
	static final String left =   "|\n";
	static final String top =    "------------    \n";
	
	static final String top1 =   "-------+----    \n";
	static final String head =   "|     (:)       \n"; 
	static final String neck = 	 "|      |        \n";
	static final String hands =  "|     /|\\      \n"; 
	static final String body = 	 "|      |        \n"; 
	static final String legs =   "|     / \\      \n"; 
	static final String bench =  "|  |-------|    \n";
	static final String footer = "---|-------|----\n"; 

	static final String footer1x = "----------------\n"; 
	
	static final String footer1 = "F r e e  t o  g o\n"; 


	static final String [] pictures = {
			// 0
			top + 
			left + 
			left + 
			left + 
			left + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 1
			top1 + 
			head + 
			left + 
			left + 
			left + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 2
			top1 + 
			head + 
			neck + 
			left + 
			left + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 3
			top1 + 
			head + 
			neck + 
			hands + 
			left + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 4
			top1 + 
			head + 
			neck + 
			hands + 
			body + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 5
			top1 + 
			head + 
			neck + 
			hands + 
			body + 
			body + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 6
			top1 + 
			head + 
			neck + 
			hands + 
			body + 
			body + 
			legs + 
			left + 
			left + 
			bench + 
			footer,
			
			// WON: 7
			top + 
			left + 
			left + 
			head + 
			neck + 
			hands + 
			body + 
			legs + 
			footer1,
	};
	public static final int LOST = 6;
	public static final int LOGO = LOST;
	public static final int WON = 7;
}

/*

-------|----
|     (:)
|      |
|     /|\
|      |
|      |
|     / \
|
|  |-------|
---+-------+---

*/

