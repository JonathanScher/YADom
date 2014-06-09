package dominion.strategies;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dominion.PlayerImpl;
import dominion.card.Copper;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.BuyOrder;
import dominion.interfaces.strategies.CardsToBuy;
import dominion.interfaces.strategies.SimpleBehaviour;

public class MatchTest {
	@Test
	public void gamesDoNotShareTheirBuyOrder() {
		// G
		BuyOrder bo = new BuyOrder();
		bo.add(new CardsToBuy(Copper.INSTANCE, 10));

		Match match = new Match(GameDeck.basicDeck2Players(), bo, bo);
		match.numberOfGames = 2;
		match.init();
		match.initGames();

		// W
		Player player = match.getPlayer(0, 0);
		player.turn(match.games.get(0));

		// T
		Player otherPlayer = match.getPlayer(1, 0);
		assertEquals(new CardsToBuy(Copper.INSTANCE, 9),
				((SimpleBehaviour) ((PlayerImpl) player).strategy).buyOrder
						.get(0));
		assertEquals(
				new CardsToBuy(Copper.INSTANCE, 10),
				((SimpleBehaviour) ((PlayerImpl) otherPlayer).strategy).buyOrder
						.get(0));

		// list two games
		// match.initialise
		// play a turn of one game
		// make sure that the buy Order haven't been changed
	}

	@Test
	public void constructor() {
		// G
		BuyOrder player1BO = new BuyOrder();
		BuyOrder player2BO = new BuyOrder();
		GameDeck deck = new GameDeck();

		// W
		Match match = new Match(deck, player1BO, player2BO);

		// T
		assertNotNull(match.games);
		assertEquals(player1BO, match.player1BO);
		assertEquals(player2BO, match.player2BO);
		assertEquals(deck, match.gameDeck);
	}

	@SuppressWarnings("unused")
	@Test
	public void init() {
		// G
		BuyOrder player1BO = new BuyOrder();
		BuyOrder player2BO = new BuyOrder();
		GameDeck deck = new GameDeck();
		Match match = new Match(deck, player1BO, player2BO);

		// W
		match.init();

		// T
		assertEquals(Match.NUMBER_OF_GAMES, match.games.size());
		if (Match.NUMBER_OF_GAMES > 1) {
			assertFalse(match.games.get(0).getGameDeck() == match.games.get(1)
					.getGameDeck());
		}
	}

	@Test
	public void initGames() {
		// G
		BuyOrder player1BO = mock(BuyOrder.class);
		BuyOrder player2BO = mock(BuyOrder.class);

		GameDeck gameDeck = new GameDeck();
		Game game1 = mock(Game.class);
		Game game2 = mock(Game.class);

		Match match = new Match(gameDeck, player1BO, player2BO);
		match.games.add(game1);
		match.games.add(game2);

		// W
		match.initGames();

		// T
		verify(game1, times(2)).register(any(BuyOrder.class));
		verify(game2, times(2)).register(any(BuyOrder.class));
		verify(game1, times(0)).register(player2BO);
		verify(game1, times(0)).register(player1BO);
		verify(game2, times(0)).register(player1BO);
		verify(game2, times(0)).register(player2BO);
	}

	@Test
	public void playGames() {
		// G
		Game game1 = mock(Game.class);
		Game game2 = mock(Game.class);
		BuyOrder player1BO = mock(BuyOrder.class);
		BuyOrder player2BO = mock(BuyOrder.class);
		GameDeck deck = mock(GameDeck.class);
		Match match = new Match(deck, player1BO, player2BO);
		match.games.add(game1);
		match.games.add(game2);

		// W
		match.playGames();

		// T
		verify(game1).play();
		verify(game2).play();
	}

	@Test
	public void gatherResults() {
		// G
		Game game1 = mock(Game.class);
		BuyOrder player1BO = mock(BuyOrder.class);
		BuyOrder player2BO = mock(BuyOrder.class);
		GameDeck deck = mock(GameDeck.class);
		Match match = new Match(deck, player1BO, player2BO);
		match.games.add(game1);

		Player player1 = mock(Player.class);
		Player player2 = mock(Player.class);
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		when(game1.winner()).thenReturn(players);

		when(player1.victoryValue()).thenReturn(1);
		when(player2.victoryValue()).thenReturn(0);

		// W
		match.gatherResult();

		// T
		assertEquals(Integer.valueOf(1), match.player1wins);
		assertEquals(Integer.valueOf(0), match.player2wins);
	}
}
