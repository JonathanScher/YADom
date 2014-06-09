package dominion.interfaces;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import dominion.card.data.CardData;

public abstract class Card {
	protected CardData data;

	public CardData getData() {
		return data;
	}

	public Integer getVictoryValue() {
		return data.victoryValue;
	}

	public Integer getCost() {
		return data.cost;
	}

	public Integer getGoldValue() {
		return data.goldValue;
	}

	public void play(Game game, Player player) {
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
}
