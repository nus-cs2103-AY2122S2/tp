package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, and a partial or full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true //a full word match
     *       containsWordIgnoreCase("ABc def", "DEF") == true //a full word match
     *       containsWordIgnoreCase("ABc def", "AB") == true //a partial word match
     *       containsWordIgnoreCase("AbC def", "AcB") == false //no match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1,
                "Word parameter should be a single word");

        String preppedSentence = sentence.toLowerCase();
        String preppedKeyword = preppedWord.toLowerCase();

        return preppedSentence.contains(preppedKeyword);
    }

    /**
     * Returns true if the {@code tagString} contains the {@code word}.
     *   Ignores case, and a partial or full word match is required.
     *   <br>examples:<pre>
     *       tagContainsWordIgnoreCase("ABc def", "abc") == true //a full word match
     *       tagContainsWordIgnoreCase("ABc def", "DEF") == true //a full word match
     *       tagContainsWordIgnoreCase("ABc def", "Abc Def") == true //a full word match
     *       tagContainsWordIgnoreCase("ABc def", "AB") == true //a partial word match
     *       tagContainsWordIgnoreCase("ABc def", "ABc de") == true //a partial word match
     *       tagContainsWordIgnoreCase("ABc def", "AB de") == true //a partial word match
     *       tagContainsWordIgnoreCase("AbC def", "AcB") == false //no match
     *       </pre>
     * @param ownerName cannot be null
     * @param word cannot be null, cannot be empty
     */
    public static boolean nameContainsWordsIgnoreCase(String ownerName, String word) {
        requireNonNull(ownerName);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Filter word parameter cannot be empty");

        String preppedSentence = ownerName.toLowerCase();
        String preppedKeyword = preppedWord.toLowerCase();

        if (!preppedSentence.contains(preppedKeyword)) {
            String[] separateSentence = preppedSentence.split("\\s+");
            String[] separateKeyword = preppedKeyword.split("\\s+");

            int minLength = Math.min(separateKeyword.length, separateSentence.length);

            for (int i = 0; i < minLength; i++) {
                if (!separateSentence[i].contains(separateKeyword[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the {@code tagString} contains the {@code word}.
     *   Ignores case, and a partial or full word match is required.
     *   <br>examples:<pre>
     *       tagContainsWordIgnoreCase("ABc def", "abc") == true //a full word match
     *       tagContainsWordIgnoreCase("ABc def", "DEF") == true //a full word match
     *       tagContainsWordIgnoreCase("ABc def", "Abc Def") == true //a full word match
     *       tagContainsWordIgnoreCase("ABc def", "AB") == true //a partial word match
     *       tagContainsWordIgnoreCase("ABc def", "ABc de") == true //a partial word match
     *       tagContainsWordIgnoreCase("ABc def", "AB de") == true //a partial word match
     *       tagContainsWordIgnoreCase("AbC def", "AcB") == false //no match
     *       </pre>
     * @param tagString cannot be null
     * @param word cannot be null, cannot be empty
     */
    public static boolean tagContainsWordsIgnoreCase(String tagString, String word) {
        requireNonNull(tagString);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Filter word parameter cannot be empty");

        String preppedSentence = tagString.toLowerCase();
        String preppedKeyword = preppedWord.toLowerCase();

        if (!preppedSentence.contains(preppedKeyword)) {
            String[] separateSentence = preppedSentence.split("\\s+");
            String[] separateKeyword = preppedKeyword.split("\\s+");

            int minLength = Math.min(separateKeyword.length, separateSentence.length);

            for (int i = 0; i < minLength; i++) {
                if (!separateSentence[i].contains(separateKeyword[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
