package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code keys}.
     *   Ignores case, but white space in {@code keys} must match {@code sentence}'s.
     *   <br>examples:<pre>
     *       containsPartialSentenceIgnoreCase("ABc def", "abc") == true
     *       containsPartialSentenceIgnoreCase("ABc def", "abc d") == true
     *       containsPartialSentenceIgnoreCase("ABc def", "abcd") == false // whitespace does not tally
     *       containsPartialSentenceIgnoreCase("ABc def", "abc defg") == false // not a substring
     *       </pre>
     * @param sentence cannot be null
     * @param keys cannot be null, cannot be empty
     */
    public static boolean containsPartialSentenceIgnoreCase(String sentence, String keys) {
        requireNonNull(sentence);
        requireNonNull(keys);

        checkArgument(!keys.isBlank(), "Keys cannot be empty");
        String capitalizedName = sentence.toUpperCase().trim();
        String capitalizedKeys = keys.toUpperCase().trim();
        return capitalizedName.contains(capitalizedKeys);
    }

    /**
     * Returns true if the {@code sentence1} contains the {@code sentence2}.
     *   Ignores case, but a full sentence match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc def") == true
     *       containsWordIgnoreCase("ABc def", "abc DEF") == true
     *       containsWordIgnoreCase("ABc def", "ABc") == false //not a full sentence match
     *       </pre>
     * @param sentence1 cannot be null
     * @param sentence2 cannot be null, cannot be empty
     */
    public static boolean containsFullSentenceIgnoreCase(String sentence1, String sentence2) {
        requireNonNull(sentence1);
        requireNonNull(sentence2);

        checkArgument(!sentence2.isEmpty(), "sentence2 parameter cannot be empty");
        return sentence1.equalsIgnoreCase(sentence2);
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
     * Returns true if {@code s} represents a nonZero unsigned integer that is less than
     * or equal to {@code Integer.MAX_VALUE}
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit(String s) {
        try {
            BigInteger parsedInteger = new BigInteger(s);
            Integer maxInt = Integer.MAX_VALUE;
            String maxIntString = maxInt.toString();
            if (parsedInteger.compareTo(new BigInteger(maxIntString)) == 1) { //check for overflow
                return false;
            }
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
