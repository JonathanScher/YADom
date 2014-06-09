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
	private static final int MAX_GAME_TURNS = 80;
	private static final Logger LOGGER = Logger.getLogger(GameImpl.class);
	public GameDeck gameDeck;
	public List<Player> players;
	public int turn;

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
		Integer playerTurn = 0;
		while (!over) {
			play(playerTurn);
			playerTurn = incrementPlayerTurn(playerTurn);
			over = gameOver();
		}
		players.forEach(x -> {
			LOGGER.trace(x + " has scored " + x.victoryValue());
		});
	}

	private void play(Integer playerTurn) {
		Player player = players.get(playerTurn);
		player.turn(this);
	}

	private Integer incrementPlayerTurn(Integer playerTurn) {
		Integer next = playerTurn;
		next ++;
		if(next >= players.size()) {
			next = 0;
		}
		return next;
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
		return turn > MAX_GAME_TURNS || gameDeck.gameOver(players.size());
	}

	@Override
	public void buy(Card card, Player player) throws BuyException {
		if (gameDeck.get(card) == null) {
			throw new CardNotInDeckException(card);
		}
		if (gameDeck.get(card) < 1) {
			throw new PileDepletedException(card);
		}
		if (player.getGold() < card.getCost()) {
			throw new NotEnoughGoldException(card);
		}
		if (player.getBuyLeft() < 1) {
			throw new NotAllowedToBuyException(card);
		}
		player.buy(card);
		int numberOfCards = gameDeck.get(card);
		numberOfCards -= 1;
		gameDeck.put(card, numberOfCards);
	}

	@Override
	public void playCard(Player player, Card card) {
		LOGGER.trace(player + " plays " + card.getData());
		card.play(this, player);
	}

	@Override
	public Player getPlayer(int playerNumber) {
		return players.get(playerNumber);
	}
}
