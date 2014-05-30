package dominion.strategies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dominion.PlayerImpl;
import dominion.cards.Card;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

@RunWith(MockitoJUnitRunner.class)
public class SmithyBigMoneyTest {
	@Mock
	Game game;
	Player player;

	@Before
	public void init() {
		player = new PlayerImpl();
		Strategy smithy = new SmithyBigMoney();
		player.setStrategy(smithy);
	}

	@Test
	public void ifSmithyInHandPlayIt() {
		player.getHand().add(Card.SMITHY);
		// W
		player.turn(game);
		// T
		verify(game).playCard(Card.SMITHY);
	}

	@Test
	public void ifNoSmithyBoughtBuyOne() throws BuyException {
		player.getHand().add(Card.GOLD, Card.SILVER);
		// W
		player.turn(game);
		// T
		verify(game).buy(Card.SMITHY, player);
	}
	
	@Test
	public void ifNotEnoughGoldDontBuySmithy() throws BuyException {
		// W
		player.turn(game);
		// T
		verify(game, times(0)).buy(Card.SMITHY, player);
	}
}
