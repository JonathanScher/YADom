package dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import dominion.interfaces.strategies.BuyOrder;
import dominion.strategies.BigMoney;
import dominion.strategies.Match;
import dominion.strategies.SmithyBigMoney;
import dominion.tournament.GeneticIA;
import dominion.tournament.Tournament;

@SuppressWarnings("unused")
public class Main {
	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// tournament();
		// match();
		// geneticIA();
		infiniteGame();
		long endTime = System.currentTimeMillis();
		LOGGER.info("Execution time: " + (endTime - startTime) + "ms");
	}

	private static void infiniteGame() {
		Game game = new GameImpl(GameDeck.basicDeck2Players());
		Player player1 = new PlayerImpl();
		Player player2 = new PlayerImpl();
		game.register(player1);
		game.register(player2);
		game.play();
	}

	private static void geneticIA() {
		GeneticIA gai = new GeneticIA();
		gai.run();
		LOGGER.info("the winner is " + gai.buyOrder0);
		LOGGER.info("the winner is " + gai.buyOrder1);
	}

	private static void tournament() {
		BuyOrder doNothing = new BuyOrder();
		BigMoney bm = new BigMoney();
		SmithyBigMoney sbm = new SmithyBigMoney();
		GameDeck gameDeck = GameDeck.basicDeck2Players();
		gameDeck.put(Smithy.INSTANCE, 8);

		doNothing.name = "Do Nothing";
		bm.buyOrder.name = "Big Money";
		sbm.buyOrder.name = "Smithy/Big Money";

		Tournament tournament = new Tournament(gameDeck);
		Map<Player, Integer> results = tournament.play(doNothing, bm.buyOrder,
				sbm.buyOrder);
		LOGGER.info("wins: " + results);

	}

	private static void match() {
		BigMoney bm = new BigMoney();
		SmithyBigMoney sbm = new SmithyBigMoney();
		GameDeck gameDeck = GameDeck.basicDeck2Players();
		gameDeck.put(Smithy.INSTANCE, 8);

		Match match = new Match(gameDeck, bm.buyOrder, sbm.buyOrder);
		Match.runMatch(match);

		LOGGER.info("player 1 wins: " + match.player1wins);
		LOGGER.info("player 2 wins: " + match.player2wins);
		LOGGER.info("winner is: " + match.winner());
	}

	private static void simpleGame() {
		Integer player0wins;
		List<Game> games = init();

		long startTime = System.currentTimeMillis();
		player0wins = games.parallelStream().mapToInt(Main::player0WinsGame)
				.sum();

		long middleTime = System.currentTimeMillis();
		LOGGER.info("Parallel execution time: " + (middleTime - startTime)
				+ "ms");
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
			player0.setStrategy(new SmithyBigMoney());
			Player player1 = new PlayerImpl();
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
