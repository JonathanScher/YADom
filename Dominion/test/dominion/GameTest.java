package dominion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.mock.GameDeckMock;
import dominion.mock.PlayerDummy;

public class GameTest {
	/*
	 * informations required to print the board: - number of actions left -
	 * number of buy left - number of gold within the turn
	 */
	Game game;
	PlayerImpl player0;
	PlayerImpl player1;
	PlayerDeck cards;
	GameDeck gameDeck;

	@Before
	public void init() {
		gameDeck = new GameDeck();
		player0 = new PlayerImpl();
		player1 = new PlayerImpl();
		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player1);
		cards = player1.getPlayerDeck();
	}

	@Test
	public void gameOver() {
		gameDeck = new GameDeckMock(true);
		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player0);
		game.register(player0);
		assertTrue(game.gameOver());
		assertEquals(new Integer(3), ((GameDeckMock)gameDeck).numberOfPlayers);
	}

	@Test
	public void gameNotOver() {
		gameDeck = new GameDeckMock(false);
		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player0);
		assertFalse(game.gameOver());
		assertEquals(new Integer(2), ((GameDeckMock)gameDeck).numberOfPlayers);
	}
	
	@Test
	public void drawsHandWhenRegisters() {
		// Given
		PlayerDummy player = new PlayerDummy();
		// When
		game.register(player);
		// Then
		assertEquals(new Integer(1), player.handDrawn);
	}

	@Test
	public void winnerPlayer0() {
		// Given
		player0.pile.add(Card.ESTATE);
		// When
		Player winner = game.winner();
		// Then
		assertEquals(player0, winner);
	}

	@Test
	public void winnerPlayer1() {
		// Given
		player1.pile.add(Card.ESTATE);
		// When
		Player winner = game.winner();
		// Then
		assertEquals(player1, winner);
	}

	@Test
	public void setInitialGameDeck() {
		assertEquals(gameDeck, game.getGameDeck());
	}

	@Test
	public void setInitialPlayers() {
		assertEquals(player0, ((GameImpl) game).players.get(0));
		assertEquals(player1, ((GameImpl) game).players.get(1));
	}

	@Test
	public void getNumberOfCardsInHand() {
		// GIVEN
		List<Card> player0sHand = new ArrayList<>();
		player0sHand.add(Card.COPPER);
		player0sHand.add(Card.COPPER);
		player0sHand.add(Card.COPPER);
		player0.hand.addAll(player0sHand);

		List<Card> player1sHand = new ArrayList<>();
		player1sHand.add(Card.COPPER);
		player1sHand.add(Card.COPPER);
		player1.hand.addAll(player1sHand);

		List<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(2);

		// WHEN
		List<Integer> numberOfCards = game.getNumberOfCards();

		// THEN
		assertEquals(expected, numberOfCards);
	}
}
