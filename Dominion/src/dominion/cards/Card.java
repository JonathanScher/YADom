package dominion.cards;

import dominion.interfaces.CardAction;

public enum Card {
	COPPER(1, 0, 0, new DoNothing()), //
	ESTATE(0, 1, 2, new DoNothing()), //
	DUCHY(0, 2, 5, new DoNothing()), //
	PROVINCE(0, 3, 8, new DoNothing()), //
	CURSE(0, -1, 0, new DoNothing()), //
	GOLD(3, 0, 6, new DoNothing()), //
	SILVER(2, 0, 3, new DoNothing()), //
	SMITHY(0, 0, 4, new DoNothing());//
	public Integer goldValue;
	public Integer victoryValue;
	public Integer cost;
	public CardAction action;

	Card(Integer goldValue, Integer victoryValue, Integer cost,
			CardAction action) {
		this.goldValue = goldValue;
		this.victoryValue = victoryValue;
		this.cost = cost;
		this.action = action;
	}
}
