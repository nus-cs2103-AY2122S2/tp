package seedu.address.commons.core.keyword;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isSameStringIgnoreCases;

import java.util.Arrays;

/**
 * Represents the keywords used in {@code FindCommand}.
 */
public class Keyword {

    public static final String MESSAGE_CONSTRAINTS = "A keyword can't be empty, and only alphanumeric characters"
            + " (letters and digits) are allowed in a keyword, and a keyword should be no longer than 63 characters.";

    /*
     * The first character of the name must not be a whitespace,
     * and only alphanumeric characters are allowed.
     * The maximum number of characters allowed is 63.
     */
    private static final String VALIDATION_REGEX = "\\p{Alnum}{1,63}";

    private final String value;

    /**
     * Constructs a keyword.
     * The whitespaces around {@code value} are trimmed.
     *
     * @param value the {@code String} that represents a keyword
     */
    public Keyword(String value) {
        validateKeyword(value);
        this.value = value.trim();
    }

    public String getValue() {
        return this.value;
    }



    /**
     * Validates a keyword. A keyword should be alphanumeric, and its length should be 1 to 63.
     *
     * @param keyword the keyword to be checked
     */
    private static void validateKeyword(String keyword) {

        requireNonNull(keyword);
        String trimmedKeyword = keyword.trim();

        // If the regex is matched, it ensures that there's only one keyword.
        checkArgument(trimmedKeyword.matches(VALIDATION_REGEX), MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks the validity of a keyword.
     *
     * @param input the input to be checked
     * @return true if the {@code input} represents a valid {@code keyword}
     */
    public static boolean isValidKeyword(String input) {
        if (input == null) {
            return false;
        }
        return input.trim().matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Keyword // instanceof handles nulls
                && isSameStringIgnoreCases(value, ((Keyword) other).value)); // state check
    }

    @Override
    public int hashCode() {
        requireNonNull(this.value);
        return value.toLowerCase().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case and punctuations, but a full match is required. However, if a word is separated by punctuation(s),
     *   it is split by the punctuations.
     *   <br>examples:<pre>
     *       containsWordIgnorePunctuationAndCase("ABc def", "abc") == true
     *       containsWordIgnorePunctuationAndCase("ABc, def", "abc") == true
     *       containsWordIgnorePunctuationAndCase("ABc,def", "abc") == true
     *       containsWordIgnorePunctuationAndCase("ABc-def", "abcdef") == false // "ABc-def" is split into two words
     *       containsWordIgnorePunctuationAndCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param keyword a {@code Keyword} to be found in the {@code sentence}
     */
    public static boolean containsKeywordIgnorePunctuationAndCase(String sentence, Keyword keyword) {
        requireNonNull(sentence);

        String preppedWord = keyword.getValue();
        return Arrays.stream(sentence.split("[^\\p{Alnum}]+")).anyMatch(preppedWord::equalsIgnoreCase);
    }

}
