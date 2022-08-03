package games.minilotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ResultCheckerTest {

    private ResultChecker resultChecker;
    private NumbersReceiver numbersReceiver;
    private RandomNumbersGenerator numbersGenerator;

    @ParameterizedTest
    @MethodSource("possibleGameResults")
    @DisplayName("[1] player won [2] player lose, but got hits [3] player lose")
    public void winning_game_results(String playerNumbers, String lottoNumbers, String hitNumbers, String resultMessage) {
        // given
        numbersReceiver = spy(NumbersReceiver.class);
        numbersGenerator = spy(RandomNumbersGenerator.class);


        // when
        when(numbersReceiver.getPlayerNumbers()).thenReturn(sortedList(playerNumbers));
        when(numbersGenerator.getWinningNumbers()).thenReturn(sortedList(lottoNumbers));
        resultChecker = new ResultChecker(numbersReceiver, numbersGenerator);
        GameResults gameResults = resultChecker.checkResults();

        // then
        assertThat(gameResults.toString()).isEqualTo(resultMessage +
                "\nLotto numbers: [" + lottoNumbers + "]\n" +
                "Player hit numbers: [" + hitNumbers + "]");
    }

    public static Stream<Arguments> possibleGameResults() {
        return Stream.of(
                arguments("21, 22, 23, 24, 25, 26", "21, 22, 23, 24, 25, 26", "21, 22, 23, 24, 25, 26", "You won!")
                , arguments("34, 44, 54, 64, 74, 84", "34, 44, 54, 55, 56, 57", "34, 44, 54", "You lose, but got hits.")
                , arguments("18, 28, 38, 48, 58, 68", "91, 92, 93, 94, 95, 96", "", "You lose.")
        );
    }

    private List<Integer> sortedList(String stringList) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(stringList.split(", ")).forEach(
                stringNumber -> list.add(Integer.parseInt(stringNumber))
        );
        return Collections.unmodifiableList(list);
    }
}
