package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Estate extends Card {
	public static final Card INSTANCE = new Estate();

	private Estate() {
		data = CardData.ESTATE;
	};
}
