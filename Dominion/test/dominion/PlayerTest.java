package dominion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import dominion.cards.Card;
import dominion.deck.PlayerDeck;
import dominion.interfaces.Game;
import dominion.interfaces.Strategy;
import dominion.mock.PlayerDeckNoShuffle;
import dominion.strategies.DoNothing;

public class PlayerTest {
	PlayerImpl player;
	PlayerDeck pile;

	@Before
	public void init() {
		player = new PlayerImpl();
		pile = new PlayerDeck();
		pile.add(Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE, Card.COPPER, Card.ESTATE,
				Card.COPPER, Card.ESTATE);
		player.pile = pile;
	}

	@Test
	public void toStringPrintsName() {
		player.setName("test");
		assertEquals("test", player.toString());
	}

	@Test
	public void getGoldReturnsGoldInHand() {
		// G
		PlayerDeck hand = new PlayerDeck();
		hand.add(Card.COPPER, Card.SILVER, Card.GOLD);
		player.hand = hand;
		// W
		int actual = player.getGold();
		// T
		assertEquals(6, actual);
	}

	@Test
	public void turnEndsByDiscardingAndDrawing() {
		// G
		Game game = mock(Game.class);
		PlayerDeck pile = new PlayerDeck();
		pile.add(Card.COPPER, Card.COPPER, Card.COPPER, Card.COPPER, Card.COPPER,
				Card.COPPER);
		player.pile = pile;
		
		PlayerDeck hand = new PlayerDeck();
		hand.add(Card.CURSE, Card.CURSE, Card.CURSE, Card.CURSE, Card.CURSE);
		player.hand = hand;
		
		PlayerDeck discard = new PlayerDeck();
		discard.add(Card.DUCHY);
		player.discard = discard;
		// W
		player.turn(game);
		// T
		PlayerDeck expectedPile = new PlayerDeck();
		expectedPile.add(Card.COPPER);
		PlayerDeck expectedHand = new PlayerDeck();
		expectedHand.add(Card.COPPER, Card.COPPER, Card.COPPER, Card.COPPER, Card.COPPER);
		PlayerDeck expectedDiscard = new PlayerDeck();
		expectedDiscard.add(Card.DUCHY, Card.CURSE, Card.CURSE, Card.CURSE, Card.CURSE, Card.CURSE);
		
		assertEquals(expectedDiscard, player.discard);
		assertEquals(expectedHand, player.hand);
		assertEquals(expectedPile, player.pile);

	}

	@Test
	public void eachTurnPlayerCallsItsStrategy() {
		// G
		Game game = mock(Game.class);
		Strategy mockStrat = mock(Strategy.class);
		player.strategy = mockStrat;
		// W
		player.turn(game);
		// T
		verify(mockStrat).turn(player, game);
	}

	@Test
	public void doNothingIsTheDefaultStrategy() {
		assertEquals(DoNothing.class, player.strategy.getClass());
	}

	@Test
	public void value() {
		// Given
		player.pile = new PlayerDeck();
		player.hand = new PlayerDeck();
		player.discard = new PlayerDeck();
		player.pile.add(Card.ESTATE);
		player.hand.add(Card.ESTATE);
		player.discard.add(Card.ESTATE);

		Integer expected = 3;
		// When
		Integer actual = player.victoryValue();
		// Then
		assertEquals(expected, actual);
	}

	@Test
	public void initPileCreatesTheFirstDeck() {
		// Given
		player.pile = mock(PlayerDeck.class);
		// When
		player.initPile();
		// Then
		verify(player.pile, times(3)).add(Card.ESTATE);
		verify(player.pile, times(7)).add(Card.COPPER);
		verify(player.pile).shuffle();

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
		PlayerDeck pile = new PlayerDeck();
		pile.add(Card.COPPER, Card.COPPER, Card.COPPER);
		PlayerDeck discard = new PlayerDeckNoShuffle();
		discard.add(Card.ESTATE, Card.COPPER, Card.ESTATE, Card.ESTATE,
				Card.ESTATE, Card.ESTATE);
		PlayerDeck hand = new PlayerDeck();
		player.pile = pile;
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
