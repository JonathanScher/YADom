package dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

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
		player.drawHand();
		players.add(player);
	}

	@Override
	public List<Integer> getNumberOfCards() {
		List<Integer> numberOfCards = new ArrayList<>();
		for (Player player : players) {
			numberOfCards.add(player.getHandSize());
		}
		return numberOfCards;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		// call each player "turn" until game over
		//
		// Game over : after 1000 turns, or no more Province, or enough stack
		// empty
		// enough being 2 for a 2 player game, 3 for more than 2 players
	}

	@Override
	public Player winner() {
		return Collections.max(players);
	}

	@Override
	public Boolean gameOver() {
		return gameDeck.gameOver(players.size());
	}
}
