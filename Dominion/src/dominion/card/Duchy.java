package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;

public class Duchy extends Card {
	public static final Card INSTANCE = new Duchy();

	private Duchy() {
		data = CardData.DUCHY;
	};
}
