package dominion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Silver;
import dominion.deck.PlayerDeck;
import dominion.interfaces.Game;
import dominion.interfaces.strategies.Strategy;
import dominion.mock.PlayerDeckNoShuffle;
import dominion.strategies.DoNothing;

public class PlayerTest {
	PlayerImpl player;
	PlayerDeck pile;

	@Before
	public void init() {
		player = new PlayerImpl();
		pile = new PlayerDeck();
		pile.add(Copper.INSTANCE, Estate.INSTANCE, Copper.INSTANCE,
				Estate.INSTANCE, Copper.INSTANCE, Estate.INSTANCE,
				Copper.INSTANCE, Estate.INSTANCE, Copper.INSTANCE,
				Estate.INSTANCE);
		player.pile = pile;
	}

	@Test
	public void draw() {
		PlayerDeck expected = new PlayerDeck();
		expected.add(Copper.INSTANCE, Estate.INSTANCE);
		player.draw(2);
		assertEquals(expected, player.hand);
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
		hand.add(Copper.INSTANCE, Silver.INSTANCE, Gold.INSTANCE);
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
		pile.add(Copper.INSTANCE, Copper.INSTANCE, Copper.INSTANCE,
				Copper.INSTANCE, Copper.INSTANCE, Copper.INSTANCE);
		player.pile = pile;

		PlayerDeck hand = new PlayerDeck();
		hand.add(Curse.INSTANCE, Curse.INSTANCE, Curse.INSTANCE,
				Curse.INSTANCE, Curse.INSTANCE);
		player.hand = hand;

		PlayerDeck discard = new PlayerDeck();
		discard.add(Duchy.INSTANCE);
		player.discard = discard;
		// W
		player.turn(game);
		// T
		PlayerDeck expectedPile = new PlayerDeck();
		expectedPile.add(Copper.INSTANCE);
		PlayerDeck expectedHand = new PlayerDeck();
		expectedHand.add(Copper.INSTANCE, Copper.INSTANCE, Copper.INSTANCE,
				Copper.INSTANCE, Copper.INSTANCE);
		PlayerDeck expectedDiscard = new PlayerDeck();
		expectedDiscard.add(Duchy.INSTANCE, Curse.INSTANCE, Curse.INSTANCE,
				Curse.INSTANCE, Curse.INSTANCE, Curse.INSTANCE);

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
		player.pile.add(Estate.INSTANCE);
		player.hand.add(Estate.INSTANCE);
		player.discard.add(Estate.INSTANCE);

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
		verify(player.pile, times(3)).add(Estate.INSTANCE);
		verify(player.pile, times(7)).add(Copper.INSTANCE);
		verify(player.pile).shuffle();

	}

	@Test
	public void drawHandTakes5FirstCards() {
		// Given
		PlayerDeck expected = new PlayerDeck();
		expected.add(Copper.INSTANCE, Estate.INSTANCE, Copper.INSTANCE,
				Estate.INSTANCE, Copper.INSTANCE);

		// When
		player.drawHand();
		PlayerDeck actual = player.hand;
		// Then
		assertEquals(expected, actual);
	}

	@Test
	public void drawHandRemovesCardsFromDeck() {
		PlayerDeck expected = new PlayerDeck();
		expected.add(Estate.INSTANCE, Copper.INSTANCE, Estate.INSTANCE,
				Copper.INSTANCE, Estate.INSTANCE);

		// When
		player.drawHand();

		// Then
		assertEquals(expected, player.pile);
	}

	@Test
	public void drawHandTakes3FirstCardsIfThereIsOnly3CardsInTheDeckAndDiscardedIsEmpty() {

		PlayerDeck smallDraw = new PlayerDeck();
		smallDraw.add(Copper.INSTANCE);
		player.pile = smallDraw;

		PlayerDeck expected = new PlayerDeck();
		expected.add(Copper.INSTANCE);

		// When
		player.drawHand();

		// Then
		assertEquals(expected, player.hand);
	}

	@Test
	public void drawWithNotEnoughInDrawingPile() {
		// Given
		PlayerDeck pile = new PlayerDeck();
		pile.add(Copper.INSTANCE, Copper.INSTANCE, Copper.INSTANCE);
		PlayerDeck discard = new PlayerDeckNoShuffle();
		discard.add(Estate.INSTANCE, Copper.INSTANCE, Estate.INSTANCE,
				Estate.INSTANCE, Estate.INSTANCE, Estate.INSTANCE);
		PlayerDeck hand = new PlayerDeck();
		player.pile = pile;
		player.discard = discard;
		player.hand = hand;

		PlayerDeck expectedDraw = new PlayerDeck();
		expectedDraw.add(Estate.INSTANCE, Estate.INSTANCE, Estate.INSTANCE,
				Estate.INSTANCE);
		PlayerDeck expectedDiscard = new PlayerDeck();
		PlayerDeck expectedHand = new PlayerDeck();
		expectedHand.add(Copper.INSTANCE, Copper.INSTANCE, Copper.INSTANCE,
				Estate.INSTANCE, Copper.INSTANCE);
		// When
		player.drawHand();
		// Then
		assertEquals(expectedDraw, player.pile);
		assertEquals(expectedDiscard, player.discard);
		assertEquals(expectedHand, player.hand);
		assertEquals(new Integer(1), player.pile.shuffled);
	}
}
