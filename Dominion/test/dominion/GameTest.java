package dominion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dominion.interfaces.Game;

public class GameTest {
	/*
	 * informations required to print the board: - hand - number of actions left
	 * - number of buy left - number of gold within the turn
	 */
	Game game;
	Player player0;
	Player player1;
	PlayerDeck cards;
	GameDeck gameDeck;

	@Before
	public void init() {
		gameDeck = new GameDeck();
		player0 = new Player();
		player1 = new Player();
		game = new GameImpl(gameDeck);
		game.register(player0);
		game.register(player1);
		cards = player1.getPlayerDeck();
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

	@Test
	@Ignore
	public void firstHandContainsOneCopper() {
		List<Card> cards = player1.hand;
		assertTrue(cards.contains(Card.COPPER));
	}

	@Test
	public void firstDeckIs7Coppers3Estates() {

		assertEquals(7, Collections.frequency(cards, Card.COPPER));
		assertEquals(3, Collections.frequency(cards, Card.ESTATE));
	}

	@Test
	public void firstDeckIsShuffled() {
		assertEquals(new Integer(1), cards.getShuffled());
	}
}
