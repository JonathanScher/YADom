package dominion;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.strategies.BigMoney;

public class Main {

	public static void main(String[] args) {
		GameDeck gameDeck = new GameDeck();
		gameDeck.put(Copper.INSTANCE, 100);
		gameDeck.put(Silver.INSTANCE, 100);
		gameDeck.put(Gold.INSTANCE, 100);
		gameDeck.put(Curse.INSTANCE, 10);
		gameDeck.put(Duchy.INSTANCE, 8);
		gameDeck.put(Estate.INSTANCE, 8);
		gameDeck.put(Province.INSTANCE, 8);

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
