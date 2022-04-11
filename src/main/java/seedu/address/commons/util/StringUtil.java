package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String[] wordsInPreppedSentence = sentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code searchValue}.
     *   Ignores case, but a full word/phrase match is required.
     * @param recipeValues cannot be null
     * @param searchValue cannot be null and cannot be empty
     */
    public static boolean recipeContainsIgnoreCase(String recipeValues, String searchValue) {
        requireNonNull(recipeValues);
        requireNonNull(searchValue);

        String preppedSearchValue = searchValue.trim().toLowerCase();
        checkArgument(!preppedSearchValue.isEmpty(), "Search value cannot be empty");

        return recipeValues.contains(preppedSearchValue);
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
     * @return true if the integer value of {@code s} is greater than 0.
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
     * @return true if the double value of {@code s} is greater than 0.
     */
    public static boolean isNonZeroPositiveDouble(String s) {
        requireNonNull(s);

        try {
            double value = Double.parseDouble(s);
            return value > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * @return true if {@code s} contains any special characters
     */
    public static boolean isAlphaNumeric(String s) {
        requireNonNull(s);

        Pattern special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher hasSpecial = special.matcher(s);

        return !hasSpecial.find();
    }
}
