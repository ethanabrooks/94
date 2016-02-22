import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Created by Ethan on 2/19/16.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // construct data structure
        int wordLength = Prompt.forWordLength();
        int remainingGuesses = Prompt.forNumGuesses();
        printBlankWord(wordLength);
        Game game = new Game(wordLength, remainingGuesses, "dictionary.txt");
        if (game.youWon()) {
            System.out.println("Victory never tasted so sweet!");
        }
        else {
            System.out.println("You lost! No one beats the evil hangman!!!");
        }
    }

    /**
     * Prints blanks representing word to be guessed.
     * @param wordLength length of word.
     */
    private static void printBlankWord(int wordLength) {
        IntStream.range(0, wordLength)
                .forEach(i -> System.out.printf("-"));
    }
}