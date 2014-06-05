package dominion.exception;

import dominion.interfaces.Card;

public abstract class BuyException extends Exception {
	private static final long serialVersionUID = -1275544597115299872L;
	public Card card;

}
