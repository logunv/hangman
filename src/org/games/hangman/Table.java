//print data in tabular form
// works fine in xterm. May not work well in other terminals or DOS window
// Tested on Mac Terminal

package org.games.hangman;

import static org.games.hangman.IO.*;

public class Table {

	String hline = "-", line = "|", fmt = "|";
	
	int lineColor = fgBlue;
	
	void init(int ... length) {
		for (int v : length) {
			fmt += " %" + v + "s |";
			v = Math.abs(v);
			String tmp = new String(new char[v + 2]).replace('\0', '-');
			hline += tmp + "-";
			line += tmp + "|";
		}
		fmt += "\n";
		if(header) end();
	}

	boolean header = true;
	public Table(boolean header, String ... columns) {
		this.header = header;
		// col:length (tbd:formatting)
		String [] h = new String[columns.length];
		int [] l = new int[columns.length ];

		int i = 0;
		for (String c : columns) {
			String [] tmp = c.split(":");
			if(tmp.length < 2) {
				err.println("Invalid column format:" + c);
				return;
			}
			h[i] = tmp[0];
			l[i] = Integer.parseInt(tmp[1]);
			i++;
		}
		init(l);
		if(header) head(h);
		
	}

	public Table(String ... columns) {
		this(true, columns);
	}
	
	void head(String ...values) {
//		out.printf(Utils.getColor(String.format(fmt, (Object[])values), Utils.fgBlue));
		out.printf(fmt, (Object[])values);
	}
	
	void printLine() {
		out.println(getColor(line, lineColor));
	}
	void print(Object...values) {
		printLine();
		Object [] v1 = new Object[values.length];
		for(int i = 0; i < values.length; i++) v1[i] = values[i];
		out.printf(fmt, v1);
	}
	
	// color support
	int id = 1;
	void printc(int col, Object...values) {
		printLine();
		Object [] v1 = new Object[values.length];
		for(int i = 0; i < values.length; i++) v1[i] = values[i];
		String tmp = String.format(fmt, v1);
		tmp = getColor(tmp, col);
		out.print(tmp);
	}
	
	void end() {
		out.println(getColor(hline, lineColor));
	}
}
