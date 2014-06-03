package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Province extends Card {
	public static final Card INSTANCE = new Province();
	
	
	private Province() {
		name = CardName.PROVINCE;
		victoryValue = 0;
		cost = 5;
		goldValue = 0;
	}
	
	@Override
	public void play(Game game, Player player) {
	}
}
