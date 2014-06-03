package dominion.deck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dominion.card.Copper;
import dominion.card.Duchy;
import dominion.card.Province;

public class GameDeckTest {

	GameDeck gameDeck;
	
	@Before
	public void init(){
		gameDeck = new GameDeck();
	}
	
	@Test
	public void gameOverIfProvinceRunsOut() {
		gameDeck.put(Province.INSTANCE, 0);
		assertTrue(gameDeck.gameOver(2));
	}

	@Test
	public void gameNotOver() {
		gameDeck.put(Province.INSTANCE, 2);
		assertFalse(gameDeck.gameOver(2));
	}

	
	@Test
	public void gameOverIf2StacksAreOut2Players() {
		gameDeck.put(Copper.INSTANCE, 0);
		gameDeck.put(Duchy.INSTANCE, 0);
		assertTrue(gameDeck.gameOver(2));
	}

	@Test
	public void gameNotOverIf2StacksAreOut4Players() {
		gameDeck.put(Copper.INSTANCE, 0);
		gameDeck.put(Duchy.INSTANCE, 0);
		assertFalse(gameDeck.gameOver(4));
	}

}
