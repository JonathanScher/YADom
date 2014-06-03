package dominion.strategies;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.exception.BuyException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

@RunWith(MockitoJUnitRunner.class)
public class BigMoneyTest {
	@Mock
	public Game game;
	@Mock
	public Player player;

	public Strategy bigMoney;

	@Before
	public void init() {
		bigMoney = new BigMoney();
	}

	@Test
	public void whenIGet12GoldIBuyAProvince() throws BuyException {
		//G
		when(player.getGold()).thenReturn(12);
		//When
		bigMoney.turn(player, game);
		//Then
		verify(game).buy(Province.INSTANCE, player);
	}
	
	@Test
	public void whenIGet6GoldIBuyAGold() throws BuyException {
		//G
		when(player.getGold()).thenReturn(6);
		//When
		bigMoney.turn(player, game);
		//Then
		verify(game).buy(Gold.INSTANCE, player);
	}

	@Test
	public void whenIGet3GoldIBuyASilver() throws BuyException {
		//G
		when(player.getGold()).thenReturn(3);
		//When
		bigMoney.turn(player, game);
		//Then
		verify(game).buy(Silver.INSTANCE, player);
	}

}
