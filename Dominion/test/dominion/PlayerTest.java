package dominion;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import dominion.interfaces.Game;
import dominion.mock.PlayerDeckNoShuffle;

public class PlayerTest {
	PlayerImpl player;
	PlayerDeck draw;

	@Before
	public void init() {
		player = new PlayerImpl();
		GameDeck gameDeck = new GameDeck();
		Game game = new GameImpl(gameDeck);
		game.register(player);

		draw = new PlayerDeck();
		draw.add(Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE);
		player.draw = draw;
	}

	@Test
	public void initDrawCreatesTheFirstDeck() {
		// Given
		player.draw = new PlayerDeckNoShuffle();
		//When
		player.initDraw();
		//Then
		assertEquals(10, player.draw.size());
		assertEquals(3, Collections.frequency(player.draw, Card.ESTATE));
		assertEquals(7, Collections.frequency(player.draw, Card.COPPER));
		assertEquals(new Integer(1), player.draw.shuffled);
	}

	@Test
	public void drawHandTakes5FirstCards() {
		// Given
		PlayerDeck expected = new PlayerDeck();
		expected.add(Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER);

		// When
		player.drawHand();
		PlayerDeck actual = player.hand;
		// Then
		assertEquals(expected, actual);
	}

	@Test
	public void drawHandRemovesCardsFromDeck() {
		PlayerDeck expected = new PlayerDeck();
		expected.add(Card.ESTATE, Card.COPPER, Card.ESTATE, Card.COPPER,
				Card.ESTATE);

		// When
		player.drawHand();

		// Then
		assertEquals(expected, player.draw);
	}

	@Test
	public void drawHandTakes3FirstCardsIfThereIsOnly3CardsInTheDeckAndDiscardedIsEmpty() {
		PlayerDeck smallDraw = new PlayerDeck();
		smallDraw.add(Card.COPPER);
		player.draw = smallDraw;

		PlayerDeck expected = new PlayerDeck();
		expected.add(Card.COPPER);

		// When
		player.drawHand();

		// Then
		assertEquals(expected, player.hand);
	}

	@Test
	public void drawWithNotEnoughInDrawingPile() {
		// Given
		PlayerDeck draw = new PlayerDeck();
		draw.add(Card.COPPER, Card.COPPER, Card.COPPER);
		PlayerDeck discard = new PlayerDeckNoShuffle();
		discard.add(Card.ESTATE, Card.COPPER, Card.ESTATE, Card.ESTATE,
				Card.ESTATE, Card.ESTATE);
		PlayerDeck hand = new PlayerDeck();
		player.draw = draw;
		player.discard = discard;
		player.hand = hand;

		PlayerDeck expectedDraw = new PlayerDeck();
		expectedDraw.add(Card.ESTATE, Card.ESTATE, Card.ESTATE, Card.ESTATE);
		PlayerDeck expectedDiscard = new PlayerDeck();
		PlayerDeck expectedHand = new PlayerDeck();
		expectedHand.add(Card.COPPER, Card.COPPER, Card.COPPER, Card.ESTATE,
				Card.COPPER);
		// When
		player.drawHand();
		// Then
		assertEquals(expectedDraw, player.draw);
		assertEquals(expectedDiscard, player.discard);
		assertEquals(expectedHand, player.hand);
		assertEquals(new Integer(1), player.draw.shuffled);
	}
}