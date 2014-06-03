package dominion.card;

import org.junit.Test;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

@RunWith(MockitoJUnitRunner.class)
public class SmithyTest {
	@Mock
	public Game game;
	@Mock
	public Player player;

	@Test
	public void action() {
		//G
		Card smithy = Smithy.INSTANCE;
		//W
		smithy.play(game, player);
		//T
		verify(player).draw(3);
	}
}
