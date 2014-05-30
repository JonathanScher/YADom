package dominion.cards;

import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.strategies.CardAction;

public class DoNothing implements CardAction {

	@Override
	public void playCard(Game game, Player player) {
	}

}
