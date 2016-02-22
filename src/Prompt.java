import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Ethan on 2/20/16.
 */
public class Prompt {
    private static final int MAX_WORD_LENGTH = 20;
    private static final int SIZE_ALPHABET = 27;
    private static Scanner prompter = new Scanner(System.in);

    /**
     * Sets the InputStream for prompter. Used for testing.
     * If this method is never called, prompter defaults to System.in
     * @param in InputStream for prompter to read from.
     */
    public static void setIn(InputStream in) {
        prompter = new Scanner(in);
    }

    /**
     * Prompt player for length of word to guess.
     * @return player input
     */
    public static int forWordLength() {
        System.out.println("Choose a word length: ");
        return nextValidInputBetween(2, MAX_WORD_LENGTH);
    }

    /**
     * Receives integer input from the player and checks if it is
     * positive and less than max.
     * @param min smallest int that the system will accept as input.
     * @param max largest int that the system will accept as input.
     * @return valid user input.
     */
    private static int nextValidInputBetween(int min, int max) {

        int userInput;
        while (!prompter.hasNextInt()
               || (userInput = prompter.nextInt()) < min
               || userInput > max) {
                System.out.printf("Enter a number between 0 and %d: ", max);
        }
        return userInput;
    }

    /**
     * Prompt player for the number of guesses.
     * @return player input
     */
    public static int forNumGuesses() {
        System.out.println("Choose number of guesses: ");
        return nextValidInputBetween(0, SIZE_ALPHABET);
    }

    /**
     * Prompt player to guess a letter.
     * @return player's guess.
     */
    public static String forGuess() {
        String guess;
        while (true) {
            System.out.printf("guess a letter: ");
            String playerInput = prompter.next();
            guess = String.valueOf(playerInput.charAt(0));
            if (playerInput.length() > 1){
                System.out.printf("The game selects the first character from your input."
                        + " Is '%s' your guess? [y|n] ", guess);
                String response = prompter.next();
                if (response.equals("y")) {
                    break;
                }
            } else if (!Game.ALPHABET.contains(guess)){
                System.out.println("Please enter a lowercase letter from the Latin alphabet.\n");
            } else {
                break;
            }
        }
        return guess;
    }
}
