package dominion.strategies;

import org.apache.log4j.Logger;

public class MatchService {
	public static final Logger LOGGER = Logger.getLogger(MatchService.class);
	public Match match;

	public MatchService(Match match) {
		this.match = match;
	}

	public void run() {
		LOGGER.debug("run Start");
		match.init();
		LOGGER.debug("init done");
		match.initGames();
		LOGGER.debug("initGame done");
		match.playGames();
		LOGGER.debug("playGame done");
		match.gatherResult();
		LOGGER.debug("gatherResult done");
	}
}
