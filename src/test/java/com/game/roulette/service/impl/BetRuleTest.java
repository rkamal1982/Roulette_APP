package com.game.roulette.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.game.roulette.service.impl.BetRule;
import com.game.roulette.model.Bet;
import com.game.roulette.model.Player;


public class BetRuleTest {
	@Test
    public void testBetRule() throws Exception {
        Player player1 = new Player("tom");
        Player player2 = new Player("jack");
        

        Map<String,Player> players = new HashMap<>(3);
        players.put(player1.getName(), player1);
        players.put(player2.getName(), player2);
       

        BetRule betRule = new BetRule(players);

        Bet bet = betRule.parseLine("tom ODD 10");

        Assert.assertEquals(bet.getPlayer(), player1);
        Assert.assertTrue(bet.isOdd());
        Assert.assertEquals(bet.getBettingAmount(), BigDecimal.TEN.setScale(1));


        bet = betRule.parseLine("jack EVEN 0");

        Assert.assertEquals(bet.getPlayer(), player2);
        Assert.assertTrue(bet.isEven());
        Assert.assertEquals(bet.getBettingAmount(), BigDecimal.ZERO.setScale(1));

	
}


}
