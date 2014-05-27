package dominion;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerDeck extends ArrayList<Card> {
	private static final long serialVersionUID = -8374173682996740658L;
	protected Integer shuffled = 0;

	public Integer getShuffled() {
		return shuffled;
	}

	public void shuffle() {
		Collections.shuffle(this);
		shuffled++;
	}

	public void add(Card... cards) {
		for (Card card : cards) {
			this.add(card);
		}
	}

	public Integer value() {
		Integer value = 0;
		for (Card card : this) {
			value += card.victoryValue;
		}
		return value;
	}

}
