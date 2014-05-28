package dominion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominion.cards.Card;
import dominion.deck.GameDeck;
import dominion.deck.PlayerDeck;
import dominion.exception.BuyException;
import dominion.exception.CardNotInDeckException;
import dominion.exception.NotAllowedToBuyException;
import dominion.exception.NotEnoughGoldException;
import dominion.exception.PileDepletedException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

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

	@Test(expected = PileDepletedException.class)
	public void tryToBuyACardFromDepletedPile() throws BuyException {
		gameDeck.put(Card.CURSE, 0);
		game.buy(Card.CURSE, player0);
	}

	@Test(expected = NotAllowedToBuyException.class)
	public void tryToBuyTwoCardsWithOneBuyPossible() throws BuyException {
		gameDeck.put(Card.CURSE, 8);
		game.buy(Card.CURSE, player0);
		game.buy(Card.CURSE, player0);
	}

	@Test
	public void canBuyTwoCardsInTwoTurns() throws BuyException {
		//Given
		gameDeck.put(Card.CURSE, 8);
		game.buy(Card.CURSE, player0);
		player0.turn(game);
		//When
		game.buy(Card.CURSE, player0);
		//Then
		assertEquals(2, Collections.frequency(player0.discard, Card.CURSE));
	}

	
	@Test(expected = CardNotInDeckException.class)
	public void tryToBuyACardNotFromDeck() throws BuyException {
		gameDeck.put(Card.CURSE, 0);
		game.buy(Card.DUCHY, player0);
	}
	@Test(expected = NotEnoughGoldException.class)
	public void tryToBuyACardNotEnoughGold() throws BuyException {
		gameDeck.put(Card.PROVINCE, 5);
		player1.hand.add(Card.COPPER, Card.COPPER);
		game.buy(Card.PROVINCE, player0);
	}

	@Test
	public void buyACopperAddsACopperInPlayersHand() throws BuyException {
		// G
		PlayerDeck discard = mock(PlayerDeck.class);
		gameDeck.put(Card.COPPER, 10);
		player0.discard = discard;
		// W
		game.buy(Card.COPPER, player0);
		// T
		verify(discard).add(Card.COPPER);
	}

	@Test
	public void buyACopperRemovesACopperFromGameStack() throws BuyException {
		// G
		gameDeck.put(Card.COPPER, 10);
		// W
		game.buy(Card.COPPER, player0);
		// T
		assertEquals(9, (int) gameDeck.get(Card.COPPER));
	}

	@Test
	public void registerInitialisePlayers() {
		// Given
		Player player = mock(PlayerImpl.class);

		// When
		game.register(player);

		// Then
		verify(player).initPile();
	}

	@Test
	public void threeTurnsThenGameOver() {
		// Given
		gameDeck = Mockito.mock(GameDeck.class);
		when(gameDeck.gameOver(2)).thenReturn(false).thenReturn(false)
				.thenReturn(false).thenReturn(true);

		player0 = mock(PlayerImpl.class);
		player1 = mock(PlayerImpl.class);

		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player1);
		// When
		game.play();
		// Then
		verify(player0, times(2)).turn(game);
		verify(player1, times(1)).turn(game);
	}

	@Test
	public void threeTurnsThenGameOverThreePlayers() {
		// Given
		gameDeck = Mockito.mock(GameDeck.class);
		when(gameDeck.gameOver(3)).thenReturn(false).thenReturn(false)
				.thenReturn(false).thenReturn(false).thenReturn(true);

		player0 = mock(PlayerImpl.class);
		player1 = mock(PlayerImpl.class);
		Player player2 = mock(PlayerImpl.class);

		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player1);
		game.register(player2);
		// When
		game.play();
		// Then
		verify(player0, times(2)).turn(game);
		verify(player1, times(1)).turn(game);
		verify(player2, times(1)).turn(game);
	}

	@Test
	public void gameOver() {
		gameDeck = mock(GameDeck.class);
		when(gameDeck.gameOver(3)).thenReturn(true);
		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player0);
		game.register(player0);
		assertTrue(game.gameOver());
	}

	@Test
	public void gameNotOver() {
		gameDeck = mock(GameDeck.class);
		when(gameDeck.gameOver(2)).thenReturn(false);
		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player0);
		assertFalse(game.gameOver());
	}

	@Test
	public void drawsHandWhenRegisters() {
		// Given
		Player player = mock(PlayerImpl.class);
		// When
		game.register(player);
		// Then
		verify(player).drawHand();
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
		PlayerDeck player0sHand = new PlayerDeck();
		player0sHand.add(Card.COPPER);
		player0sHand.add(Card.COPPER);
		player0sHand.add(Card.COPPER);
		player0.hand = player0sHand;

		PlayerDeck player1sHand = new PlayerDeck();
		player1sHand.add(Card.COPPER);
		player1sHand.add(Card.COPPER);
		player1.hand = player1sHand;

		List<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(2);

		// WHEN
		List<Integer> numberOfCards = game.getNumberOfCards();

		// THEN
		assertEquals(expected, numberOfCards);
	}
}
