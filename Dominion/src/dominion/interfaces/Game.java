package dominion.interfaces;

import java.util.List;

import dominion.cards.Card;
import dominion.deck.GameDeck;
import dominion.exception.BuyException;

public interface Game {
	void register(Player player);
	GameDeck getGameDeck();
	List<Integer> getNumberOfCards();
	void play();
	Player winner();
	Boolean gameOver();
	void buy(Card card, Player player) throws BuyException;
	void playCard(Card card);
}
