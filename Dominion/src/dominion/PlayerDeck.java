package dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.ToIntFunction;

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

	public Integer victoryValue(){
		return sumCard(c -> c.victoryValue);
	}

	public Integer goldValue() {
		return sumCard(c -> c.goldValue);
	}

	private Integer sumCard(ToIntFunction<Card> mapper) {
		return this.parallelStream().mapToInt(mapper).sum();
	}
}
