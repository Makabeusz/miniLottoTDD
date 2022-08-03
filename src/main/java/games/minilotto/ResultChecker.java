package games.minilotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResultChecker {

    private NumbersReceiver numbersReceiver;
    private RandomNumbersGenerator numbersGenerator;

    public ResultChecker(NumbersReceiver numbersReceiver, RandomNumbersGenerator numbersGenerator) {
        this.numbersReceiver = numbersReceiver;
        this.numbersGenerator = numbersGenerator;
    }

    public GameResults checkResults() {
        if (isPlayerWinner(getHitNumbers())) {
            return new GameResults(String.format(LottoConfig.WON_MESSAGE
                    , numbersGenerator.getWinningNumbers(), getHitNumbers()));
        }
        if (playerHasHits()) {
            return new GameResults(String.format(LottoConfig.LOSE_BUT_HIT_NUMBERS_MESSAGE
                    , numbersGenerator.getWinningNumbers(), getHitNumbers()));
        } else {
            return new GameResults(String.format(LottoConfig.LOSE_MESSAGE
                    , numbersGenerator.getWinningNumbers(), getHitNumbers()));
        }
    }

    private boolean playerHasHits() {
        return getHitNumbers().size() > 0;
    }

    private boolean isPlayerWinner(List<Integer> hitNumbers) {
        return hitNumbers.size() == LottoConfig.NUMBERS_IN_GAME;
    }

    private List<Integer> getHitNumbers() {
        List<Integer> playerNumbers = new ArrayList<>(numbersReceiver.getPlayerNumbers());

        playerNumbers.retainAll(numbersGenerator.getWinningNumbers());

        return playerNumbers;
    }
}
