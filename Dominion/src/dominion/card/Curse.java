package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Curse extends Card {
	public static final Card INSTANCE = new Curse();

	private Curse() {
		name = CardName.CURSE;
		victoryValue = -1;
		cost = 0;
		goldValue = 0;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
