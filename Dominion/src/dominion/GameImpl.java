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
		Integer numberOfPlayers = players.size();
		Boolean over = gameDeck.gameOver(numberOfPlayers);
		while (!over) {
			over = gameTurn(numberOfPlayers);
		}
	}

	private Boolean gameTurn(Integer numberOfPlayers) {
		// having a return in a middle of a loop kills me. I don't know why, and
		// I don't know how to simply get rid of it
		for (Player player : players) {
			player.turn();
			if (gameDeck.gameOver(numberOfPlayers)) {
				return true;
			}
		}
		return false;
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
