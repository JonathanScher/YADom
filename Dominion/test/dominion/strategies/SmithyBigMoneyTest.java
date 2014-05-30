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
	SmithyBigMoney smithy;

	@Before
	public void init() {
		player = new PlayerImpl();
		smithy = new SmithyBigMoney();
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

	@Test
	public void ifAlreadyBoughtSmithyDontBuyASecond() throws BuyException {
		player.getHand().add(Card.GOLD, Card.SILVER);
		player.turn(game);
		player.getHand().add(Card.GOLD, Card.SILVER);
		// W
		player.turn(game);
		// T
		verify(game, times(1)).buy(Card.SMITHY, player);
	}

	@Test
	public void otherWiseBigMoney() {
		// G
		Strategy bigMoney = mock(Strategy.class);
		smithy.otherStrat = bigMoney;
		smithy.hasOneSmithy = true;
		player.getHand().add(Card.GOLD, Card.SILVER);
		// W
		smithy.turn(player, game);
		// Then
		verify(bigMoney).turn(player, game);

	}
}
