package dominion.cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominion.interfaces.CardName;

public class CardTest {

	@Test
	public void test() {
		assertEquals(new Integer(1), CardName.ESTATE.victoryValue);
	}

}
