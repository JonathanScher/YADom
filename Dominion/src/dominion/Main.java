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
		player0.setStrategy(new BigMoney());
		Player player1 = new PlayerImpl();
		//at some point we want to define the strategy for each player here
		game.register(player0);
		game.register(player1);
		
		game.play(); //not implemented
		System.out.println("winner is: " + game.winner());
	}

}
