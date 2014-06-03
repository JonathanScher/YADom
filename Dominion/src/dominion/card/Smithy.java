package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Smithy extends Card {
	private static final int NB_OF_CARDS_SMITHY_DRAW = 3;
	public static final Card INSTANCE = new Smithy();

	private Smithy() {
		data = CardData.SMITHY;
	};


	@Override
	public void play(Game game, Player player) {
		player.draw(NB_OF_CARDS_SMITHY_DRAW);
	}

}
