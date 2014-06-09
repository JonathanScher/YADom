package dominion.interfaces;

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
}
