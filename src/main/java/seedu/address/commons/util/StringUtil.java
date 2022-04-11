package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Returns true if any word in {@code sentence1} matches with any word
     * from {@code sentence2}.
     * <br>examples:<pre>
     *      *       containsWordIgnoreCaseForTwoSentence("ABc def", "abc") == true
     *      *       containsWordIgnoreCaseForTwoSentence("ABc def", "DEF DEF") == true
     *      *       containsWordIgnoreCaseForTwoSentence("ABc def", "AB de") == false //not a full word match
     *      *       </pre>
     *
     * @param sentence1 Cannot be null.
     * @param sentence2 Cannot be null.
     * @return Result of the check.
     */
    public static boolean containsWordIgnoreCaseForTwoSentence(String sentence1, String sentence2) {
        requireNonNull(sentence1);
        requireNonNull(sentence2);

        // Split sentence by white space.
        String[] wordsInPreppedSentence1 = sentence1.split("\\s+");
        String[] wordsInPreppedSentence2 = sentence2.split("\\s+");
        return Arrays.stream(wordsInPreppedSentence1)
                .anyMatch(word -> Arrays.stream(wordsInPreppedSentence2).anyMatch(word::equalsIgnoreCase));
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
     * Removes all the white space from the given string.
     *
     * @param str String for white space removal.
     * @return A string that has its whitespaces completely removed.
     */
    public static String removeSpaces(String str) {
        return str.replace(" ", "");
    }

    /**
     * Removes all the white space in each of the input string in the list.
     *
     * @param userList A list of string that is targeted for white space removal.
     * @return A list that has no whitespace for each of the strings.
     */
    public static List<String> removeSpaces(List<String> userList) {
        List<String> resultList = new ArrayList<>();
        userList.forEach(inputStr -> resultList.add(inputStr.replace(" ", "")));
        return resultList;
    }

    /**
     * Replaces 2 or more consecutive whitespaces between words with a single whitespace.
     *
     * @param str String to be trimmed.
     * @return Trimmed string with extra whitespaces removed.
     */
    public static String trimExtraWhiteSpaces(String str) {
        return str.replaceAll("\\s{2,}", " ");
    }
}
