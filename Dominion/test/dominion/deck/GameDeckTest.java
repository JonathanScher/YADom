package dominion.deck;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dominion.card.Copper;
import dominion.card.Curse;
import dominion.exception.BuyException;
import dominion.exception.PileDepletedException;

@RunWith(MockitoJUnitRunner.class)
public class GameDeckTest {

	GameDeck gameDeck;
	@Mock
	GameDeckListener observer; 
	@Before
	public void init() {
		gameDeck = new GameDeck();
	}

	@Test
	public void addingAnObserver() {
		observer = mock(GameDeckListener.class);
		gameDeck.addObserver(observer);
		assertEquals(observer, gameDeck.observers.get(0));
	}

	@Test
	public void notifyAllObserver() {
		//G
		observer = mock(GameDeckListener.class);
		gameDeck.addObserver(observer);
		gameDeck.addObserver(observer);
		//W
		gameDeck.notifyAllDepleted(Copper.INSTANCE);
		//T
		verify(observer, times(2)).pileDepleted(Copper.INSTANCE);
	}
	

	@Test(expected = PileDepletedException.class)
	public void tryToBuyACardFromDepletedPile() throws BuyException {
		gameDeck.put(Curse.INSTANCE, 0);
		gameDeck.buy(Curse.INSTANCE);
	}

	@Test
	public void buysLastCopperNotifiesObserver() throws BuyException {
		//G
		gameDeck.put(Copper.INSTANCE, 1);
		observer = mock(GameDeckListener.class);
		gameDeck.addObserver(observer);
		//W
		gameDeck.buy(Copper.INSTANCE);
		//T
		verify(observer, times(1)).pileDepleted(Copper.INSTANCE);
	}
}
