import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ethan on 2/21/16.
 */
@RunWith(Parameterized.class)
public class GameTest {

    private int wordLength;
    private boolean playerWon;

    @Parameterized.Parameters(name = "value: {0}, list: {1}")
    public static Collection<Object []> data() {
        return Arrays.asList(new Object[][] {
                {1, "a\n", true},
                {2, "e\nb\nh\n", true},
                {2, "e\nz\nl\n", false}
        });
    }

    public GameTest(int wordLength, String guesses, boolean playerWon) {
        this.wordLength = wordLength;
        this.playerWon = playerWon;
        Prompt.setIn(new ByteArrayInputStream(guesses.getBytes()));
    }

    @Test
    public void testPlay() throws Exception {
        int numGuesses = 2;
        Game game = new Game(wordLength, numGuesses, "test-dictionary.txt");
        boolean playerWon = game.youWon();
        assertThat(playerWon, is(this.playerWon));
    }
}