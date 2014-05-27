package dominion;

import java.util.List;

import dominion.interfaces.Player;

public class PlayerImpl implements Player {

	private static final int DRAWING_SIZE = 5;
	private static final int INITIAL_NUMBER_OF_ESTATES = 3;
	private static final int INITIAL_NUMBER_OF_COPPERS = 7;

	public PlayerDeck hand;
	public PlayerDeck draw;
	public PlayerDeck discard;

	public PlayerImpl() {
		hand = new PlayerDeck();
		discard = new PlayerDeck();
		draw = new PlayerDeck();
	}

	@Override
	public void initDraw() { //TODO: never called!!!
		addCardsToDeck(draw, Card.COPPER, INITIAL_NUMBER_OF_COPPERS);
		addCardsToDeck(draw, Card.ESTATE, INITIAL_NUMBER_OF_ESTATES);
		draw.shuffle();
	}

	@Override
	public Integer getHandSize() {
		return hand.size();
	}

	@Override
	public void drawHand() {
		if (draw.size() < DRAWING_SIZE) {
			drawAndShuffle();
		} else {
			drawFromDrawingPile(DRAWING_SIZE);
		}
	}

	public PlayerDeck getPlayerDeck() {
		return draw;
	}

	private void addCardsToDeck(List<Card> deck, Card card,
			Integer initialNumber) {
		for (int cpt = 0; cpt < initialNumber; cpt++) {
			deck.add(card);
		}
	}

	private void drawAndShuffle() {
		int intialDraw = draw.size();
		drawFromDrawingPile(intialDraw);
		discard.shuffle();
		draw = discard;
		discard = new PlayerDeck();

		drawFromDrawingPile(Math.min(5 - intialDraw, draw.size()));
	}

	private void drawFromDrawingPile(int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
			hand.add(draw.get(0));
			draw.remove(0);
		}
	}

}
