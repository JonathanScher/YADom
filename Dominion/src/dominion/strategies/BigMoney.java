package dominion.strategies;

import dominion.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class BigMoney implements Strategy {

	@Override
	public void turn(Player player, Game game) {
		if (player.getGold() > 7) {
			game.buy(Card.PROVINCE, player);
		} else if (player.getGold() > 5) {
			game.buy(Card.GOLD, player);
		} else if (player.getGold() > 2) {
			game.buy(Card.SILVER, player);
		}
	}

}
