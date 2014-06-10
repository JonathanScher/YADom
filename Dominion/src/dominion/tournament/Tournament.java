package dominion.tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominion.GameImpl;
import dominion.PlayerImpl;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.BuyOrder;
import dominion.interfaces.strategies.SimpleBehaviour;
import dominion.strategies.Match;

public class Tournament {

	public Map<BuyOrder, Integer> wins = new HashMap<>();
	private GameDeck gameDeck;
	public List<Game> games = new ArrayList<>();

	public Tournament(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
	}

	public static <T> List<Couple<T>> combinaisons(List<T> toCombine) {
		List<Couple<T>> result = new ArrayList<Couple<T>>();
		for (int i = 0; i < toCombine.size(); i++) {
			for (int j = i + 1; j < toCombine.size(); j++) {
				result.add(new Couple<T>(toCombine.get(i), toCombine.get(j)));
			}
		}
		return result;
	}

	public Map<Player, Integer> play(BuyOrder... strategies) {
		List<Couple<BuyOrder>> versus = Tournament.combinaisons(Arrays
				.asList(strategies));
		List<Match> matches = new ArrayList<>();
		versus.parallelStream().forEach(x -> {
			Match match = new Match(gameDeck, x.y, x.x);
			Match.runMatch(match);
			matches.add(match);
			Match.runMatch(match);
		});

		Map<Player, Integer> results = new HashMap<>();
		matches.forEach(x -> {
			Player winner = x.winner();
			if (winner != null) {
				if (results.get(winner) == null) {
					results.put(winner, 0);
				}
				results.put(winner, results.get(winner) + 1);
			}
		});
		return results;
		// combine all strategies
		// plays 100 game for each combinaison
		// order each buyOrder per number of win
	}

	public void play(BuyOrder strat1, BuyOrder strat2) {
		// play 100 times. We could parallelise that
		// list of games, operator transforming it into list of players
		// then we can loop on the transformed list to count the winners
		for (int i = 0; i < 100; i++) {
			// Initialise the game
			Game game = new GameImpl((GameDeck) gameDeck.clone());

			Player player1 = new PlayerImpl();
			SimpleBehaviour sb1 = new SimpleBehaviour(strat1);
			player1.setStrategy(sb1);
			game.register(player1);

			Player player2 = new PlayerImpl();
			SimpleBehaviour sb2 = new SimpleBehaviour(strat2);
			player2.setStrategy(sb2);
			game.register(player2);

			// play the game
			game.play();

			// collect the winners, and add them to the results
			List<Player> winners = game.winner();
			Player winner = winners.get(0);
			Player loser = winners.get(1);
			BuyOrder stratWin = strat2;
			BuyOrder stratLose = strat1;
			if (winner == player1) {
				stratWin = strat1;
				stratLose = strat2;
			}

			if (winner.equals(loser)) {
				if (wins.get(stratLose) == null) {
					wins.put(stratLose, 0);
				}
				wins.put(stratLose, wins.get(stratLose) + 1);
			}
			if (wins.get(stratWin) == null) {
				wins.put(stratWin, 0);
			}
			wins.put(stratWin, wins.get(stratWin) + 1);
		}
	}
}
