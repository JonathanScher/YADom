package dominion.interfaces;

public interface Player extends Comparable<Player> {
	void initPile();

	Integer getHandSize();

	void drawHand();

	Integer victoryValue();

	void turn(Game game);
}