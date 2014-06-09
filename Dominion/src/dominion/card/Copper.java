package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Copper extends Card {
	public static final Card INSTANCE = new Copper();

	private Copper() {
		data = CardData.COPPER;
	}
}
