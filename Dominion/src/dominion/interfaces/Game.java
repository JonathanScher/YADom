package dominion.interfaces;

import java.util.List;

import dominion.deck.GameDeck;
import dominion.exception.BuyException;
import dominion.interfaces.strategies.BuyOrder;

public interface Game {
	void register(Player player);
	void register(BuyOrder buyOrder);
	List<Integer> getNumberOfCards();
	void play();
	List<Player> winner();
	Boolean gameOver();
	void buy(Card instance, Player player) throws BuyException;
	void playCard(Player player, Card card);
	GameDeck getGameDeck();
}
