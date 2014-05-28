package dominion.strategies;

import dominion.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class BigMoney implements Strategy {

	@Override
	public void turn(Player player, Game game) {
		if (player.getGold() > 7) {
			game.buy(Card.PROVINCE);
		} else if (player.getGold() > 5) {
			game.buy(Card.GOLD);
		} else if (player.getGold() > 2) {
			game.buy(Card.SILVER);
		}
		// if I have more than 3 gold, I buy a Silver
		// if I have less than 3 gold, I do nothing
	}

}
