/**
 * 
 */
package com.game.roulette.MainService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.game.roulette.model.Bet;
import com.game.roulette.model.Win;
import com.game.roulette.service.BetCalculator;
import com.game.roulette.service.RouletteDashBoard;
import com.game.roulette.service.RouletteNumber;

/**
 * @author Raj_Kamal02
 *
 */
public class Roulette {
	 public static final int CYCLE_PERIOD = 30;
	    private RouletteNumber rouletteNumber;
	    private RouletteDashBoard rouletteDashBoard;
	    private BetCalculator betCalculator;

	    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	    public void play() {

	    	rouletteDashBoard.bettingForNextRound();

	        scheduler.scheduleAtFixedRate(new Runnable() {

	            @Override
	            public void run() {

	                try {
	                    List<Bet> bets = rouletteDashBoard.getBets();

	                    int result = rouletteNumber.play();

	                    List<Win> wins = betCalculator.calculateWins(bets, result);

	                    rouletteDashBoard.getWins(result, wins, bets);

	                    rouletteDashBoard.bettingForNextRound();

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }

	            }
	        }, 30, CYCLE_PERIOD, TimeUnit.SECONDS
	        );


	    }

	  
	    public void setRouletteNumber(RouletteNumber rouletteNumber) {
	        this.rouletteNumber = rouletteNumber;
	    }

	    public void setRouletteDashBoard(RouletteDashBoard rouletteDashBoard) {
	        this.rouletteDashBoard = rouletteDashBoard;
	    }

	    public void setBetCalculator(BetCalculator betCalculator) {
	        this.betCalculator = betCalculator;
	    }

}
