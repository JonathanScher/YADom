package dominion.mock;

import dominion.deck.PlayerDeck;

public class PlayerDeckNoShuffle extends PlayerDeck {

	private static final long serialVersionUID = -6721115875301940150L;

	@Override
	public void shuffle() {
		shuffled += 1;
	}
}
