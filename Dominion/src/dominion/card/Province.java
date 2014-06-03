package dominion.card;

import dominion.card.data.CardData;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Province extends Card {
	public static final Card INSTANCE = new Province();
	
	
	private Province() {
		data = CardData.PROVINCE;
	}
	
	@Override
	public void play(Game game, Player player) {
	}
}
