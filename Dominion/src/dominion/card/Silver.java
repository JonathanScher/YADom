package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Silver extends Card {
	public static final Card INSTANCE = new Silver();

	private Silver() {
		data = CardData.SILVER;
	};

	@Override
	public void play(Game game, Player player) {
	}

}
