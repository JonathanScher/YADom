package dominion;

public enum Card {
	COPPER(0), ESTATE(1), DUCHY(2), PROVINCE(3), CURSE(-1);
	
	public Integer victoryValue;
	Card(Integer victoryValue){
		this.victoryValue = victoryValue;
	}
	
}
