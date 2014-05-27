package dominion;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import dominion.interfaces.Game;
import dominion.mock.PlayerDeckNoShuffle;

public class PlayerTest {
	PlayerImpl player;
	PlayerDeck pile;

	@Before
	public void init() {
		player = new PlayerImpl();
		GameDeck gameDeck = new GameDeck();
		Game game = new GameImpl(gameDeck);
		game.register(player);

		pile = new PlayerDeck();
		pile.add(Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE);
		player.pile = pile;
	}

	@Test
	public void value(){
		//Given
		player.pile = new PlayerDeck();
		player.hand = new PlayerDeck();
		player.discard = new PlayerDeck();
		player.pile.add(Card.ESTATE);
		player.hand.add(Card.ESTATE);
		player.discard.add(Card.ESTATE);
		
		Integer expected = 3;
		//When
		Integer actual = player.victoryValue();
		//Then
		assertEquals(expected, actual);
	}
	
	@Test
	public void initDrawCreatesTheFirstDeck() {
		// Given
		player.pile = new PlayerDeckNoShuffle();
		//When
		player.initPile();
		//Then
		assertEquals(10, player.pile.size());
		assertEquals(3, Collections.frequency(player.pile, Card.ESTATE));
		assertEquals(7, Collections.frequency(player.pile, Card.COPPER));
		assertEquals(new Integer(1), player.pile.shuffled);
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
		assertEquals(expected, player.pile);
	}

	@Test
	public void drawHandTakes3FirstCardsIfThereIsOnly3CardsInTheDeckAndDiscardedIsEmpty() {
		PlayerDeck smallDraw = new PlayerDeck();
		smallDraw.add(Card.COPPER);
		player.pile = smallDraw;

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
		player.pile = draw;
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
		assertEquals(expectedDraw, player.pile);
		assertEquals(expectedDiscard, player.discard);
		assertEquals(expectedHand, player.hand);
		assertEquals(new Integer(1), player.pile.shuffled);
	}
}
