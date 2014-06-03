package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Estate extends Card {
	public static final Card INSTANCE = new Estate();

	private Estate() {
		name = CardName.ESTATE;
		victoryValue = 1;
		cost = 2;
		goldValue = 0;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
