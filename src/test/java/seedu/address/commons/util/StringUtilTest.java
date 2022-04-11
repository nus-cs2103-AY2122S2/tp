package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("")); // Boundary value
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("a"));
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("-1"));
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit(
            Long.toString(Integer.MAX_VALUE + 1)));

        // EP: number equal to Integer.MAX_VALUE
        assertTrue(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit(Long.toString(Integer.MAX_VALUE)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit("10"));
    }


    //---------------- Tests for containsPartialSentenceIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for keys: null, empty
     * Invalid equivalence partitions for sentence: null
     * The three test cases below test one invalid input at a time.
     */

    @Test
    public void containsPartialSentenceIgnoreCase_nullKeys_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> StringUtil.containsPartialSentenceIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsPartialSentenceIgnoreCase_emptyKeys_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Keys cannot be empty", ()
            -> StringUtil.containsPartialSentenceIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsPartialSentenceIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> StringUtil.containsPartialSentenceIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for keys:
     *   - contain symbols/numbers
     *   - contain leading/trailing spaces
     *   - one word
     *   - multiple words
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - contain leading/trailing spaces
     *
     * Possible scenarios returning true:
     *   - key fully matches first word in sentence
     *   - key partially matches first word in sentence
     *   - key matches sentence fully
     *   - key matches first 3 words of sentence fully and last word of sentence partially
     *
     * Possible scenarios returning false:
     *   - key is longer than sentence
     *   - key is not a substring of sentence
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsPartialSentenceIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("    ", "123"));

        // Key matches sentences fully
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa", "aaa")); // one word (boundary)
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bBb cc", "aaa bBb cc")); // Multiple

        // Key matches sentences partially
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa", "aa")); // one word (boundary)
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bbb ccc", "bb")); // partial match
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bbb ccc", "aaa bbb c")); // Multiple

        // Key matches sentence partially, different upper/lower case letters
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bBb ccc", "AAA")); // First word
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bBb ccc", "CCc")); // Last word
        // Keys with numerals and symbols
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa ccc@1", "@1"));
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("Aaa", "aaa")); // one word (boundary)
        // Leading/trailing spaces in keys
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bbb ccc", "  ccc  "));
        // Leading/trailing spaces in sentence
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase(" aaa bBb cc ", "AAA bbb cc"));

        // Key matches sentence fully, different upper/lower case letters
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("aaa bBb cc", "AAA bbb cc"));

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsPartialSentenceIgnoreCase("AAA bBb ccc  bbb", "bbB"));

        // Key unable to match sentence
        // Keyword longer than sentence
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("aaa", "aaaa"));
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("aaa", "aaa bb")); // Extra keywords
        // Whitespace does not tally
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("aaa bbb", "aaab bb"));
        // Different sequence
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("AAA bbb cc", "AAA cc bbb"));
        // Different sequence
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("AAA bbb cc", "cc AAA"));
        // Key is not a substring
        assertFalse(StringUtil.containsPartialSentenceIgnoreCase("aaa bb", "cc"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
