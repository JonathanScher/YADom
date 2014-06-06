package dominion.strategies;

import org.apache.log4j.Logger;

import dominion.GameImpl;
import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class BigMoney implements Strategy {
	private static final Logger LOGGER = Logger.getLogger(BigMoney.class);
	@Override
	public void turn(Player player, Game game) {
		try {
			if (player.getGold() >= Province.INSTANCE.getCost()) {
				game.buy(Province.INSTANCE, player);
			} else if (player.getGold() >= Gold.INSTANCE.getCost()) {
				game.buy(Gold.INSTANCE, player);
			} else if (player.getGold() >= Silver.INSTANCE.getCost()) {
				game.buy(Silver.INSTANCE, player);
			}
		} catch (BuyException e) {
			LOGGER.error("BigMoney is unable to buy " + e.card.getData()
					+ " but an error occured: " + e.getClass()
					+ ". Number of this card in game deck: "
					+ ((GameImpl) game).gameDeck.get(e.card)
					+ ". Number of PROVINCE left: "
					+ ((GameImpl) game).gameDeck.get(Province.INSTANCE));
		}
	}

}
