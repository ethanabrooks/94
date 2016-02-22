import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ethan on 2/21/16.
 */
@RunWith(Parameterized.class)
public class MatrixTest {


    @Parameterized.Parameters(name = "value: {0}, list: {1}")
    public static Collection<Object []> data() {
        return Arrays.asList(new Object[][] {
                {'z', "-", Collections.singletonList("a")},
                {'a', "a", Collections.singletonList("a")},
                {'e', "-e", Arrays.asList("be", "he")},
                {'o', "--", Arrays.asList("be", "he")}
        });
    }

    private Matrix matrix;
    private int i;
    private String family;
    private final List<String> wordPossibilities;

    public MatrixTest(char guess, String family, List<String> wordPossibilities)
            throws
            IOException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException
    {
        this.family = family;
        this.wordPossibilities = wordPossibilities;
        int length = family.replace(" ", "").length();
        matrix = new Matrix("test-dictionary.txt", length);

        final String alphabet = Game.ALPHABET;
        Map<Character, Integer> charToIndex = IntStream
                .range(0, alphabet.length()).mapToObj(i -> i)
                .collect(Collectors.toMap(alphabet::charAt, i -> i));

        i = charToIndex.get(guess);
    }

    @Test
    public void testChooseWordFamily() throws Exception {
        String chosenFamily = matrix.chooseWordFamily(i);
        assertThat(chosenFamily, is(family));
    }

    @Test
    public void testFilterFor() throws Exception {
        matrix.filterFor(family, i);
        assertThat(matrix.wordPossibiilities(), is(wordPossibilities));
    }

}