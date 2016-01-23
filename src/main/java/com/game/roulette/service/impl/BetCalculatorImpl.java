/**
 * 
 */
package com.game.roulette.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.game.roulette.model.Bet;
import com.game.roulette.model.Win;
import com.game.roulette.service.BetCalculator;

/**
 * @author Raj_Kamal02
 *
 */
public class BetCalculatorImpl implements BetCalculator {

	@Override
	public List<Win> calculateWins(List<Bet> bets, int number) {
		 List<Win> wins = new ArrayList<>();
	        boolean isEven = number % 2 == 0;
	       

	        for (Bet bet : bets) {

	            BigDecimal winAmount = null;

	            if (bet.getNumber() == number) {
	                winAmount = bet.getBettingAmount().multiply(BigDecimal.valueOf(36));

	            } else if (isEven && bet.isEven()
	                    || !isEven && bet.isOdd()) {

	                winAmount = bet.getBettingAmount().multiply(BigDecimal.valueOf(2));
	            }

	            if (winAmount != null) {
	                Win win = new Win(bet, winAmount);
	                wins.add(win);
	            }
	        }

	        return wins;
	    }

}
