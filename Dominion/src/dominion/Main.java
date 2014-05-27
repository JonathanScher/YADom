package dominion;

import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class Main {

	public static void main(String[] args) {
		GameDeck gameDeck = new GameDeck();
		//We should be able to define a starting deck here
		Game game = new GameImpl(gameDeck);

		Player player0 = new PlayerImpl();
		Player player1 = new PlayerImpl();
		//at some point we want to define the strategy for each player here
		game.register(player0);
		game.register(player1);
		
		game.play(); //not implemented
		System.out.println("winner is: " + game.winner());
	}

}
