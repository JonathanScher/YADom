package dominion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardTest {

	@Test
	public void test() {
		assertEquals(new Integer(1), Card.ESTATE.victoryValue);
	}

}
