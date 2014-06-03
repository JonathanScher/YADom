package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Smithy extends Card {
	public static final Card INSTANCE = new Smithy();

	private Smithy() {
		data = CardData.SMITHY;
	};


	@Override
	public void play(Game game, Player player) {
	}

}
