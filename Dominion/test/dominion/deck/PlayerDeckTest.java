package dominion.deck;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominion.card.Copper;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Silver;
public class PlayerDeckTest {
	@Test
	public void addMultipleCards(){
		//Given
		PlayerDeck playerDeck = new PlayerDeck();
		PlayerDeck expected = new PlayerDeck();
		expected.add(Copper.INSTANCE);
		expected.add(Copper.INSTANCE);
		//When
		playerDeck.add(Copper.INSTANCE, Copper.INSTANCE);
		//Then
		assertEquals(expected, playerDeck);
	}
	
	@Test
	public void victoryValue() {
		PlayerDeck playerDeck = new PlayerDeck();
		playerDeck.add(Copper.INSTANCE, Estate.INSTANCE, Duchy.INSTANCE);
		
		assertEquals(new Integer(3), playerDeck.victoryValue());
	}
	@Test
	public void goldValue(){
		PlayerDeck playerDeck = new PlayerDeck();
		playerDeck.add(Copper.INSTANCE, Silver.INSTANCE, Gold.INSTANCE);
		
		assertEquals(new Integer(6), playerDeck.goldValue());
	}
	
	@Test
	public void goldWithEstates(){
		PlayerDeck playerDeck = new PlayerDeck();
		playerDeck.add(Copper.INSTANCE, Estate.INSTANCE);
		
		assertEquals(new Integer(1), playerDeck.goldValue());
	}
}
