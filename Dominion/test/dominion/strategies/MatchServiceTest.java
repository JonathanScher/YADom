package dominion.strategies;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.InOrder;


public class MatchServiceTest {

	@Test
	public void run() {
		//G
		Match match = mock(Match.class);
		InOrder inOrder = inOrder(match);
		//W
		MatchService.runMatch(match);
		//T
		inOrder.verify(match).init();
		inOrder.verify(match).initGames();
		inOrder.verify(match).playGames();
		verify(match).gatherResult();
	}

}
