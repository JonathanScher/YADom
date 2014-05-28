package dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dominion.cards.Card;
import dominion.deck.GameDeck;
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
		player.initPile();
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
			player.turn(this);
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

	@Override
	public void buy(Card card, Player player) {
		//TODO OK We are only concidering the happy case where the strategy will only do legal operation.
		// - try to buy a card when there is no more in pile?
		// - Multiple buy in a turn when I have only 1 buy possible?
		// - Buy a card without enough gold to do so?
		player.giveCard(card);
		int numberOfCards = gameDeck.get(card);
		numberOfCards -=1;
		gameDeck.put(card, numberOfCards);
	}
}
