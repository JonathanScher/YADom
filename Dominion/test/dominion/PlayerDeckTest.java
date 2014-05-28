package dominion;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class PlayerDeckTest {
	@Test
	public void addMultipleCards(){
		//Given
		PlayerDeck playerDeck = new PlayerDeck();
		PlayerDeck expected = new PlayerDeck();
		expected.add(Card.COPPER);
		expected.add(Card.COPPER);
		//When
		playerDeck.add(Card.COPPER, Card.COPPER);
		//Then
		assertEquals(expected, playerDeck);
	}
	
	@Test
	public void victoryValue(){
		PlayerDeck playerDeck = new PlayerDeck();
		playerDeck.add(Card.COPPER, Card.ESTATE, Card.DUCHY);
		
		assertEquals(new Integer(3), playerDeck.value());
	}
}
