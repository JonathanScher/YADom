package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Copper extends Card {
	public static final Card INSTANCE = new Copper();

	private Copper() {
		data = CardData.COPPER;
	}

	@Override
	public void play(Game game, Player player) {
	}

}
