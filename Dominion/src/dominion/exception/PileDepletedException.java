package dominion.exception;

import dominion.interfaces.Card;

public class PileDepletedException extends BuyException {
	private static final long serialVersionUID = -974185381380507668L;

	public PileDepletedException(Card card) {
		this.card = card;
	}
}
