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
public interface RouletteDashBoard {
	
    void start();    
    List<Bet> getBets();  
    void getWins(int result, List<Win> wins, List<Bet> bets);   
    void bettingForNextRound();

}
