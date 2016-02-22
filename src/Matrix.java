import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 2/20/16.
 * The matrix class is built around a mathematical matrix expressed as
 * an ArrayList of rows connected in a Linked List. Each row is
 * associated with a word and a list of families. For example:
 *       a    b    c    d    e   ...
 * cat | -a-  ---  c--  ---  --- ...
 * ace | a--  ---  -c-  ---  --e ...
 * ...
 *
 * The reason I used this data structure is that it allows the system to
 * quickly compare rows based on the player's guess -- for example,
 * if the player guesses 'e', we can easily compare all the families
 * under 'e' (the 5th column) because accessing the 5th element of
 * an ArrayList is O(1).
 *
 * A linked list also allows us to remove elements from the middle in O(1).
 * This is necessary because once we have chosen the most common family,
 * we need to exclude all words that do not belong to that family.
 */
public class Matrix {
    private LinkedList<Row> matrix = new LinkedList<>();

    /**
     * Matrix constructor
     * @param filename path to
     * @param wordLength
     * @throws IOException
     */
    public Matrix(String filename, int wordLength) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(filename));
        String word;
        while ((word = file.readLine()) != null) {
            if (word.length() == wordLength) {
                matrix.add(new Row(word));
            }
        }
    }

    /**
     * @param i int corresponding to guessed letter
     * @return a HashMap from families to the size (membership) of the family
     */
    private Map<String, Integer> countMembership(int i) {
        Map<String, Integer> membership = new HashMap<>();
        matrix.stream().forEach(row -> {
            // family that corresponds to guessed character
            String family = row.familyAt(i);
            // Get family's membership size if family is already in hash
            int numMembers = membership.containsKey(family)? membership.get(family) : 0;
            // If present, increment. Otherwise add new.
            membership.put(family, numMembers + 1);
        });
        return membership;
    }

    /**
     * @param i int corresponding to guess
     * @return family with the largest membership
     */
    public String chooseWordFamily(int i) {
        Map<String, Integer> membership = countMembership(i);
        return Collections.max(membership.keySet(),
                // families are ordered by the size of their membership
                (fam1, fam2) -> membership.get(fam1) - membership.get(fam2));
    }

    /**
     * Remove all words from the matrix that do not belong to the matrix.
     * @param family String with dashes for letters that do not correspond
     *               to the guess e.g. if the guess was 'a', then 'cat' would
     *               belong to the family '-a-'.
     * @param i int corresponding to guess
     */
    public void filterFor(String family, int i) {
        matrix.stream()
                .filter(row -> !row.familyAt(i).equals(family))
                 // save to temporary list so that we can modify matrix
                .collect(Collectors.toList()).stream()
                .forEach(matrix::remove);
    }

    /**
     * @return list of words that the evil hangman can still choose from,
     * given the player's guesses.
     */
    public List<String> wordPossibiilities() {
        return matrix.stream()
                .map(Row::getWord)
                .collect(Collectors.toList());
    }

    /**
     * @return true if the matrix has one row.
     */
    public boolean downToOneWord() {
        return matrix.size() == 1;
    }

    /**
     * @return the first row in the matrix.
     */
    public Row getRow() {
        return matrix.get(0);
    }
}
