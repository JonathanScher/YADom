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
import dominion.strategies.SmithyBigMoney;

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

		Integer player0wins = 0;
		Integer numberOfGames = 1000000;
		for (int i = 0; i < numberOfGames; i++) {
			Game game = new GameImpl(gameDeck);

			Player player0 = new PlayerImpl();
			player0.setName("Player 0");
			player0.setStrategy(new SmithyBigMoney());
			Player player1 = new PlayerImpl();
			player1.setName("Player 1");
			player1.setStrategy(new BigMoney());

			game.register(player0);
			game.register(player1);

			game.play();
			Player winner = game.winner();
			if (winner.equals(player0)) {
				player0wins++;
			}
		}
		System.out.println("Player 0 wins"
				+ player0wins);
		System.out.println("Player 0 wins, in %: "
				+ (Double.valueOf(player0wins) / Double.valueOf(numberOfGames)) * 100d);
	}

}
