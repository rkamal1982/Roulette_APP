/**
 * 
 */
package com.game.roulette.MainService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.game.roulette.service.BetCalculator;
import com.game.roulette.service.Reader;
import com.game.roulette.service.RouletteDashBoard;
import com.game.roulette.service.RouletteNumber;
import com.game.roulette.service.impl.BetCalculatorImpl;
import com.game.roulette.service.impl.BetPlayer;
import com.game.roulette.service.impl.RouletteConsole;
import com.game.roulette.service.impl.RouletteDisplayNumber;
import com.game.roulette.model.Player;




/**
 * @author Raj_Kamal02
 *
 */
public class PlayRoulette {

	private static final String players="players.txt";
	
	 private static InputStream getStreamToPlayers() {
	        InputStream stream = PlayRoulette.class.getClassLoader().getResourceAsStream(players);
	        if (stream == null) {
	            throw new IllegalStateException("Players are not there");
	        }
	        return stream;
	    }

	

	public static void main(String[] args) throws IOException {
		Roulette roulette = new Roulette();
		roulette.setBetCalculator(new BetCalculatorImpl());
		roulette.setRouletteNumber(new RouletteDisplayNumber());
	       Reader reader = new BetPlayer();
        Map<String,Player> players;
        InputStream streamToPlayers = null;
        try {
            streamToPlayers = getStreamToPlayers();
            players = reader.getPlayers(streamToPlayers);
        } finally {
            if (streamToPlayers != null) {
                streamToPlayers.close();
            }
        }

        RouletteConsole rouletteInterface = new RouletteConsole(players);
        roulette.setRouletteDashBoard(rouletteInterface);
        rouletteInterface.start();
        roulette.play();
		
	}

}
