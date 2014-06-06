package dominion;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.card.Smithy;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.strategies.BigMoney;
import dominion.strategies.SmithyBigMoney;

public class Main {
	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Integer player0wins;
		List<Game> games = init();

		long startTime = System.currentTimeMillis();
		player0wins = games.parallelStream().mapToInt(Main::player0WinsGame)
				.sum();

		long middleTime = System.currentTimeMillis();
		LOGGER.info("Parallel execution time: "
				+ (middleTime - startTime) + "ms");
		LOGGER.info("Player 0 wins: " + player0wins);
	}

	private static List<Game> init() {

		Integer numberOfGames = 1000000;

		List<Game> games = new ArrayList<Game>();

		for (int i = 0; i < numberOfGames; i++) {
			GameDeck gameDeck = new GameDeck();
			gameDeck.put(Copper.INSTANCE, 100);
			gameDeck.put(Silver.INSTANCE, 100);
			gameDeck.put(Gold.INSTANCE, 100);
			gameDeck.put(Curse.INSTANCE, 10);
			gameDeck.put(Duchy.INSTANCE, 8);
			gameDeck.put(Estate.INSTANCE, 8);
			gameDeck.put(Province.INSTANCE, 8);
			gameDeck.put(Smithy.INSTANCE, 8);

			Game game = new GameImpl(gameDeck);
			games.add(game);
			Player player0 = new PlayerImpl();
			player0.setName("Player 0");
			player0.setStrategy(new SmithyBigMoney());
			Player player1 = new PlayerImpl();
			player1.setName("Player 1");
			player1.setStrategy(new BigMoney());

			game.register(player0);
			game.register(player1);
		}
		return games;
	}

	public static Integer player0WinsGame(Game game) {
		game.play();
		List<Player> winners = game.winner();
		if (winners.get(0).victoryValue() == winners.get(1).victoryValue()) {
			return 0;
		} else {
			if ("Player 0".equals(winners.get(0).toString())) {
				return 1;
			}
		}
		return 0;
	}
}
