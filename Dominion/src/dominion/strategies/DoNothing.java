package dominion.strategies;

import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.Strategy;

public class DoNothing implements Strategy {

	@Override
	public void turn(Player player, Game game) {
	}

	@Override
	public Strategy copy() {
		return new DoNothing();
	}

	@Override
	public void pileDepleted(Card card) {
	}

}
