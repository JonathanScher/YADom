package dominion.strategies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dominion.GameImpl;
import dominion.PlayerImpl;
import dominion.deck.GameDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.BuyOrder;

public class MatchTest {

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
		assertEquals(100, match.games.size());
		assertFalse(match.games.get(0).getGameDeck() == match.games.get(1)
				.getGameDeck());
		assertEquals("player1", player1BO.name);
		assertEquals("player2", player2BO.name);
	}

	@Test
	public void initGames() {
		// G
		BuyOrder player1BO = mock(BuyOrder.class);
		BuyOrder player1BOClone = mock(BuyOrder.class);
		when(player1BO.clone()).thenReturn(player1BOClone);

		BuyOrder player2BO = mock(BuyOrder.class);
		BuyOrder player2BOClone = mock(BuyOrder.class);
		when(player2BO.clone()).thenReturn(player2BOClone);

		GameDeck gameDeck = new GameDeck();
		Game game1 = mock(Game.class);
		Game game2 = mock(Game.class);

		Match match = new Match(gameDeck, player1BO, player2BO);
		match.games.add(game1);
		match.games.add(game2);

		// W
		match.initGames();

		// T
		verify(game1).register(player1BOClone);
		verify(game1).register(player2BOClone);
		verify(game2).register(player1BOClone);
		verify(game2).register(player2BOClone);
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

		when(player1.getName()).thenReturn(Match.PLAYER1);
		when(player2.getName()).thenReturn(Match.PLAYER2);
		when(player1.victoryValue()).thenReturn(1);
		when(player2.victoryValue()).thenReturn(0);

		// W
		match.gatherResult();
		
		//T
		assertEquals(Integer.valueOf(1), match.player1wins);
		assertEquals(Integer.valueOf(0), match.player2wins);
	}
}
