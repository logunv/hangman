/*
 *  Draw the beautilful hangman picture
 */


package org.games.hangman;

import static org.games.hangman.Constants.INCORRECT_NUM_ERRORS;
import static org.games.hangman.IO.*;

public class Picture {

	public static void printHangman(int picNum) throws Exception {
		if(picNum < 0 || picNum >= pictures.length) {
			printError(INCORRECT_NUM_ERRORS);
			return;
		}
		clearScreen();
		if(picNum == WON) {
			printBlue(empty + pictures[picNum] + empty);
		} else {
			printRed(empty + pictures[picNum] + empty);
		}
	}

	static final String empty =  "\n";
	static final String left =   "|\n";
	static final String top =    "------------    \n";
	
	static final String top1 =   "-------+----    \n";
	static final String head =   "|     ( )       \n"; 
	static final String body =   "|      |      \n"; 
static final String leftHand  =  "|     /|      \n"; 
static final String rightHand =  "|     /|\\      \n"; 
static final String rightHand1 = "|     \\O/      \n" + 
								 "|      |\n"; 
static final String leftLeg   =  "|     /      \n"; 
static final String rightLeg  =  "|     / \\      \n"; 
	static final String bench =  "|  |-------|    \n";
	static final String footer = "---|-------|----\n"; 

	static final String footer1x = "----------------\n"; 
	
	//static final String footer1 = "F R E E  T O  G O\n"; 
	  static final String footer1 = " Y O U  W O N !! \n"; 


	static final String [] pictures = {
			// 0
			top + 
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
			bench + 
			footer,
			
			// 2
			top1 + 
			head + 
			body + 
			left + 
			left + 
			left + 
			bench + 
			footer,
			
			// 3
			top1 + 
			head +
			leftHand + 
			left + 
			left + 
			bench + 
			footer,
			
			// 4
			top1 + 
			head + 
			rightHand + 
			left + 
			left + 
			bench + 
			footer,
			
			// 5
			top1 + 
			head + 
			rightHand + 
			leftLeg + 
			left + 
			bench + 
			footer,
			
			// LOGO: 6
			top1 + 
			head + 
			rightHand + 
			rightLeg + 
			left + 
			left + 
			bench + 
			footer,
			
			// WON: 7
			top + 
			left + 
			left + 
			left + 
			rightHand1 + 
			rightLeg + 
			footer1,
	};
	public static final int LOST = 6;
	public static final int LOGO = LOST;
	public static final int WON = 7;
}

/*

-------|----
|     ( )
|     /|\
|     / \
|
|
|  |-------|
---+-------+---


*/

