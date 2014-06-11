package dominion.interfaces.strategies;

import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;


public interface Strategy {
	void turn(Player player, Game game);

	Strategy copy();

	void pileDepleted(Card card);
}
