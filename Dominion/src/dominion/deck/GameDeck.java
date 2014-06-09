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
	private static final int NUMBER_OF_CURSES_2_PLAYERS = 10;
	private static final int NUMBER_OF_COPPERS = 60;
	private static final int NUMBER_OF_SILVER_CARDS = 40;
	private static final int NUMBER_OF_GOLD_CARDS = 30;
	private static final int NUMBER_OF_VICTORY_CARDS_2_PLAYERS = 8;
	private static final int NUMBER_OF_PILES_TO_BE_DEPLETED_2_PLAYERS = 2;
	private static final int NUMBER_OF_PILES_TO_BE_DEPLETED_MORE_PLAYERS = 3;
	private static final long serialVersionUID = 7699189497179269801L;

	public static GameDeck basicDeck2Players(){
		GameDeck gameDeck = new GameDeck();
		gameDeck.put(Province.INSTANCE, NUMBER_OF_VICTORY_CARDS_2_PLAYERS);
		gameDeck.put(Duchy.INSTANCE, NUMBER_OF_VICTORY_CARDS_2_PLAYERS);
		gameDeck.put(Estate.INSTANCE, NUMBER_OF_VICTORY_CARDS_2_PLAYERS);
		gameDeck.put(Gold.INSTANCE, NUMBER_OF_GOLD_CARDS);
		gameDeck.put(Silver.INSTANCE, NUMBER_OF_SILVER_CARDS);
		gameDeck.put(Copper.INSTANCE, NUMBER_OF_COPPERS);
		gameDeck.put(Curse.INSTANCE, NUMBER_OF_CURSES_2_PLAYERS);
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
