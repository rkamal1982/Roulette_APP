/**
 * 
 */
package com.game.roulette.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.game.roulette.model.Player;

/**
 * @author Raj_Kamal02
 *
 */
public interface Reader {
	
	 Map<String,Player> getPlayers(InputStream stream) throws IOException;

}
