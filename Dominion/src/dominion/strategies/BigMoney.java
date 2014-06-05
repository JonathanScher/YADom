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
	private static final Logger logger = Logger.getLogger(BigMoney.class);
	@Override
	public void turn(Player player, Game game) {
		try {
			if (player.getGold() > 7) {
				game.buy(Province.INSTANCE, player);
			} else if (player.getGold() > 5) {
				game.buy(Gold.INSTANCE, player);
			} else if (player.getGold() > 2) {
				game.buy(Silver.INSTANCE, player);
			}
		} catch (BuyException e) {
			logger.error("BigMoney is unable to buy " + e.card.getData()
					+ " but an error occured: " + e.getClass()
					+ ". Number of this card in game deck: "
					+ ((GameImpl) game).gameDeck.get(e.card)
					+ ". Number of PROVINCE left: "
					+ ((GameImpl) game).gameDeck.get(Province.INSTANCE));
		}
	}

}
