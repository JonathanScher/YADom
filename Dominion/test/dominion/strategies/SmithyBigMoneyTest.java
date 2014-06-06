package dominion.strategies;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dominion.PlayerImpl;
import dominion.card.Gold;
import dominion.card.Silver;
import dominion.card.Smithy;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

@RunWith(MockitoJUnitRunner.class)
public class SmithyBigMoneyTest {
	@Mock
	Game game;
	Player player;
	SmithyBigMoney smithy;

	@Before
	public void init() {
		player = new PlayerImpl();
		smithy = new SmithyBigMoney();
		player.setStrategy(smithy);
	}

	@Test
	public void ifSmithyInHandPlayIt() {
		player.getHand().add(Smithy.INSTANCE);
		smithy.turn(player, game);
		verify(game).playCard(player, Smithy.INSTANCE);
	}

	@Test
	public void ifNoSmithyBoughtBuyOne() throws BuyException {
		player.getHand().add(Gold.INSTANCE, Silver.INSTANCE);
		// W
		player.turn(game);
		// T
		verify(game).buy(Smithy.INSTANCE, player);
	}

	@Test
	public void ifNotEnoughGoldDontBuySmithy() throws BuyException {
		// W
		player.turn(game);
		// T
		verify(game, times(0)).buy(Smithy.INSTANCE, player);
	}

	@Test
	public void ifAlreadyBoughtSmithyDontBuyASecond() throws BuyException {
		player.getHand().add(Gold.INSTANCE, Silver.INSTANCE);
		player.turn(game);
		player.getHand().add(Gold.INSTANCE, Silver.INSTANCE);
		// W
		player.turn(game);
		// T
		verify(game, times(1)).buy(Smithy.INSTANCE, player);
	}

	@Test
	public void otherWiseBigMoney() throws BuyException {
		// G
		player.getHand().add(Gold.INSTANCE, Silver.INSTANCE);
		smithy.turn(player, game);
		// W
		smithy.turn(player, game);
		// Then
		verify(game, times(1)).buy(Smithy.INSTANCE, player);
		verify(game, times(1)).buy(Silver.INSTANCE, player);
	}
}
