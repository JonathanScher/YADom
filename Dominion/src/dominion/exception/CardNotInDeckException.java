package dominion.exception;

import dominion.interfaces.Card;

public class CardNotInDeckException extends BuyException {

	private static final long serialVersionUID = 1L;

	public CardNotInDeckException(Card card) {
		this.card = card;
	}
}
