package dominion.deck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dominion.cards.Card;

public class GameDeckTest {

	GameDeck gameDeck;
	
	@Before
	public void init(){
		gameDeck = new GameDeck();
	}
	
	@Test
	public void gameOverIfProvinceRunsOut() {
		gameDeck.put(Card.PROVINCE, 0);
		assertTrue(gameDeck.gameOver(2));
	}

	@Test
	public void gameNotOver() {
		gameDeck.put(Card.PROVINCE, 2);
		assertFalse(gameDeck.gameOver(2));
	}

	
	@Test
	public void gameOverIf2StacksAreOut2Players() {
		gameDeck.put(Card.COPPER, 0);
		gameDeck.put(Card.DUCHY, 0);
		assertTrue(gameDeck.gameOver(2));
	}

	@Test
	public void gameNotOverIf2StacksAreOut4Players() {
		gameDeck.put(Card.COPPER, 0);
		gameDeck.put(Card.DUCHY, 0);
		assertFalse(gameDeck.gameOver(4));
	}

}
