package dominion.interfaces.strategies;

import static org.junit.Assert.*;

import org.junit.Test;

import dominion.card.Copper;

public class CardsToBuyTest {

	@Test
	public void equals() {
		CardsToBuy cardsToBuy = new CardsToBuy(Copper.INSTANCE, 0);
		CardsToBuy cardsToBuy2 = new CardsToBuy(Copper.INSTANCE, 0);
		assertEquals(cardsToBuy, cardsToBuy2);
		assertEquals(cardsToBuy.hashCode(), cardsToBuy2.hashCode());
	}

}
