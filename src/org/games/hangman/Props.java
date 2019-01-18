/*
 * Manage properties
 */
package org.games.hangman;

import java.util.Properties;

public class Props extends Properties {

	public Props(String propFile) throws Exception {
		load(Props.class.getClassLoader().getResourceAsStream(propFile));
	}
}

