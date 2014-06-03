package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Gold extends Card {
	public static final Card INSTANCE = new Gold();

	private Gold() {
		name = CardName.GOLD;
		victoryValue = 0;
		cost = 0;
		goldValue = 3;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
