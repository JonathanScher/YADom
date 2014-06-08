package dominion.strategies;

import java.util.List;

import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.interfaces.strategies.CardsToBuy;
import dominion.interfaces.strategies.SimpleBehaviour;

public class BigMoney extends SimpleBehaviour {
	private static final int NUMBER_OF_PROVINCE_TO_BUY = 8;
	private static final int NUMBER_OF_GOLD_TO_BUY = 8;
	private static final int NUMBER_OF_SILVER_TO_BUY = 8;

	public static void initBigMoney(List<CardsToBuy> buyOrder){
		buyOrder.add(new CardsToBuy(Province.INSTANCE, NUMBER_OF_PROVINCE_TO_BUY));
		buyOrder.add(new CardsToBuy(Gold.INSTANCE, NUMBER_OF_GOLD_TO_BUY));
		buyOrder.add(new CardsToBuy(Silver.INSTANCE, NUMBER_OF_SILVER_TO_BUY));
		
	}
	
	public BigMoney() {
		initBigMoney(buyOrder);
	}
}
