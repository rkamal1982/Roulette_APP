/**
 * 
 */
package com.game.roulette.model;

import java.math.BigDecimal;

/**
 * @author Raj_Kamal02
 *
 */
public class Win {
	
	private Bet bet;
	private BigDecimal bettingAmount;
	public Win(Bet bet, BigDecimal bettingAmount) {
		super();
		this.bet = bet;
		this.bettingAmount = bettingAmount;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
	public BigDecimal getBettingAmount() {
		return bettingAmount;
	}
	public void setBettingAmount(BigDecimal bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	
	
	

}
