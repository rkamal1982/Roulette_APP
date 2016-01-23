/**
 * 
 */
package com.game.roulette.model;

import java.math.BigDecimal;

/**
 * @author Raj_Kamal02
 *
 */
public class Bet {
	
	private int number;
	private boolean odd;
	private boolean even;
	private BigDecimal bettingAmount;
	private Player player;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isOdd() {
		return odd;
	}
	public void setOdd(boolean odd) {
		this.odd = odd;
	}
	public boolean isEven() {
		return even;
	}
	public void setEven(boolean even) {
		this.even = even;
	}
	public BigDecimal getBettingAmount() {
		return bettingAmount;
	}
	public void setBettingAmount(BigDecimal bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	

}
