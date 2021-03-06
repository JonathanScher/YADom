package dominion.interfaces.strategies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

import dominion.card.Smithy;
import dominion.exception.BuyException;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class SimpleBehaviour implements Strategy {
	public static Logger logger = Logger.getLogger(SimpleBehaviour.class);
	public BuyOrder buyOrder;

	protected SimpleBehaviour() {
		buyOrder = new BuyOrder();
	}

	public SimpleBehaviour(BuyOrder buyOrder) {
		this.buyOrder = buyOrder;
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
					logger.error("BuyException of type " + e.getClass()
							+ " for card " + e.card);
				}
			}
		}
	}

	protected void play(Player player, Game game) {
		if (player.getHand().contains(Smithy.INSTANCE)) {
			game.playCard(player, Smithy.INSTANCE);
		}
	}

	@Override
	public Strategy copy() {
		SimpleBehaviour strategy = new SimpleBehaviour();
		strategy.buyOrder = new BuyOrder(buyOrder);
		return strategy;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return "SimpleBehaviour [buyOrder=" + buyOrder + "]";
	}

	@Override
	public void pileDepleted(Card card) {
		buyOrder.forEach(x -> {
			if (x.card.equals(card)) {
				x.numberToBuy = 0;
			}
		});
	}

}
