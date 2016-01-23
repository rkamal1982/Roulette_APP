/**
 * 
 */
package com.game.roulette.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.game.roulette.model.Player;
import com.game.roulette.service.Reader;

/**
 * @author Raj_Kamal02
 *
 */
public class BetPlayer implements Reader {

	@Override
	public Map<String, Player> getPlayers(InputStream stream)throws IOException {
		 BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	        Map<String, Player> players = new HashMap<>();

	        String line;
	        while ((line = reader.readLine()) != null) {
	            Player player = new Player(line);
	            players.put(line, player);
	        }

	        return players;
	    }
}
