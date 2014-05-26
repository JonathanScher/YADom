package dominion;

public class MockPlayerDeck extends PlayerDeck {

	private static final long serialVersionUID = 3837333289366272913L;

	@Override
	public void shuffle() {
		this.shuffled++;
	}

}
