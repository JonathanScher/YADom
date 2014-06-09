package dominion.strategies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;


public class MatchServiceTest {

	@Test
	public void run() {
		//G
		Match match = mock(Match.class);
		InOrder inOrder = inOrder(match);
		MatchService matchService = new MatchService(match);
		//W
		matchService.run();
		//T
		inOrder.verify(match).init();
		inOrder.verify(match).initGames();
		inOrder.verify(match).playGames();
		verify(match).gatherResult();
	}

}
