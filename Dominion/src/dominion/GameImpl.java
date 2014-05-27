package dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class GameImpl implements Game {
	public GameDeck gameDeck;
	public List<Player> players;

	public GameImpl(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
		players = new ArrayList<Player>();
	}

	public GameDeck getGameDeck() {
		return gameDeck;
	}

	public Player getPlayer(int playerNumber) {
		return players.get(playerNumber);
	}

	@Override
	public void register(Player player) {
		player.drawHand(); //TODO: Untested!!!
		players.add(player);
	}

	@Override
	public List<Integer> getNumberOfCards() {
		List<Integer> numberOfCards = new ArrayList<>();
		for(Player player:players){
			numberOfCards.add(player.getHandSize());
		}
		return numberOfCards;
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player winner() {
		return Collections.max(players);
	}

}
