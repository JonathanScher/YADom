package dominion.strategies;

import org.apache.log4j.Logger;

import dominion.card.Smithy;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class SmithyBigMoney implements Strategy {
	static final Logger LOGGER = Logger.getLogger(SmithyBigMoney.class);
	
	public boolean hasOneSmithy = false;
	public Strategy otherStrat;
	
	public SmithyBigMoney() {
		otherStrat = new BigMoney();
	}
	
	@Override
	public void turn(Player player, Game game) {
		LOGGER.trace("new turn for Smithy Big Money");
		
		if (player.getHand().contains(Smithy.INSTANCE)) {
			game.playCard(player, Smithy.INSTANCE);
		}
		try {
			if (!hasOneSmithy && player.getGold() >= Smithy.INSTANCE.getCost()) {
				game.buy(Smithy.INSTANCE, player);
				hasOneSmithy = true;
			} else {
				otherStrat.turn(player, game);
			}
		} catch (BuyException e) {
			LOGGER.error("buy exception for SmithyBigMoney", e);
		}
	}

}
