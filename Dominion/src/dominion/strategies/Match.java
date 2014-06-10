package dominion.strategies;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dominion.GameImpl;
import dominion.PlayerImpl;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.BuyOrder;
import dominion.interfaces.strategies.SimpleBehaviour;

/**
 * Match plays 100 games with two given strategies
 */
public class Match {

	public static final Logger LOGGER = Logger.getLogger(Match.class);

	public static final int NUMBER_OF_GAMES = 100;
	public int numberOfGames = NUMBER_OF_GAMES;
	public List<Game> games;
	public Player player1;
	public Player player2;
	public GameDeck gameDeck;
	public Integer player1wins;
	public Integer player2wins;

	public Match(GameDeck deck, BuyOrder player1bo, BuyOrder player2bo) {
		games = new ArrayList<Game>();
		player1 = new PlayerImpl(new SimpleBehaviour(player1bo));
		player2 = new PlayerImpl(new SimpleBehaviour(player2bo));

		this.gameDeck = deck;
		player1wins = 0;
		player2wins = 0;
	}

	public void init() {
		for (int i = 0; i < numberOfGames; i++) {
			Game game = new GameImpl(new GameDeck(gameDeck));
			games.add(game);
		}
	}

	public void initGames() {
		games.parallelStream().forEach(x -> {
			x.register(new PlayerImpl(player1));
			x.register(new PlayerImpl(player2));
		});
	}

	public void playGames() {
		games.parallelStream().forEach(x -> {
			x.play();
		});
	}

	public void gatherResult() {
		games.parallelStream().forEach(x -> {
			List<Player> players = x.winner();
			Integer player1Victory = players.get(0).victoryValue();
			Integer player2Victory = players.get(1).victoryValue();
			if (player1Victory > player2Victory) {
				player1wins++;
			} else if (player2Victory > player1Victory) {
				player2wins++;
			}
		});
	}

	public Player getPlayer(int gameNumber, int playerNumber) {
		return games.get(gameNumber).getPlayer(playerNumber);
	}

	public Player winner() {
		Player returned = null;
		if (player1wins < player2wins) {
			returned = player2;
		}
		if (player1wins > player2wins) {
			returned = player1;
		}
		return returned;
	}
	
	public static void runMatch(Match match) {
		LOGGER.debug("run Start");
		match.init();
		LOGGER.debug("init done");
		match.initGames();
		LOGGER.debug("initGame done");
		match.playGames();
		LOGGER.debug("playGame done");
		match.gatherResult();
		LOGGER.debug("gatherResult done");
	}

}
