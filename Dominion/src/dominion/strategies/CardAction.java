package dominion.strategies;

import dominion.interfaces.Game;
import dominion.interfaces.Player;

public interface CardAction {
	void playCard(Game game, Player player);
}
