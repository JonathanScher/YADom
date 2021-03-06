package dominion;

import java.util.List;

import org.apache.log4j.Logger;

import dominion.card.Copper;
import dominion.card.Estate;
import dominion.deck.PlayerDeck;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.Strategy;
import dominion.strategies.DoNothing;

public class PlayerImpl implements Player {
	private static final Logger LOGGER = Logger.getLogger(PlayerImpl.class);
	private static final int BUY_PER_TURN = 1;
	private static final int DRAWING_SIZE = 5;
	private static final int INITIAL_NUMBER_OF_ESTATES = 3;
	private static final int INITIAL_NUMBER_OF_COPPERS = 7;

	public PlayerDeck hand;
	public PlayerDeck pile;
	public PlayerDeck discard;
	public Strategy strategy;

	public Integer buyLeft;

	@Override
	public int hashCode() {
		if (strategy == null) {
			return 0;
		} else {
			return strategy.hashCode();
		}
	}

	@Override
	public boolean equals(Object other) {
		try {
			PlayerImpl otherPlayer = (PlayerImpl) other;
			return (strategy == null && otherPlayer.strategy == null)
					|| (strategy != null && otherPlayer.strategy != null && strategy
							.equals(otherPlayer.strategy));
		} catch (ClassCastException e) {
			return false;
		}
	}

	public PlayerImpl() {
		strategy = new DoNothing();
		hand = new PlayerDeck();
		discard = new PlayerDeck();
		pile = new PlayerDeck();
		buyLeft = 1;
	}

	public PlayerImpl(Player origin) {
		strategy = origin.getStrategy().copy();
		hand = new PlayerDeck(origin.getHand());
		discard = new PlayerDeck(origin.getDiscard());
		pile = new PlayerDeck(origin.getPile());
		buyLeft = origin.getBuyLeft();
	}

	public PlayerImpl(Strategy simpleBehaviour) {
		this();
		strategy = simpleBehaviour;
	}

	@Override
	public void initPile() {
		addCardsToDeck(pile, Copper.INSTANCE, INITIAL_NUMBER_OF_COPPERS);
		addCardsToDeck(pile, Estate.INSTANCE, INITIAL_NUMBER_OF_ESTATES);
		pile.shuffle();
	}

	@Override
	public Integer getHandSize() {
		return hand.size();
	}

	@Override
	public void drawHand() {
		draw(DRAWING_SIZE);
	}

	public PlayerDeck getPlayerDeck() {
		return pile;
	}

	private void addCardsToDeck(List<Card> deck, Card card,
			Integer initialNumber) {
		for (int cpt = 0; cpt < initialNumber; cpt++) {
			deck.add(card);
		}
	}

	private void drawAndShuffle(Integer numberOfCards) {
		int intialDraw = pile.size();
		drawFromDrawingPile(intialDraw);
		discard.shuffle();
		pile = discard;
		discard = new PlayerDeck();

		drawFromDrawingPile(Math.min(numberOfCards - intialDraw, pile.size()));
	}

	private void drawFromDrawingPile(int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
			LOGGER.trace("Player draws " + pile.get(0).getData());
			hand.add(pile.get(0));
			pile.remove(0);
		}
	}

	@Override
	public Integer victoryValue() {
		return discard.victoryValue() + pile.victoryValue()
				+ hand.victoryValue();
	}

	@Override
	public int compareTo(Player otherPlayer) {
		return this.victoryValue().compareTo(otherPlayer.victoryValue());
	}

	@Override
	public void turn(Game game) {
		LOGGER.trace("-----------------");
		buyLeft = BUY_PER_TURN;
		strategy.turn(this, game);
		discard.addAll(hand);
		hand = new PlayerDeck();
		drawHand();
	}

	@Override
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;

	}

	@Override
	public Integer getGold() {
		return hand.goldValue();
	}

	@Override
	public void giveCard(Card card) {
		discard.add(card);
	}

	@Override
	public void bought(Card card) {
		LOGGER.trace("Player buys " + card.getData());
		giveCard(card);
		buyLeft -= 1;
	}

	@Override
	public int getBuyLeft() {
		return buyLeft;
	}

	@Override
	public PlayerDeck getHand() {
		return hand;
	}

	@Override
	public void draw(Integer numberOfCards) {
		if (pile.size() < numberOfCards) {
			drawAndShuffle(numberOfCards);
		} else {
			drawFromDrawingPile(numberOfCards);
		}
	}

	@Override
	public Strategy getStrategy() {
		return strategy;
	}

	@Override
	public PlayerDeck getDiscard() {
		return discard;
	}

	@Override
	public PlayerDeck getPile() {
		return pile;
	}

	@Override
	public String toString() {
		return "PlayerImpl [strategy=" + strategy + "]";
	}

	@Override
	public void pileDepleted(Card card) {
		strategy.pileDepleted(card);
	}
}
