package dominion.interfaces;

import java.util.List;

import dominion.deck.GameDeck;
import dominion.deck.GameDeckListener;
import dominion.exception.BuyException;

public interface Game extends GameDeckListener {
	void register(Player player);

	List<Integer> getNumberOfCards();

	void play();

	List<Player> winner();

	void buy(Card instance, Player player) throws BuyException;

	void playCard(Player player, Card card);

	GameDeck getGameDeck();

	Player getPlayer(int playerNumber);
}
