package dominion.strategies;

import dominion.cards.Card;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class SmithyBigMoney implements Strategy {
	public boolean hasOneSmithy = false;
	public Strategy otherStrat;
	
	public SmithyBigMoney() {
		otherStrat = new BigMoney();
	}
	
	@Override
	public void turn(Player player, Game game) {
		if (player.getHand().contains(Card.SMITHY)) {
			game.playCard(Card.SMITHY);
		}
		try {
			if (!hasOneSmithy && player.getGold() >= 4) {
				game.buy(Card.SMITHY, player);
				hasOneSmithy = true;
			} else {
				otherStrat.turn(player, game);
			}
		} catch (BuyException e) {
		}
	}

}
