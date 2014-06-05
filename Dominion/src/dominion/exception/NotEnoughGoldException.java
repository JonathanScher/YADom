package dominion.exception;

import dominion.interfaces.Card;

public class NotEnoughGoldException extends BuyException {

	private static final long serialVersionUID = -4263908138772006516L;

	public NotEnoughGoldException(Card card) {
		this.card = card;
	}

}
