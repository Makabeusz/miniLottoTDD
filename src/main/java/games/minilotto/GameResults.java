package games.minilotto;

public class GameResults {

    private final String gameResults;

    public GameResults(String gameResults) {
        this.gameResults = gameResults;
    }

    @Override
    public String toString() {
        return gameResults;
    }
}
