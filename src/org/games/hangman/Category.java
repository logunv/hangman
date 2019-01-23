// Manage game categories for Hangman

package org.games.hangman;

import static org.games.hangman.Constants.CAT_FILE;
import static org.games.hangman.IO.clearScreen;
import static org.games.hangman.IO.out;
import static org.games.hangman.IO.printInfo;
import static org.games.hangman.IO.readInt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Category {

	public static Category getCategory() throws Exception {
		
		Props props = new Props(CAT_FILE);
		
		clearScreen();
		printInfo("Categories\n");
		int idx = 0;
		String [] cats = new String[props.size()];
		Set<String> keys = props.stringPropertyNames();
		String fmt = "%4d: %s\n";
		out.printf(fmt, 0, "Go Back");
		for(String key: keys) {
			cats[idx] = key;
			out.printf(fmt, ++idx, props.getProperty(key));
		}
		idx = readInt("\n  Select a category", 0, keys.size());
		if(idx == 0) return null;
		String cat = cats[idx - 1];
		return new Category(props.getProperty(cat), cat + ".txt");
	}
	
	public Category(String cat, String file) throws Exception {
		this.name = cat;
		loadDictionary(file);
	}
	
	String name;
	
	List<String> words = new ArrayList<String>();
	
	// load hangman words from the given words file
	void loadDictionary(String file) throws Exception {
		file = "/" + file;
		InputStream in = getClass().getResourceAsStream(file);
		if(in == null) {
			throw new Exception("Dictionary not found: " + file);
		}
		BufferedReader fin = new BufferedReader(new InputStreamReader(in));
		
		String line;
		while((line = fin.readLine()) != null) {
			words.add(line.toUpperCase());
		}
		
		fin.close();
		if(words.size() <= 0) {
			throw new Exception("Empty words file:" + file);
		}

	}

	// return the name of this category
	public String getName() {
		return name;
	}
	
	Random r = new Random();

	// get a random work from the words list.
	public String getRandomWord() throws Exception {
		
//		return words.get(r.nextInt(words.size()));
		int rnd = r.nextInt(words.size());
		String word = words.get(rnd);
		words.remove(rnd);
		
		return word;
		
	}
}

