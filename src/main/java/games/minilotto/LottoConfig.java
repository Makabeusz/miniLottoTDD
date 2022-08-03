package games.minilotto;


public class LottoConfig {

    // Integer constants
    public static final int NUMBERS_IN_GAME = 6;
    public static final int UPPER_BOUND_ACCEPTED_NUMBER = 99;
    public static final int LOWER_BOUND_ACCEPTED_NUMBER = 1;

    // String constants
    public static final String WON_MESSAGE = "You won!" + LottoConfig.HIT_NUMBERS_EVALUATION;
    public static final String LOSE_BUT_HIT_NUMBERS_MESSAGE = "You lose, but got hits." + LottoConfig.HIT_NUMBERS_EVALUATION;
    public static final String LOSE_MESSAGE = "You lose." + LottoConfig.HIT_NUMBERS_EVALUATION;
    public static final String WELCOME_MESSAGE = "Welcome to the miniLotto game!\n" +
            "Game rules are to type six numbers in range " + LOWER_BOUND_ACCEPTED_NUMBER + " to " + UPPER_BOUND_ACCEPTED_NUMBER + " (both ranges inclusive) and wait for the game results.\n" +
            "Results will be announced asap on your console. Good luck!";
    public static final String PROVIDE_X_NUMBER = "Type %d number: ";
    public static final String GIVEN_NUMBER_OUTSIDE_THE_RANGE = "Given numbers should be in range of " + LOWER_BOUND_ACCEPTED_NUMBER + " to " + UPPER_BOUND_ACCEPTED_NUMBER
            + ", but provided %d.\n";
    public static final String GIVEN_NUMBER_ALREADY_IN_GAME = "Given number was already introduced into the game.";
    public static final String ONLY_NUMBERS_ALLOWED = "Only numbers are allowed in the game.";
    public static final String UNEXPECTED_END_OF_SCANNER = "Input scanner unexpectedly reach its end.";

    private static final String HIT_NUMBERS_EVALUATION = "\nLotto numbers: %s\nPlayer hit numbers: %s";
}
