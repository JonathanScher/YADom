package dominion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.card.Estate;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.card.Smithy;
import dominion.deck.GameDeck;
import dominion.deck.PlayerDeck;
import dominion.exception.BuyException;
import dominion.exception.CardNotInDeckException;
import dominion.exception.NotAllowedToBuyException;
import dominion.exception.NotEnoughGoldException;
import dominion.exception.PileDepletedException;
import dominion.interfaces.Card;
import dominion.interfaces.Player;

public class GameTest {
	GameImpl game;
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
	public void pileDepletedAddsOne() {
		// G
		// W
		game.pileDepleted(Copper.INSTANCE);
		// T
		assertEquals(1, game.depleted.size());
	}

	@Test
	public void provinceDepletedEndsGame() {
		// G
		// W
		game.pileDepleted(Province.INSTANCE);
		// T
		assertTrue(game.gameOver);
	}

	@Test
	public void threeDepletedEndsGame() {
		// G
		// W
		game.pileDepleted(Curse.INSTANCE);
		game.pileDepleted(Copper.INSTANCE);
		game.pileDepleted(Silver.INSTANCE);
		// T
		assertTrue(game.gameOver);
	}

	@Test
	public void threeSameCardDepletedDoesNotEndsGame() {
		// G
		// W
		game.pileDepleted(Copper.INSTANCE);
		game.pileDepleted(Copper.INSTANCE);
		game.pileDepleted(Copper.INSTANCE);
		// T
		assertFalse(game.gameOver);
	}

	@Test
	public void gameLastFor80Turns() {
		// G
		Player player = mock(Player.class);
		game.players = new ArrayList<>();
		game.register(player);
		game.turn = 81;
		// W
		game.play();
		// T
		verify(player, times(1)).turn(game);
	}

	@Test
	public void playCard() {
		// G
		Card card = mock(Smithy.class);
		// W
		game.playCard(player0, card);
		// T
		verify(card).play(game, player0);
	}

	@Test(expected = PileDepletedException.class)
	public void tryToBuyACardFromDepletedPile() throws BuyException {
		gameDeck.put(Curse.INSTANCE, 0);
		game.buy(Curse.INSTANCE, player0);
	}

	@Test(expected = NotAllowedToBuyException.class)
	public void tryToBuyTwoCardsWithOneBuyPossible() throws BuyException {
		gameDeck.put(Curse.INSTANCE, 8);
		game.buy(Curse.INSTANCE, player0);
		game.buy(Curse.INSTANCE, player0);
	}

	@Test
	public void canBuyTwoCardsInTwoTurns() throws BuyException {
		// Given
		gameDeck.put(Curse.INSTANCE, 8);
		game.buy(Curse.INSTANCE, player0);
		player0.turn(game);
		// When
		game.buy(Curse.INSTANCE, player0);
		// Then
		assertEquals(2, Collections.frequency(player0.discard, Curse.INSTANCE));
	}

	@Test(expected = CardNotInDeckException.class)
	public void tryToBuyACardNotFromDeck() throws BuyException {
		gameDeck.put(Curse.INSTANCE, 0);
		game.buy(Copper.INSTANCE, player0);
	}

	@Test(expected = NotEnoughGoldException.class)
	public void tryToBuyACardNotEnoughGold() throws BuyException {
		gameDeck.put(Province.INSTANCE, 5);
		player1.hand.add(Copper.INSTANCE, Copper.INSTANCE);
		game.buy(Province.INSTANCE, player0);
	}

	@Test
	public void buyACopperAddsACopperInPlayersHand() throws BuyException {
		// G
		PlayerDeck discard = mock(PlayerDeck.class);
		gameDeck.put(Copper.INSTANCE, 10);
		player0.discard = discard;
		// W
		game.buy(Copper.INSTANCE, player0);
		// T
		verify(discard).add(Copper.INSTANCE);
	}

	@Test
	public void buyACopperRemovesACopperFromGameStack() throws BuyException {
		// G
		gameDeck.put(Copper.INSTANCE, 10);
		// W
		game.buy(Copper.INSTANCE, player0);
		// T
		assertEquals(9, (int) gameDeck.get(Copper.INSTANCE));
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
	public void ifGameOverNoPlay() {
		// Given
		player0 = mock(PlayerImpl.class);

		game = new GameImpl(gameDeck);
		game.register(player0);
		game.gameOver = true;
		// When
		game.play();
		// Then
		verify(player0, times(0)).turn(game);
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
		player0.pile.add(Estate.INSTANCE);
		List<Player> expected = new ArrayList<>();
		expected.add(player0);
		expected.add(player1);
		// When
		List<Player> winners = game.winner();
		// Then
		assertEquals(expected, winners);
	}

	@Test
	public void winnerPlayer1() {
		// Given
		player1.pile.add(Estate.INSTANCE);
		List<Player> expected = new ArrayList<>();
		expected.add(player1);
		expected.add(player0);
		// When
		List<Player> winners = game.winner();
		// Then
		assertEquals(expected, winners);
	}

	@Test
	public void setInitialGameDeck() {
		assertEquals(gameDeck, ((GameImpl) game).getGameDeck());
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
		player0sHand.add(Copper.INSTANCE);
		player0sHand.add(Copper.INSTANCE);
		player0sHand.add(Copper.INSTANCE);
		player0.hand = player0sHand;

		PlayerDeck player1sHand = new PlayerDeck();
		player1sHand.add(Copper.INSTANCE);
		player1sHand.add(Copper.INSTANCE);
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
