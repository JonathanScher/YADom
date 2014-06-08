package dominion.interfaces.strategies;

import dominion.interfaces.Card;

public class CardsToBuy {
	public Card card;
	public Integer numberToBuy;
	
	public CardsToBuy() {
	}

	public CardsToBuy(Card card, Integer numberToBuy) {
		super();
		this.card = card;
		this.numberToBuy = numberToBuy;
	}

}
