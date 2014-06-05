package dominion.exception;

import dominion.interfaces.Card;

public class NotAllowedToBuyException extends BuyException {

	private static final long serialVersionUID = -4884095745016884673L;

	public NotAllowedToBuyException(Card card) {
		this.card = card;
	}

}
