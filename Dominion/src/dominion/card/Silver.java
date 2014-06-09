package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Silver extends Card {
	public static final Card INSTANCE = new Silver();

	private Silver() {
		data = CardData.SILVER;
	};
}
