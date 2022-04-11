package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code string} contains the {@code substring}.
     *   Ignores case, a full substring match is required.
     *
     * @param string cannot be null
     * @param substring cannot be null, cannot be empty
     */
    public static boolean containsSubstringIgnoreCase(String string, String substring) {
        requireNonNull(string);
        requireNonNull(substring);

        String preppedSubstring = substring.trim();
        checkArgument(!preppedSubstring.isEmpty(), "Substring parameter cannot be empty");

        return string.contains(preppedSubstring);
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

    /**
     * Returns true if {@code s} contains multiple entries, does not check for validness.
     * Returns false if {@code s} is only a single entry.
     * e.g. "1", "2" returns false, "1 2 3", "5    10" (multiple whitespaces) returns true
     *
     * @param s trimmed string of arguments
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean containsMultipleIndex(String s) {
        requireNonNull(s);
        String[] indexes = s.split(" ");

        return indexes.length != 1;
    }

    /**
     * Returns true if {@code s} contains multiple all unique entries (integers), does not check for validness.
     * Returns false if {@code s} contains duplicate entries (integers).
     * e.g.:
     * "1 1", "1 2 1 3" returns false
     * "1 2 3", "5 10 2 3" (multiple whitespaces) returns true
     *
     * @param s trimmed string of arguments
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isAllUniqueIntegers(String s) {
        requireNonNull(s);
        String[] indexes = s.split(" ");
        HashSet<Integer> hashSet = new HashSet<>();
        for (String t : indexes) {
            int n = Integer.parseInt(t);
            if (!hashSet.contains(n)) {
                hashSet.add(n);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if every entry in {@code s} represents a non-zero unsigned integer
     * e.g. "5 6 7 8"
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "1 a" (contains letters)
     * "1, 2 3" (contains comma), "1   2 3" (multiple whitespaces between adjacent integers)
     *
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isAllNonZeroUnsignedInteger(String s) {
        requireNonNull(s);
        String[] indexes = s.split(" ");
        boolean result = true;

        for (String t : indexes) {
            result = result && isNonZeroUnsignedInteger(t);
        }
        return result;
    }
}
