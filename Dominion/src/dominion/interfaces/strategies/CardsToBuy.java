package dominion.interfaces.strategies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import dominion.interfaces.Card;

public class CardsToBuy {
	public Card card;
	public Integer numberToBuy;

	public CardsToBuy(Card card, Integer numberToBuy) {
		super();
		this.card = card;
		this.numberToBuy = numberToBuy;
	}

	public CardsToBuy(CardsToBuy origin) {
		this.card = origin.card;
		this.numberToBuy = origin.numberToBuy;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
