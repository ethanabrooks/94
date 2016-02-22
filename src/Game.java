import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ethan on 2/21/16.
 * Runs the game
 */
public class Game {
    private int remainingGuesses;
    private Matrix dictionary;
    private StringBuilder guesses = new StringBuilder();
    public static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";


    /**
     * Game constructor
     * @param wordLength length of word to guess
     * @param numGuesses number of guesses before the player loses
     * @param dictionaryFile relative path to file containing list of possible words
     * @throws IOException
     */
    public Game(int wordLength, int numGuesses, String dictionaryFile) throws IOException {
        this.remainingGuesses = numGuesses;
        this.dictionary = new Matrix(dictionaryFile, wordLength);
    }

    /**
     * Convert letter into an int such that a -> 1, b -> 2, etc.
     * @param guess single character string
     * @return the index of the character in the alphabet
     */
    private static int charToIndex(String guess) {
        return ALPHABET.indexOf(guess);
    }

    /**
     * Play the game.
     * @return true if you won, otherwise false.
     */
    public boolean youWon() throws InterruptedException {
        while(remainingGuesses > 0) {
            printGameStatus();
            String guess = Prompt.forGuess();
            int i = charToIndex(guess);
            guesses.append(guess).append(" ");
            // identify most common word family
            String family = dictionary.chooseWordFamily(i);
            if (!family.contains(guess)) {
                remainingGuesses--;
            }
            // remove all words not belonging to family
            dictionary.filterFor(family, i);

            // check winning condition
            if (dictionary.downToOneWord()) {
                String lastWord = dictionary.getRow().getWord();
                String guesses = this.guesses.toString();
                boolean guessedAllLetters = lastWord.chars()
                        .mapToObj(c -> (char) c)
                        .map(String::valueOf)
                        .allMatch(guesses::contains);
                if (guessedAllLetters) {
                    System.out.println("You guessed it! The word is...");
                    System.out.println("**drum roll**");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(lastWord);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Print the players guesses, remaining guesses, and revealed letters
     */
    private void printGameStatus() {
        System.out.printf("Guesses: %s\n", guesses);
        System.out.printf("Remaining guesses = %d\n", remainingGuesses);
        // Reveal guessed letters
        System.out.println(dictionary.getRow().revealCharsIn(guesses.toString()));
    }
}
