package pl.juniorJavaReady.LottoProject.ReadUserInput;

import pl.juniorJavaReady.LottoProject.LottoGameSettings;
import pl.juniorJavaReady.LottoProject.MessageProvider;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsoleInputReader implements InputReader {

    Scanner scanner;
    final Set<Integer> userGuesses;

    public ConsoleInputReader() {
        this.scanner = new Scanner(System.in);
        this.userGuesses = new HashSet<>();
    }

    @Override
    public Set<Integer> acquireUserInput() {
        System.out.println(MessageProvider.PROMPT);

        while (userGuesses.size() < LottoGameSettings.NUMBERS_FOR_LOTTO_GAME) {
            int userInput = readUserInput();

            if (isInBounds(userInput)) {
                addUserInputToUserGuesses(userInput);
            } else {
                informUserInputOutOfBounds();
            }
        }
        return userGuesses;
    }

    private int readUserInput() {
        int userInput;
        while (true) {
            if (isInputNumber()) {
                userInput = scanner.nextInt();
                break;
            } else {
                scanner.next();
                informUserInputIsNotNumber();
            }
        }
        return userInput;
    }

    private void addUserInputToUserGuesses(int userInput) {
        userGuesses.add(userInput);
    }

    private boolean isInputNumber() {
        return scanner.hasNextInt();
    }

    private void informUserInputIsNotNumber() {
        System.out.println(MessageProvider.NOT_NUMBER);
    }

    private boolean isInBounds(int guess) {
        return guess < 100 && guess > 0;
    }

    private void informUserInputOutOfBounds() {
        System.out.println(MessageProvider.OUT_OF_BOUNDS);
    }
}
