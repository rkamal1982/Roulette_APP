/**
 * 
 */
package com.game.roulette.service.impl;

import java.io.Console;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.roulette.model.Bet;
import com.game.roulette.model.Win;
import com.game.roulette.service.RouletteDashBoard;
import com.game.roulette.model.Player;
import com.game.roulette.service.impl.BetRule;


/**
 * @author Raj_Kamal02
 *
 */
public class RouletteConsole implements RouletteDashBoard,Runnable {
	private List<Bet> bets = new ArrayList<>();
    private Map<String, Player> players;
    private  BetRule betRule;
    
    
    

	public RouletteConsole(Map<String, Player> players) {
		    this.players = players;
	        betRule = new BetRule(players);
	}
	
	 private Console getConsole() {
	        return System.console();
	    }

	@Override
	public void start() {
		Console console = getConsole();
        if (console == null) {
            throw new IllegalStateException("CONSOLE IS NOT AVAILABLE");
        }
        Thread thread = new Thread(this);
        thread.setName(RouletteConsole.class.getSimpleName() + "_" + thread.getName());
        thread.setDaemon(true);
        thread.start();
		
	}

	@Override
	public List<Bet> getBets() {
		ArrayList<Bet> betsCopy = new ArrayList<>(bets);
        bets.clear();
        return betsCopy;
	}

	@Override
	public void getWins(int result, List<Win> wins, List<Bet> bets) {
		 Console console = getConsole();
	        console.printf("Number: %d\n", result);
	        console.printf("%-8s %-4s %-4s %-4s\n", "Player", "Bet", "Outcome", "Winnings");
	        console.printf("---\n");

	        //Create structures for easier display
	        Map<String, Win> winsByPlayer = new HashMap<>(wins.size());
	        for (Win win : wins) {
	            Player player = win.getBet().getPlayer();
	            winsByPlayer.put(player.getName(), win);
	        }

	        Map<String, Bet> betsByPlayer = new HashMap<>(wins.size());
	        for (Bet bet : bets) {
	            Player player = bet.getPlayer();
	            betsByPlayer.put(player.getName(), bet);
	        }

	        for (Player player : players.values()) {
	            String name = player.getName();

	            Bet playerBet = betsByPlayer.get(name);
	            String bet = playerBet != null ? playerBet.getBettingAmount().toPlainString() : "-";

	            Win win = winsByPlayer.get(name);
	            String outcome = win != null ? "WIN" : "LOSE";

	            String winAmount = win != null ? win.getBettingAmount().toPlainString() : BigDecimal.ZERO.toPlainString();

	            console.printf("%-8s %-4s %-4s %-8s\n", name, bet, outcome, winAmount);
	        }
	}

	@Override
	public void bettingForNextRound() {
		Console console = getConsole();
        console.printf("\n\nStarting next round in 30 sec!\n\n");
	}

	@Override
	public void run() {
		try {
            Console console = getConsole();
            while (true) {

                console.printf("\n\nbet is open-please provide data:\n\n");

                String nextLine;
                while ((nextLine = readLine(console)) == null) {
                    Thread.sleep(200);
                }

                Bet bet = null;
                try {
                    bet = betRule.parseLine(nextLine);
                } catch (Exception e) {
                   printHelp(e.getMessage());
                }

                if (bet != null) {
                    bets.add(bet);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	private void printHelp(String msg) {
        Console console = getConsole();
        console.printf("%s %s\n\n", "error:", msg);
        console.printf("%s\n", "Usage:", msg);
        console.printf("%s\n\n", "name (bet|EVEN|ODD) amount");
    }
	
	 private String readLine(Console console) throws IOException {
	        if (console.reader().ready()) {
	            return console.readLine();
	        } else {
	            return null;
	        }
	    }

}
