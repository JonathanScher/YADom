package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Smithy extends Card {
	public static final Card INSTANCE = new Smithy();

	private Smithy() {
		name = CardName.SMITHY;
		victoryValue = 3;
		cost = 6;
		goldValue = 0;
	};


	@Override
	public void play(Game game, Player player) {
	}

}
