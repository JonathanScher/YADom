package dominion;

import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.strategies.BigMoney;

public class Main {

	public static void main(String[] args) {
		GameDeck gameDeck = new GameDeck();
		gameDeck.put(Card.COPPER, 100);
		gameDeck.put(Card.SILVER, 100);
		gameDeck.put(Card.GOLD, 100);
		gameDeck.put(Card.CURSE, 10);
		gameDeck.put(Card.DUCHY, 8);
		gameDeck.put(Card.ESTATE, 8);
		gameDeck.put(Card.PROVINCE, 8);

		Game game = new GameImpl(gameDeck);

		Player player0 = new PlayerImpl();
		player0.setName("Player 0");
		Player player1 = new PlayerImpl();
		player1.setName("Player 1");
		player1.setStrategy(new BigMoney());

		game.register(player0);
		game.register(player1);

		game.play(); // not implemented
		System.out.println("winner is: " + game.winner());
	}

}
