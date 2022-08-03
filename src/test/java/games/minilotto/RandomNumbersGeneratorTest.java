package games.minilotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RandomNumbersGeneratorTest {

    private RandomNumbersGenerator randomNumbersGenerator;

    @Test
    public void generator_produce_six_distinct_numbers() {
        // given
        randomNumbersGenerator = new RandomNumbersGenerator();
        List<Integer> withoutDuplicates;

        // when
        List<Integer> winningSet = randomNumbersGenerator.getWinningNumbers();
        withoutDuplicates = new ArrayList<>(new HashSet<>(winningSet));

        // then
        assertThat(withoutDuplicates.size()).isEqualTo(6);
    }

}