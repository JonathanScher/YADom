package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Duchy extends Card {
	public static final Card INSTANCE = new Duchy();

	private Duchy() {
		data = CardData.DUCHY;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
