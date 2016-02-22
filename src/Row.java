import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 2/21/16.
 * Row of matrix. Consists of a word and the families to which
 * that word belongs.
 * For example, the row for 'cat' is:
 * cat , -a-  ---  c--  ---  --- ...
 */
public class Row {
    public String word;
    private List<String> families;

    /**
     * Row constructor
     * @param word the word associated with the row
     */
    public Row(String word) {
        this.word = word;
        this.families = Game.ALPHABET.chars()
                .mapToObj(this::matchingChars)
                .collect(Collectors.toList());
    }

    /**
     * @param i int corresponding to a character
     * @return the family in the ith index of the row.
     * Used when comparing family memberships.
     */
    public String familyAt(int i) {
        return families.get(i);
    }

    /**
     * @param charList a String (but really a list) of characters
     * @return the word associated with this row,
     * with all letters blanked out except those in charList.
     * e.g. if charList = 'ct' and word is 'cat', this returns 'c-t'.
     */
    public String revealCharsIn(String charList) {
        return word
                // stream of letters (Strings) in word
                .chars().mapToObj(c -> String.valueOf((char) c))
                // replace letters not in charList with '_'
                .map(c -> charList.contains(c) ? c : "-")
                // convert into String
                .collect(Collectors.joining());
    }

    /**
     * Same as revealCharsIn but for a single character
     * @param c the character not to blank out.
     * @return all letters of word blanked out except for those matching c
     */
    public String matchingChars(int c) {
        return revealCharsIn(String.valueOf((char) c));
    }

    /**
     * @return word
     */
    public String getWord() {
        return word;
    }
}
