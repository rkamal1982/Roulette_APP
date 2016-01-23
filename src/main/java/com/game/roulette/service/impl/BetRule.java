/**
 * 
 */
package com.game.roulette.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.game.roulette.model.Bet;
import com.game.roulette.model.Player;

/**
 * @author Raj_Kamal02
 *
 */
public class BetRule  {
    public static final int BET_MIN = 1;
    public static final int BET_MAX = 36;
    private final Pattern betPattern = Pattern.compile("(.*?) (.*?) (.*?)");
    private Map<String, Player> players;

    public BetRule(Map<String, Player> players) {
        this.players = players;
    }

    Bet parseLine(String line) throws Exception {

        Matcher match = betPattern.matcher(line);
        Bet bet;
        if (match.matches()) {

            bet = new Bet();

            String name = match.group(1);
            String number = match.group(2);
            String amount = match.group(3);

            if (name == null || name.isEmpty()) {
                throw new Exception("name is missing");
            }

            Player player = players.get(name);
            if (player == null) {
                throw new Exception("Player is not registered: " + name);
            }
          
            bet.setPlayer(player);


            if ("EVEN".equalsIgnoreCase(number)) {
                bet.setEven(true);

            } else if ("ODD".equalsIgnoreCase(number)) {
                bet.setOdd(true);

            } else {
                try {
                    int betNumber = Integer.parseInt(number);

                    if (1 > betNumber || betNumber > 36) {
                        throw new Exception("Bet out of range(" + BET_MIN + "-" + BET_MAX);
                    }

                    bet.setNumber(betNumber);

                } catch (NumberFormatException e) {
                    throw new Exception("Invalid bet " + number);
                }

            }


            if (amount == null || amount.isEmpty()) {
                throw new Exception("Amount is missing");
            }

            try {
                double amountD = Double.parseDouble(amount);
                if (amountD < 0) {
                    throw new Exception("Amount can't be negative " + amount);
                }

                bet.setBettingAmount(BigDecimal.valueOf(amountD));

            } catch (NumberFormatException e) {
                throw new Exception("Invalid amount " + amount);
            }

        } else {
            throw new Exception("Invalid input " + line);
        }

        return bet;
    }

}
