package dominion;

import java.util.List;

public class Player {

	private static final int INITIAL_NUMBER_OF_ESTATES = 3;
	private static final int INITIAL_NUMBER_OF_COPPERS = 7;

	public PlayerDeck hand;
	public PlayerDeck draw;
	public PlayerDeck discard;

	public Player() {
		hand = new PlayerDeck();
		discard = new PlayerDeck();
	}

	public PlayerDeck getPlayerDeck() {
		PlayerDeck deck = new PlayerDeck();
		addCardsToDeck(deck, Card.COPPER, INITIAL_NUMBER_OF_COPPERS);
		addCardsToDeck(deck, Card.ESTATE, INITIAL_NUMBER_OF_ESTATES);
		deck.shuffle();
		return deck;
	}

	private void addCardsToDeck(List<Card> deck, Card card,
			Integer initialNumber) {
		for (int cpt = 0; cpt < initialNumber; cpt++) {
			deck.add(card);
		}
	}

	public Integer getHandSize() {
		return hand.size();
	}

	public void drawHand() {
		if (draw.size() < 5) {
			int intialDraw = draw.size();
			int numberOfCards = intialDraw;
			drawFromDrawingPile(numberOfCards);
			discard.shuffle();
			draw = discard;
			discard = new PlayerDeck();

			drawFromDrawingPile(Math.min(5 - intialDraw, draw.size()));
		} else {
			drawFromDrawingPile(5);
		}
	}

	private void drawFromDrawingPile(int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
			hand.add(draw.get(0));
			draw.remove(0);
		}
	}

}
