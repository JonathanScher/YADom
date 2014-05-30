package dominion.strategies;

import dominion.cards.Card;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class SmithyBigMoney implements Strategy {

	@Override
	public void turn(Player player, Game game) {
		// - If havn't bought any Smithy yet and, you have 5 gold, and Smithy is
		// in the GameDeck buy it
		// - Otherwise play BigMoney
		if (player.getHand().contains(Card.SMITHY)) {
			game.playCard(Card.SMITHY);
		}
		try {
			if (player.getGold() >= 4) { // needs a condition to say it's the first one
				game.buy(Card.SMITHY, player);
			}
		} catch (BuyException e) {
		}
	}

}
