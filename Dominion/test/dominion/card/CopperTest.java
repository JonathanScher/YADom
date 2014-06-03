package dominion.card;

import static org.junit.Assert.*;

import org.junit.Test;

import dominion.interfaces.Card;

public class CopperTest {

	@Test
	public void test() {
		Card card = Copper.INSTANCE;
		assertEquals(Integer.valueOf(0), card.getCost());
		assertEquals(Integer.valueOf(1), card.getGoldValue());

	}

}
