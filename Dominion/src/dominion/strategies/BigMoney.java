package dominion.strategies;

import dominion.card.Gold;
import dominion.card.Province;
import dominion.card.Silver;
import dominion.exception.BuyException;
import dominion.exception.CardNotInDeckException;
import dominion.exception.PileDepletedException;
import dominion.interfaces.Game;
import dominion.interfaces.Player;
import dominion.interfaces.Strategy;

public class BigMoney implements Strategy {

	@Override
	public void turn(Player player, Game game) {
		try{
		if (player.getGold() > 7) {
			game.buy(Province.INSTANCE, player);
		} else if (player.getGold() > 5) {
			game.buy(Gold.INSTANCE, player);
		} else if (player.getGold() > 2) {
			game.buy(Silver.INSTANCE, player);
		}
		} catch (PileDepletedException e) {
			System.err.println("BigMoney is trying to buy from a depleted pile");
		} catch (CardNotInDeckException e) {
			System.err.println("BigMoney is trying to buy from a card that is not in deck");
		} catch (BuyException e) {
			System.err.println("BigMoney is unable to buy a card for an unknown reason");
		}
	}

}
