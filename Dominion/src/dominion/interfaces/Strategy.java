package dominion.interfaces;

import dominion.PlayerImpl;

public interface Strategy {

	void turn(Player player, Game game);

}
