package games.minilotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class NumbersReceiver {

    private Scanner scanner;
    private final Set<Integer> playerNumbers;

    // for Mockito.spy method only
    public NumbersReceiver() {
        playerNumbers = new HashSet<>();
    }

    public NumbersReceiver(Scanner scanner) {
        this.scanner = scanner;
        playerNumbers = new HashSet<>();
    }

    public void inputNumbers() {
        int playerNumber;
        for (int i = 0; i < LottoConfig.NUMBERS_IN_GAME; i++) {
            System.out.printf(LottoConfig.PROVIDE_X_NUMBER, i+1);
            try {
                playerNumber = scanner.nextInt();
                if (numberIsOutsideTheRange(playerNumber)) {
                    System.out.printf(LottoConfig.GIVEN_NUMBER_OUTSIDE_THE_RANGE, playerNumber);
                    i--;
                } else if (isDuplicate(playerNumber)) {
                    System.out.println(LottoConfig.GIVEN_NUMBER_ALREADY_IN_GAME);
                    i--;
                } else {
                    playerNumbers.add(playerNumber);
                }
            } catch (InputMismatchException e) {
                System.out.println(LottoConfig.ONLY_NUMBERS_ALLOWED);
                scanner.next();
                i--;
            } catch (NoSuchElementException e) {
                System.out.println(LottoConfig.UNEXPECTED_END_OF_SCANNER);
                scanner.close();
                return;
            }

        }
    }

    public List<Integer> getPlayerNumbers() {
        List<Integer> list = new ArrayList<>(playerNumbers);
        Collections.sort(list);
        return Collections.unmodifiableList(list);
    }

    private boolean numberIsOutsideTheRange(int number) {
        return number < LottoConfig.LOWER_BOUND_ACCEPTED_NUMBER
                || number > LottoConfig.UPPER_BOUND_ACCEPTED_NUMBER;
    }

    private boolean isDuplicate(int number) {
        return playerNumbers.contains(number);
    }

}
