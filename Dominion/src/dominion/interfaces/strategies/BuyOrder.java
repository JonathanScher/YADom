package dominion.interfaces.strategies;

import java.util.ArrayList;

public class BuyOrder extends ArrayList<CardsToBuy> {
	private static final long serialVersionUID = 8785279235044061774L;
	public String name;

	@Override
	public Object clone() {
		BuyOrder clone = new BuyOrder();
		this.forEach(x -> {
			clone.add((CardsToBuy) x.clone());
		});
		return clone;
	}
}
