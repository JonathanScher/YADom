package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Duchy extends Card {
	public static final Card INSTANCE = new Duchy();

	private Duchy() {
		name = CardName.DUCHY;
		victoryValue = 2;
		cost = 5;
		goldValue = 0;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
