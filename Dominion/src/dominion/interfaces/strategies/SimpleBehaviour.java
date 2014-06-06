package dominion.interfaces.strategies;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dominion.card.Smithy;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public abstract class SimpleBehaviour implements Strategy {
	private static final Logger LOGGER = Logger
			.getLogger(SimpleBehaviour.class);
	protected List<Couple> buyOrder;

	protected SimpleBehaviour() {
		buyOrder = new ArrayList<Couple>();
	}

	@Override
	public void turn(Player player, Game game) {
		play(player, game);
		buy(player, game);
	}

	protected void buy(Player player, Game game) {
		Integer gold = player.getGold();
		for (Couple couple : buyOrder) {
			if (couple.numberToBuy > 0 && couple.card.getCost() <= gold) {
				try {
					game.buy(couple.card, player);
					couple.numberToBuy--;
					break;
				} catch (BuyException e) {
					LOGGER.error(e);
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
