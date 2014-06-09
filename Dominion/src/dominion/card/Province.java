package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Province extends Card {
	public static final Card INSTANCE = new Province();
	
	
	private Province() {
		data = CardData.PROVINCE;
	}
}
