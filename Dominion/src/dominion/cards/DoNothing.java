package dominion.cards;

import dominion.interfaces.CardAction;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class DoNothing implements CardAction {

	@Override
	public void playCard(Game game, Player player) {
	}

}
