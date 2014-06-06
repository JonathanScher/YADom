package dominion.interfaces.strategies;

import dominion.interfaces.Card;

public class Couple {
	public Card card;
	public Integer numberToBuy;
	
	public Couple() {
	}

	public Couple(Card card, Integer numberToBuy) {
		super();
		this.card = card;
		this.numberToBuy = numberToBuy;
	}

}
