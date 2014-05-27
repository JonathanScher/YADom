package dominion.mock;

import dominion.PlayerImpl;

public class PlayerDummy extends PlayerImpl {
	public Integer handDrawn = 0;

	@Override
	public void drawHand() {
		handDrawn += 1;
	}

}
