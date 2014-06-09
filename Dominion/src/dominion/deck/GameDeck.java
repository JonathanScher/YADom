package dominion.deck;

import java.util.Collections;
import java.util.HashMap;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.interfaces.Card;

public class GameDeck extends HashMap<Card, Integer> {
	private static final int NUMBER_OF_PILES_TO_BE_DEPLETED_2_PLAYERS = 2;
	private static final int NUMBER_OF_PILES_TO_BE_DEPLETED_MORE_PLAYERS = 3;
	private static final long serialVersionUID = 7699189497179269801L;

	public static GameDeck basicDeck2Players(){
		GameDeck gameDeck = new GameDeck();
		gameDeck.put(Province.INSTANCE, 8);
		gameDeck.put(Duchy.INSTANCE, 8);
		gameDeck.put(Estate.INSTANCE, 8);
		gameDeck.put(Gold.INSTANCE, 30);
		gameDeck.put(Silver.INSTANCE, 40);
		gameDeck.put(Copper.INSTANCE, 60);
		gameDeck.put(Curse.INSTANCE, 10);
		return gameDeck;
	}
	
	public Boolean gameOver(Integer numberOfPlayers) {
		return provincesAreGone() || tooManyPilesEmptied(numberOfPlayers);
	}

	private Boolean tooManyPilesEmptied(Integer numberOfPlayers) {
		Integer numberOfPilesToBeDepleted = NUMBER_OF_PILES_TO_BE_DEPLETED_2_PLAYERS;
		if (numberOfPlayers > 2) {
			numberOfPilesToBeDepleted = NUMBER_OF_PILES_TO_BE_DEPLETED_MORE_PLAYERS;
		}
		return Collections.frequency(values(), Integer.valueOf(0)) >= numberOfPilesToBeDepleted;
	}

	private Boolean provincesAreGone() {
		return get(Province.INSTANCE) != null && get(Province.INSTANCE) == 0;
	}
}
