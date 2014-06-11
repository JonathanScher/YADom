package dominion.deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.exception.BuyException;
import dominion.exception.CardNotInDeckException;
import dominion.exception.PileDepletedException;
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

	protected List<GameDeckListener> observers;
	
	public GameDeck() {
		observers = new ArrayList<GameDeckListener>();
	}

	public GameDeck(GameDeck origin) {
		putAll(origin);
		observers = new ArrayList<GameDeckListener>();
	}

	public static GameDeck basicDeck2Players() {
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

	public void addObserver(GameDeckListener observer) {
		observers.add(observer);
	}

	public void notifyAllDepleted(Card instance) {
		observers.parallelStream().forEach(x -> x.pileDepleted(instance));
	}

	public void buy(Card card) throws BuyException {
		if (get(card) == null) {
			throw new CardNotInDeckException(card);
		}
		if (get(card) < 1) {
			throw new PileDepletedException(card);
		}
		int numberOfCards = get(card);
		numberOfCards -= 1;
		put(card, numberOfCards);
		
		if (numberOfCards == 0) {
			notifyAllDepleted(card);
		}		
	}
}
