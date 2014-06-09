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
	Strategy getStrategy();

	Integer getGold();

	void giveCard(Card card);

	void buy(Card card);

	int getBuyLeft();

	PlayerDeck getHand();

	PlayerDeck getDiscard();

	PlayerDeck getPile();
}