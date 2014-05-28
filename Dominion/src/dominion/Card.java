package dominion;

public enum Card {
	COPPER(1), ESTATE(2, 1), DUCHY(5, 2), PROVINCE(8, 3), CURSE(0, -1), GOLD(3), SILVER(
			2);
	public Integer goldValue;
	public Integer victoryValue;

	Card(Integer goldValue) {
		this.goldValue = goldValue;
		this.victoryValue = 0;
	}

	Card(Integer goldValue, Integer victoryValue) {
		this.goldValue = goldValue;
		this.victoryValue = victoryValue;
	}

}
