package dominion.strategies;

import dominion.card.Smithy;
import dominion.interfaces.strategies.Couple;
import dominion.interfaces.strategies.SimpleBehaviour;

public class SmithyBigMoney extends SimpleBehaviour {
	private static final int NUMBER_OF_SMITHIES_TO_BUY = 1;

	public SmithyBigMoney() {
		buyOrder.add(new Couple(Smithy.INSTANCE, NUMBER_OF_SMITHIES_TO_BUY));
		BigMoney.initBigMoney(buyOrder);
	}
}
