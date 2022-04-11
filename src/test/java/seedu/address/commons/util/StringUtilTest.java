package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.label.Label;
import seedu.address.model.person.Address;

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

    //---------------- Tests for containsWordIgnoreCaseInMap --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCaseInMap_nullWord_throwsNullPointerException() {
        HashMap<Label, Address> typicalSentence = new HashMap<>();
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCaseInMap(typicalSentence,
                null));
    }

    @Test
    public void containsWordIgnoreCaseInMap_emptyWord_throwsIllegalArgumentException() {
        HashMap<Label, Address> typicalSentence = new HashMap<>();
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "   "));
    }

    @Test
    public void containsWordIgnoreCaseInMap_multipleWords_throwsIllegalArgumentException() {
        HashMap<Label, Address> typicalSentence = new HashMap<>();;
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCaseInMap_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCaseInMap(null, "abc"));
    }

    //---------------- Tests for containsWordIgnoreCaseInSet --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCaseInSet_nullWord_throwsNullPointerException() {
        HashSet<String> typicalSentence = new HashSet<>();
        typicalSentence.add("adam");
        typicalSentence.add("eve");
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCaseInSet(typicalSentence,
                null));
    }

    @Test
    public void containsWordIgnoreCaseInSet_emptyWord_throwsIllegalArgumentException() {
        HashSet<String> typicalSentence = new HashSet<>();
        typicalSentence.add("adam");
        typicalSentence.add("eve");
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "   "));
    }

    @Test
    public void containsWordIgnoreCaseInSet_multipleWords_throwsIllegalArgumentException() {
        HashSet<String> typicalSentence = new HashSet<>();
        typicalSentence.add("adam");
        typicalSentence.add("eve");
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "aaaa BBB"));
    }

    @Test
    public void containsWordIgnoreCaseInSet_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCaseInSet(null, "abc"));
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

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty set
     *   - one string in set
     *   - multiple strings in set
     *   - strings with extra spaces in set
     *   - empty string in set
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
    public void containsWordIgnoreCaseInSet_validInputs_correctResult() {
        HashSet<String> typicalSentence = new HashSet<>();

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "abc")); // Boundary case
        typicalSentence.add("        ");
        assertFalse(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "123"));

        // Matches a partial word only
        typicalSentence.clear();
        typicalSentence.add("Gigi Hadid");
        assertFalse(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "gig"));
        assertFalse(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "gigigigi"));

        // Matches word in the sentence, different upper/lower case letters
        typicalSentence.add("Bella");
        assertTrue(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "bElLa")); // Mixed case
        typicalSentence.add(" Lola    Charlie");
        // String in sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "Lola"));
        typicalSentence.clear();
        typicalSentence.add("gigi");
        assertTrue(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "GiGi")); // Only one word in sentence
        // (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "  gigi  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        typicalSentence.add("gigi hadid");
        assertTrue(StringUtil.containsWordIgnoreCaseInSet(typicalSentence, "gigi"));
    }

    @Test
    public void containsWordIgnoreCaseInMap_validInputs_correctResult() {
        HashMap<Label, String> typicalSentence = new HashMap<>();

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "abc")); // Boundary case

        Label label = new Label("Label#1", false);
        Label label2 = new Label("Label#2", false);
        Label label3 = new Label("Label#3", false);
        typicalSentence.put(label, "Gigi");
        assertFalse(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "123"));

        //Matches a partial word only
        typicalSentence.clear();
        typicalSentence.put(label, "Gigi Hadid");
        assertFalse(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "gig"));
        assertFalse(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "gigigigi"));

        // Matches word in the sentence, different upper/lower case letters
        typicalSentence.put(label2, "Bella");
        assertTrue(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "bElLa")); // Mixed case
        typicalSentence.put(label3, " Lola    Charlie");
        // String in sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "Lola"));
        typicalSentence.clear();
        typicalSentence.put(label, "gigi");
        assertTrue(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "GiGi")); // Only one word in sentence
        // (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "  gigi  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        typicalSentence.put(label2, "gigi hadid");
        assertTrue(StringUtil.containsWordIgnoreCaseInMap(typicalSentence, "gigi"));
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
