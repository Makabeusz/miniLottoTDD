package games.minilotto;

import games.Game;

import java.util.Scanner;

public class LottoGame implements Game {

    @Override
    public void play() {
        System.out.println(LottoConfig.WELCOME_MESSAGE);

        NumbersReceiver numbersReceiver = new NumbersReceiver(new Scanner(System.in));
        RandomNumbersGenerator  numbersGenerator = new RandomNumbersGenerator();

        numbersReceiver.inputNumbers();

        ResultChecker resultChecker = new ResultChecker(numbersReceiver, numbersGenerator);

        System.out.println(resultChecker.checkResults());
    }

    public static void main(String[] args) {
        LottoGame game = new LottoGame();
        game.play();
    }
}
