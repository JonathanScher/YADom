package dominion.interfaces.strategies;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.apache.log4j.Logger;
import org.junit.Test;

import dominion.GameImpl;
import dominion.PlayerImpl;
import dominion.card.Copper;
import dominion.deck.GameDeck;
import dominion.exception.PileDepletedException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class SimpleBehaviourTest {

	@Test
	public void SimpleBehaviourWhenCantBuy() {
		//G
		BuyOrder buyOrder = new BuyOrder();
		buyOrder.add(new CardsToBuy(Copper.INSTANCE, 1));
		SimpleBehaviour sb = new SimpleBehaviour(buyOrder);
		sb.buyOrder = buyOrder;
		SimpleBehaviour.logger = mock(Logger.class);
		
		Player player = new PlayerImpl();
		player.setStrategy(sb);
		
		GameDeck gameDeck = new GameDeck();
		gameDeck.put(Copper.INSTANCE, 0);
		
		Game game = new GameImpl(gameDeck);
		
		//W
		sb.buy(player, game);
		
		//T
		verify(SimpleBehaviour.logger).error(new PileDepletedException(Copper.INSTANCE));
	}

}
