package seedu.ibook.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }

    //---------------- Tests for isNonZeroUnsignedCompoundInteger ----------------------------

    @Test
    public void isNonZeroUnsignedCompoundInteger_doesNotContainHyphen_returnFalse() {
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(""));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("  "));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("aa"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1a"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("11"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1 1"));
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_emptyParts_returnFalse() {
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(" -"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(" -"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("- "));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(" - "));
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_nonNumbers_returnFalse() {
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("a-1"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1-a"));
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_containsZero_nonZeroValuesReturnTrue() {

        // value is zero
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("0-2"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("2-0"));

        // zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("01-2"));
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("1-02"));
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("01-02"));
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("01-02"));

        // number contains zero digit
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("10-20"));
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_signed_returnFalse() {
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("-1-2"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("+1-2"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1-+2"));
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1--2"));
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_containsWhitespace_returnFalse() {
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(" 1-2")); // Leading spaces
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1-2 ")); // Trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1 -2")); // Spaces in the middle
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger("1- 2")); // Spaces in the middle
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_numberExceedingInteger_returnFalse() {
        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(
                String.format("%s-1", Long.toString(Integer.MAX_VALUE + 1))));

        assertFalse(StringUtil.isNonZeroUnsignedCompoundInteger(
                String.format("1-%s", Long.toString(Integer.MAX_VALUE + 1))));
    }

    @Test
    public void isNonZeroUnsignedCompoundInteger_validCompoundInteger_returnTrue() {
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("1-2"));
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("3-10"));
        assertTrue(StringUtil.isNonZeroUnsignedCompoundInteger("3-1"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
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
