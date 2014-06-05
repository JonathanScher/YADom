package dominion.interfaces;

import java.util.List;

import dominion.exception.BuyException;

public interface Game {
	void register(Player player);
	List<Integer> getNumberOfCards();
	void play();
	List<Player> winner();
	Boolean gameOver();
	void buy(Card instance, Player player) throws BuyException;
	void playCard(Player player, Card card);
}
