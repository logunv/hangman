// Hangman IO module

package org.games.hangman;

import java.io.Console;
import java.io.PrintStream;

public class IO {
	static boolean richText = false;

	static Console in = System.console();
	static PrintStream out = System.out;
	static PrintStream err = System.err;
	
	static void printError(String errmsg) {
		printRed(errmsg);
	}
	static void printBlue(String msg) {
		out.println(getColor(msg, fgBlue));
	}
	static void printRed(String msg) {
		err.println(getColor(msg, fgRed));
	}

	static void printInfo(String info) {
		printBlue(info);
	}
	
	public static int readInt(String prompt, int min, int max) throws Exception {
		while(true) {
			try {
				int ret = Integer.parseInt(readString(prompt));
				if(ret >= min && ret <= max) return ret;
				
				String msg = String.format("Incorrect number entered: %d. Enter between %d and %d", ret, min,max);
				printRed(msg);
			} catch(Exception e) {
				printRed("Not a valid input. Try again");
			}
		}
	}

	public static int readInt(String prompt) throws Exception {
		return Integer.parseInt(readString(prompt));
	}

	public static int readInt(String prompt, int def) throws Exception {
		String ret = readString(prompt + " [" + def + "]");
		if(isEmpty(ret)) return def;
		return Integer.parseInt(ret);
	}

	public static boolean readBoolean(String prompt) throws Exception {
		String s = readString(prompt);
		return
			s.equalsIgnoreCase("y") ||
			s.equalsIgnoreCase("yes") ||
			s.equalsIgnoreCase("sure") ||
			s.equalsIgnoreCase("yeah") ||
			s.equalsIgnoreCase("yup") ||
			s.equalsIgnoreCase("si") ||
			s.equalsIgnoreCase("oui")
			;
	}

	public static char readChar(String prompt) throws Exception {
		while(true) {
			String str = readString(prompt);
			if(str.length() == 1) return str.charAt(0);
			if(str.length() > 1) {
				printRed("Entered too many letters. Only one letter please.");
			} else if(str.length() == 0) {
				printRed("Please type a letter");
			}
		}
	}
	public static String readString(String prompt) throws Exception {
		out.print(prompt + "> ");
		return in.readLine();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// foreground colors
	public static int fgRed = 31;
	public static int fgLightGreen = 32;
	public static int fgGold = 33;
	public static int fgBlue = 34;
	public static int fgMagenta = 35;
	public static int fgCyan = 36;
	public static int fgWhite = 37;
	public static int fgBlack = 38;

	// background colors
	public static int bgBlack = 40;
	public static int bgRed = 41;
	public static int bgGreen = 42;
	public static int bgGold = 43;
	public static int bgBlue = 44;
	public static int bgMagenta = 45;
	public static int bgCyan = 46;
	public static int bgGray = 47;
	public static int bgWhite = 48;

	public static String getColor(String str, int fgcolor, int bgcolor) {
		if(!richText) return str;

		String ret = str;

		if(fgcolor != 0) ret = getColor(ret, fgcolor);
		if(bgcolor != 0) ret = getColor(ret, bgcolor);
		
		return ret;
	}
	static String getColor(String str, int color) {
		if(!richText) return str;
		return (char)27 + "[" + color + "m"  + str + (char)27 + "[0m";
	}

	static void clearScreen() {
		if(!richText) return;
		// TODO: does this work on DOS?
		char c = 0x12;
		System.out.print("\033\143");
	}
	
	static void pause() throws Exception {
		pause("");
	}
	static void pause(String msg) throws Exception {
		printInfo(msg);
		readString("Enter to conitnue");
	}
	public static void setRichText(boolean richText) {
		IO.richText = richText;
	}
}
