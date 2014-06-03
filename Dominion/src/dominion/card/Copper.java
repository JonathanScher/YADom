package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Copper extends Card {
	public static final Card INSTANCE = new Copper();

	private Copper() {
		name = CardName.COPPER;
		victoryValue = 0;
		cost = 0;
		goldValue = 1;
	}

	@Override
	public void play(Game game, Player player) {
	}

}
