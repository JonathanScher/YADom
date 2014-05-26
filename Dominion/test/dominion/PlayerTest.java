package dominion;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import dominion.interfaces.Game;

public class PlayerTest {
	Player player;
	PlayerDeck draw;

	@Before
	public void init() {
		player = new Player();
		draw = new PlayerDeck();
		draw.add(Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE);
		player.draw = draw;
		GameDeck gameDeck = new GameDeck();
		Game game = new GameImpl(gameDeck);
		game.register(player);
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
		
		//When
		player.drawHand();
		
		//Then
		assertEquals(expected, player.hand);
	}

}
