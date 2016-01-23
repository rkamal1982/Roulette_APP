/**
 * 
 */
package com.game.roulette.service.impl;

import com.game.roulette.service.RouletteNumber;
import java.util.Random;

/**
 * @author Raj_Kamal02
 *
 */
public class RouletteDisplayNumber implements RouletteNumber {
	private final Random random = new Random();
	@Override
	public int play() {
		return 1 + random.nextInt(36);
	}

}
