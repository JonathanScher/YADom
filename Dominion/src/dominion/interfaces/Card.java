package dominion.interfaces;

public abstract class Card {
	protected CardName name;
	protected Integer victoryValue;
	protected Integer cost;
	protected Integer goldValue;
	
	public CardName getName() {
		return name;
	}

	public Integer getVictoryValue() {
		return victoryValue;
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getGoldValue() {
		return goldValue;
	}

	public abstract void play(Game game, Player player);
}
