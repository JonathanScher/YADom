package dominion.tournament;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TournamentTest {

	@Test
	public void combinaisons() {
		// G
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		List<Couple<Integer>> expected = new ArrayList<>();
		expected.add(new Couple<Integer>(1, 2));
		expected.add(new Couple<Integer>(1, 3));
		expected.add(new Couple<Integer>(2, 3));
		// W
		List<Couple<Integer>> actual = Tournament.combinaisons(list);
		
		//T
		assertEquals(expected, actual);
	}
}
