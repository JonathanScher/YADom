package dominion.strategies;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dominion.GameImpl;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.BuyOrder;

/**
 * Match plays 100 games with two given strategies
 */
public class Match {

	public static final Logger LOGGER = Logger.getLogger(Match.class);
	
	public static final int NUMBER_OF_GAMES = 1;
	public int numberOfGames = NUMBER_OF_GAMES;
	public List<Game> games;
	public BuyOrder player1BO;
	public BuyOrder player2BO;
	public GameDeck gameDeck;
	public Integer player1wins;
	public Integer player2wins;

	public Match(GameDeck deck, BuyOrder player1bo, BuyOrder player2bo) {
		games = new ArrayList<Game>();
		this.player1BO = player1bo;
		this.player2BO = player2bo;
		this.gameDeck = deck;
		player1wins = 0;
		player2wins = 0;
	}

	public void run(){
		LOGGER.debug("run Start");
		init();
		LOGGER.debug("init done");
		initGames();
		LOGGER.debug("initGame done");
		playGames();
		LOGGER.debug("playGame done");
		gatherResult();
		LOGGER.debug("gatherResult done");
		
	}
	
	public void init() {
		for (int i = 0; i < numberOfGames; i++) {
			Game game = new GameImpl((GameDeck) gameDeck.clone());
			games.add(game);
		}
	}

	public void initGames() {
		games.parallelStream().forEach(x -> {
			x.register((BuyOrder) player1BO.clone());
			x.register((BuyOrder) player2BO.clone());
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

}
