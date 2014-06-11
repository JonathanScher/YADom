package dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import dominion.card.Province;
import dominion.deck.GameDeck;
import dominion.exception.BuyException;
import dominion.exception.NotAllowedToBuyException;
import dominion.exception.NotEnoughGoldException;
import dominion.interfaces.Card;
import dominion.interfaces.Game;
import dominion.interfaces.Player;

public class GameImpl implements Game {
	private static final int MAX_GAME_TURNS = 80;
	private static final Logger LOGGER = Logger.getLogger(GameImpl.class);
	public GameDeck gameDeck;
	public List<Player> players;
	public int turn;
	public Boolean gameOver;
	public Set<Card> depleted;

	public GameImpl(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
		gameDeck.addObserver(this);
		players = new ArrayList<Player>();
		gameOver = false;
		depleted = new HashSet<>();
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
		Integer playerTurn = 0;
		while (!gameOver) {
			play(playerTurn);
			playerTurn = incrementPlayerTurn(playerTurn);
			turn++;
			if (turn > MAX_GAME_TURNS) {
				gameOver = true;
			}
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
		next++;
		if (next >= players.size()) {
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
	public void buy(Card card, Player player) throws BuyException {
		if (player.getGold() < card.getCost()) {
			throw new NotEnoughGoldException(card);
		}
		if (player.getBuyLeft() < 1) {
			throw new NotAllowedToBuyException(card);
		}
		gameDeck.buy(card);
		player.bought(card);
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

	@Override
	public void pileDepleted(Card card) {
		depleted.add(card);

		if (Province.INSTANCE.equals(card) || depleted.size() >= 3) {
			gameOver = true;
		}
	}
}
