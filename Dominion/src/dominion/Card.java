package dominion;

public enum Card {
	COPPER(1, 0), ESTATE(0, 1), DUCHY(0, 2), PROVINCE(0, 3), CURSE(0, -1), GOLD(3, 0), SILVER(
			2, 0);
	public Integer goldValue;
	public Integer victoryValue;

	Card(Integer goldValue, Integer victoryValue) {
		this.goldValue = goldValue;
		this.victoryValue = victoryValue;
	}

}
