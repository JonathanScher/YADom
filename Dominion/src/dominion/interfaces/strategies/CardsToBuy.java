package dominion.interfaces.strategies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import dominion.interfaces.Card;

public class CardsToBuy {
	public Card card;
	public Integer numberToBuy;

	public CardsToBuy(Card card, Integer numberToBuy) {
		super();
		this.card = card;
		this.numberToBuy = numberToBuy;
	}

	@Override
	public Object clone() {
		return new CardsToBuy(card, numberToBuy);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
