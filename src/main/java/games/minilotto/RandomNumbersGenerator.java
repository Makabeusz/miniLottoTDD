package games.minilotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumbersGenerator {

    private final List<Integer> winningNumbers;



    public RandomNumbersGenerator() {
        winningNumbers = randomWinningNumbers();
    }

    private List<Integer> randomWinningNumbers() {
        Random random = new Random();
        Set<Integer> winningSet = new HashSet<>();
        do {
            winningSet.add(random.nextInt(LottoConfig.UPPER_BOUND_ACCEPTED_NUMBER + 1));
        } while (winningSet.size() < LottoConfig.NUMBERS_IN_GAME);
        List<Integer> resultList = new ArrayList<>(List.copyOf(winningSet));
        Collections.sort(resultList);

        return resultList;
    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }
}
