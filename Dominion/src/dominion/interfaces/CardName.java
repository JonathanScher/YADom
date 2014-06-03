package dominion.interfaces;



public enum CardName {
	COPPER(1, 0, 0), //
	ESTATE(0, 1, 2), //
	DUCHY(0, 2, 5), //
	PROVINCE(0, 3, 8), //
	CURSE(0, -1, 0), //
	GOLD(3, 0, 6), //
	SILVER(2, 0, 3), //
	SMITHY(0, 0, 4);//
	public Integer goldValue;
	public Integer victoryValue;
	public Integer cost;

	CardName(Integer goldValue, Integer victoryValue, Integer cost) {
		this.goldValue = goldValue;
		this.victoryValue = victoryValue;
		this.cost = cost;
	}
}
