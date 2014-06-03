package dominion.card;

import dominion.interfaces.Card;
import dominion.interfaces.CardName;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Silver extends Card {
	public static final Card INSTANCE = new Silver();

	private Silver() {
		name = CardName.SILVER;
		victoryValue = 0;
		cost = 0;
		goldValue = 2;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
