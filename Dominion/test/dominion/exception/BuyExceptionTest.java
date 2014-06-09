package dominion.exception;

import static org.junit.Assert.*;

import org.junit.Test;

import dominion.card.Copper;

public class BuyExceptionTest {

	@Test
	public void equalsHashCode() {
		BuyException a = new CardNotInDeckException(Copper.INSTANCE);
		BuyException b = new CardNotInDeckException(Copper.INSTANCE);
		assertEquals(a, b);
		assertEquals(a.hashCode(), b.hashCode());
	}

}
