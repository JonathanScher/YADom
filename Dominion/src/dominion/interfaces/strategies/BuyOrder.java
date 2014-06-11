package dominion.interfaces.strategies;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;

import dominion.interfaces.Card;

public class BuyOrder extends ArrayList<CardsToBuy> {
	private static final long serialVersionUID = 8785279235044061774L;
	public String name;

	public BuyOrder() {
	}

	public BuyOrder(BuyOrder origin) {
		origin.forEach(x -> {
			add(new CardsToBuy(x));
		});
		name = origin.name;
	}

	public void add(Card card, Integer numberToBuy) {
		add(new CardsToBuy(card, numberToBuy));
	}

}
