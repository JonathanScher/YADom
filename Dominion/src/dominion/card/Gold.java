package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Gold extends Card {
	public static final Card INSTANCE = new Gold();

	private Gold() {
		data = CardData.GOLD;
	};
}
