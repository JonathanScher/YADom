package dominion;

import java.util.List;

import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;
import dominion.strategies.DoNothing;

public class PlayerImpl implements Player {

	private static final int DRAWING_SIZE = 5;
	private static final int INITIAL_NUMBER_OF_ESTATES = 3;
	private static final int INITIAL_NUMBER_OF_COPPERS = 7;

	public PlayerDeck hand;
	public PlayerDeck pile;
	public PlayerDeck discard;
	public Strategy strategy;

	public PlayerImpl() {
		strategy = new DoNothing();
		hand = new PlayerDeck();
		discard = new PlayerDeck();
		pile = new PlayerDeck();
	}
	
	//TODO: create a method "turn"

	@Override
	public void initPile() { // TODO: never called!!!
		addCardsToDeck(pile, Card.COPPER, INITIAL_NUMBER_OF_COPPERS);
		addCardsToDeck(pile, Card.ESTATE, INITIAL_NUMBER_OF_ESTATES);
		pile.shuffle();
	}

	@Override
	public Integer getHandSize() {
		return hand.size();
	}

	@Override
	public void drawHand() {
		if (pile.size() < DRAWING_SIZE) {
			drawAndShuffle();
		} else {
			drawFromDrawingPile(DRAWING_SIZE);
		}
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

	private void drawAndShuffle() {
		int intialDraw = pile.size();
		drawFromDrawingPile(intialDraw);
		discard.shuffle();
		pile = discard;
		discard = new PlayerDeck();

		drawFromDrawingPile(Math.min(5 - intialDraw, pile.size()));
	}

	private void drawFromDrawingPile(int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
			hand.add(pile.get(0));
			pile.remove(0);
		}
	}

	@Override
	public Integer victoryValue() {
		return discard.value() + pile.value() + hand.value();
	}

	@Override
	public int compareTo(Player otherPlayer) {
		return this.victoryValue().compareTo(otherPlayer.victoryValue());
	}

	@Override
	public void turn(Game game) {
		strategy.turn(this, game);
	}

}
