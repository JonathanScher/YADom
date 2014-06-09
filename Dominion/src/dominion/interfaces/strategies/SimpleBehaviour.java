package dominion.interfaces.strategies;

import org.apache.log4j.Logger;

import dominion.card.Smithy;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class SimpleBehaviour implements Strategy {
	public static Logger logger = Logger
			.getLogger(SimpleBehaviour.class);
	public BuyOrder buyOrder;

	public SimpleBehaviour() {
		buyOrder = new BuyOrder();
	}

	@Override
	public void turn(Player player, Game game) {
		play(player, game);
		buy(player, game);
	}

	protected void buy(Player player, Game game) {
		Integer gold = player.getGold();
		for (CardsToBuy couple : buyOrder) {
			if (couple.numberToBuy > 0 && couple.card.getCost() <= gold) {
				try {
					game.buy(couple.card, player);
					couple.numberToBuy--;
					break;
				} catch (BuyException e) {
					logger.error(e);
				}
			}
		}
	}

	protected void play(Player player, Game game) {
		if (player.getHand().contains(Smithy.INSTANCE)) {
			game.playCard(player, Smithy.INSTANCE);
		}
	}
}
