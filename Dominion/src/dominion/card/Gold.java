package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Gold extends Card {
	public static final Card INSTANCE = new Gold();

	private Gold() {
		data = CardData.GOLD;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
