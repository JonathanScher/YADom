package dominion.interfaces;

import java.util.List;

import dominion.GameDeck;
import dominion.Player;

public interface Game {
	void register(Player player);
	GameDeck getGameDeck();
	List<Integer> getNumberOfCards();
}
