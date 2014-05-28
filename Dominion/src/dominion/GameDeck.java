package dominion;

import java.util.Collections;
import java.util.HashMap;

public class GameDeck extends HashMap<Card, Integer> {
	private static final int NUMBER_OF_PILES_TO_BE_DEPLETED_2_PLAYERS = 2;
	private static final int NUMBER_OF_PILES_TO_BE_DEPLETED_MORE_PLAYERS = 3;
	private static final long serialVersionUID = 7699189497179269801L;

	public Boolean gameOver(Integer numberOfPlayers) {
		// TODO might want to add a condition on time or number of turns
		return provincesAreGone() || tooManyPilesEmptied(numberOfPlayers);
	}

	private Boolean tooManyPilesEmptied(Integer numberOfPlayers) {
		Integer numberOfPilesToBeDepleted = NUMBER_OF_PILES_TO_BE_DEPLETED_2_PLAYERS;
		if (numberOfPlayers > 2) {
			numberOfPilesToBeDepleted = NUMBER_OF_PILES_TO_BE_DEPLETED_MORE_PLAYERS;
		}
		return Collections.frequency(values(), new Integer(0)) >= numberOfPilesToBeDepleted;
	}

	private Boolean provincesAreGone() {
		return get(Card.PROVINCE) != null && get(Card.PROVINCE) == 0;
	}
}
