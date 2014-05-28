package dominion.mock;

import dominion.GameDeck;

public class GameDeckMock extends GameDeck {
	private static final long serialVersionUID = 1422436854704973346L;
	public Boolean gameOver;
	public Integer numberOfPlayers;
	
	public GameDeckMock(Boolean gameOver) {
		this.gameOver = gameOver;
	}
	@Override
	public Boolean gameOver(Integer numberOfPlayers){
		this.numberOfPlayers = numberOfPlayers;
		return gameOver;
	}
}
