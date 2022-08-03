package games.minilotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NumbersReceiverTest {

    private NumbersReceiver numbersReceiver;
    private Scanner scannerMock;

    @ParameterizedTest
    @MethodSource("userInputsAndExpectedOutputs")
    @DisplayName("[1] six correct numbers " +
            "[2] numbers from outside the scope and correct ones beside them " +
            "[3] ignore duplicates and accept six correct numbers ")
    public void user_inputs_with_both_correct_and_incorrect_values(String userInput, String expectedOutput) {
        // given
        scannerMock = getScannerMock(userInput);
        numbersReceiver = new NumbersReceiver(scannerMock);

        // when
        numbersReceiver.inputNumbers();

        // then
        List<Integer> expected = sortedList(expectedOutput);
        List<Integer> playerNumbers = numbersReceiver.getPlayerNumbers();
        assertThat(playerNumbers).isEqualTo(expected);
    }

    private static Stream<Arguments> userInputsAndExpectedOutputs() {
        return Stream.of(
                arguments("1 2 3 4 5 6", "1 2 3 4 5 6")
                , arguments("-1 10 102 20 150 30 0 40 50 100 60", "10 20 30 40 50 60")
                , arguments("99 99 88 88 77 66 55 55 44", "44 55 66 77 88 99")
        );
    }

    private List<Integer> sortedList(String stringList) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(stringList.split("\\s")).forEach(
                stringNumber -> list.add(Integer.parseInt(stringNumber))
        );
        return Collections.unmodifiableList(list);
    }

    private Scanner getScannerMock(String text) {
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        Scanner scannerMock = new Scanner(System.in);
        System.setIn(originalSystemIn);

        return scannerMock;
    }
}
