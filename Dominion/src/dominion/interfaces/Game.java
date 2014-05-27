package dominion.interfaces;

import java.util.List;

import dominion.GameDeck;

public interface Game {
	void register(Player player);
	GameDeck getGameDeck();
	List<Integer> getNumberOfCards();
}
