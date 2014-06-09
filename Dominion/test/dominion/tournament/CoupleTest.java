package dominion.tournament;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoupleTest {

	@Test
	public void testHashCodeAndEquals() {
		Couple<Integer> a = new Couple<Integer>(1, 1);
		Couple<Integer> b = new Couple<Integer>(1, 1);
		assertEquals(a, b);
		assertEquals(a.hashCode(), b.hashCode());
	}

}
