package dominion.interfaces;

import dominion.deck.PlayerDeck;
import dominion.interfaces.strategies.Strategy;

public interface Player extends Comparable<Player> {
	void initPile();

	Integer getHandSize();

	void drawHand();
	
	void draw(Integer numberOfCards);

	Integer victoryValue();

	void turn(Game game);

	void setStrategy(Strategy strategy);

	Integer getGold();

	void giveCard(Card card);

	void setName(String string);

	void buy(Card card);

	int getBuyLeft();

	PlayerDeck getHand();
}