package dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import dominion.deck.GameDeck;
import dominion.exception.BuyException;
import dominion.exception.CardNotInDeckException;
import dominion.exception.NotAllowedToBuyException;
import dominion.exception.NotEnoughGoldException;
import dominion.exception.PileDepletedException;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class GameImpl implements Game {
	private static final Logger logger = Logger.getLogger(GameImpl.class);
	public GameDeck gameDeck;
	public List<Player> players;

	public GameImpl(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
		players = new ArrayList<Player>();
	}

	public GameDeck getGameDeck() {
		return gameDeck;
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
		players.forEach(x->{logger.trace(x + " has scored " + x.victoryValue());});
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
	public List<Player> winner() {
		List<Player> winners = new ArrayList<>();
		winners.addAll(players);
		Collections.sort(winners, Collections.reverseOrder());
		return winners;
	}

	@Override
	public Boolean gameOver() {
		return gameDeck.gameOver(players.size());
	}

	@Override
	public void buy(Card card, Player player) throws BuyException {
		if (gameDeck.get(card) == null) {
			throw new CardNotInDeckException();
		}
		if (gameDeck.get(card) < 1) {
			throw new PileDepletedException();
		}
		if (player.getGold() < card.getCost()) {
			throw new NotEnoughGoldException();
		}
		if (player.getBuyLeft() < 1) {
			throw new NotAllowedToBuyException();
		}
		player.buy(card);
		int numberOfCards = gameDeck.get(card);
		numberOfCards -= 1;
		gameDeck.put(card, numberOfCards);
	}

	@Override
	public void playCard(Player player, Card card) {
		logger.trace(player + " plays " +card.getData());
		card.play(this, player);
	}
}
