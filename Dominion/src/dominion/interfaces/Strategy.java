package dominion.interfaces;

import dominion.PlayerImpl;

public interface Strategy {

	void turn(PlayerImpl player, Game game);

}
