package dominion.interfaces;

import dominion.cards.Card;
import dominion.deck.PlayerDeck;

public interface Player extends Comparable<Player> {
	void initPile();

	Integer getHandSize();

	void drawHand();

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