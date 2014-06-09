package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Curse extends Card {
	public static final Card INSTANCE = new Curse();

	private Curse() {
		data = CardData.CURSE;
	};
}
