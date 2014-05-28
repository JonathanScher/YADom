package dominion.interfaces;

import java.util.List;

import dominion.Card;
import dominion.GameDeck;

public interface Game {
	void register(Player player);
	GameDeck getGameDeck();
	List<Integer> getNumberOfCards();
	void play();
	Player winner();
	Boolean gameOver();
	void buy(Card card, Player player);
}
