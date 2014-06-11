package dominion.tournament;

import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import dominion.PlayerImpl;
import dominion.card.Duchy;
import dominion.card.Estate;
import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.card.Smithy;
import dominion.deck.GameDeck;
import dominion.interfaces.Card;
import dominion.interfaces.Player;
import dominion.interfaces.strategies.BuyOrder;
import dominion.interfaces.strategies.CardsToBuy;
import dominion.interfaces.strategies.SimpleBehaviour;

public class GeneticIA {
	static Logger logger = Logger.getLogger(GeneticIA.class);
	public BuyOrder buyOrder0 = new BuyOrder();
	public BuyOrder buyOrder1 = new BuyOrder();

	// create initial buyOrder
	// while(100 times?) {
	// generate 2 variations
	// Play tournament
	// keep 2 best strategies
	// }

	public void run() {
		initialBuyOrder();

		for (int i = 0; i < 100; i++) {
			BuyOrder child0 = generateChild();
			BuyOrder child1 = generateChild();

			GeneticIA.logger.info("============");
			GeneticIA.logger.info("Generation "+i);
			GeneticIA.logger.info("buy0 " +buyOrder0);
			GeneticIA.logger.info("buy1 " +buyOrder1);
			GeneticIA.logger.info("child0" +child0);
			GeneticIA.logger.info("child1 " +child1);
			
			
			GameDeck gameDeck = GameDeck.basicDeck2Players();
			gameDeck.put(Smithy.INSTANCE, 8);

			Tournament tournament = new Tournament(gameDeck);
			Map<Player, Integer> winners = tournament.play(buyOrder0,
					buyOrder1, child1, child0);
			TreeMap<Player, Integer> sortedWinners = new TreeMap<>(
					new Comparator<Player>() {

						@Override
						public int compare(Player o1, Player o2) {
							return winners.get(o2).compareTo(winners.get(o1));
						}
					});
			sortedWinners.putAll(winners);
			buyOrder0 = ((SimpleBehaviour) ((PlayerImpl) sortedWinners
					.firstKey()).getStrategy()).buyOrder;
			sortedWinners.remove(sortedWinners.firstKey());
			buyOrder1 = ((SimpleBehaviour) ((PlayerImpl) sortedWinners
					.firstKey()).getStrategy()).buyOrder;
		}
	}

	public void initialBuyOrder() {

		for (int i = 0; i < 3; i++) {
			buyOrder0.add(Province.INSTANCE, 0);
			buyOrder0.add(Gold.INSTANCE, 0);
			buyOrder0.add(Smithy.INSTANCE, 0);
			buyOrder0.add(Silver.INSTANCE, 0);
			buyOrder0.add(Duchy.INSTANCE, 0);
			buyOrder0.add(Estate.INSTANCE, 0);
		}

		for (int i = 0; i < 3; i++) {
			buyOrder1.add(Province.INSTANCE, 8);
			buyOrder1.add(Gold.INSTANCE, 8);
			buyOrder1.add(Smithy.INSTANCE, 8);
			buyOrder1.add(Silver.INSTANCE, 8);
			buyOrder1.add(Duchy.INSTANCE, 8);
			buyOrder1.add(Estate.INSTANCE, 8);
		}
	}

	public BuyOrder generateChild() {
		BuyOrder child = new BuyOrder();
		for (int i = 0; i < 18; i++) {
			Random random = new Random(System.currentTimeMillis());
			Integer dice = random.nextInt(10);
			Card card = buyOrder0.get(i).card;
			Integer numberToBuy;
			if (dice == 5) {
				numberToBuy = (buyOrder0.get(i).numberToBuy + buyOrder1.get(i).numberToBuy) / 2;
			} else if (dice < 5) {
				numberToBuy = buyOrder0.get(i).numberToBuy;
			} else {
				numberToBuy = buyOrder1.get(i).numberToBuy;
			}
			CardsToBuy element = new CardsToBuy(card, numberToBuy);
			child.add(element);
		}
		return child;
	}
}
