/**
 * 
 */
package com.game.roulette.service;

import java.util.List;

import com.game.roulette.model.Bet;
import com.game.roulette.model.Win;

/**
 * @author Raj_Kamal02
 *
 */
public interface BetCalculator {
	
	List<Win> calculateWins(List<Bet> bets, int number);

}
