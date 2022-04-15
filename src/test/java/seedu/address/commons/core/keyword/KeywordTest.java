package seedu.address.commons.core.keyword;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllThrows;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStrings.DIGITS;
import static seedu.address.testutil.TypicalStrings.EMPTY_STRING;
import static seedu.address.testutil.TypicalStrings.LONG_STRING_50_CHAR;
import static seedu.address.testutil.TypicalStrings.LONG_STRING_63_CHAR;
import static seedu.address.testutil.TypicalStrings.NULL_STRING;
import static seedu.address.testutil.TypicalStrings.getInvalidStringsForKeyword;
import static seedu.address.testutil.TypicalStrings.getValidStringsForKeyword;

import org.junit.jupiter.api.Test;



public class KeywordTest {

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        // null case
        assertThrows(NullPointerException.class, () -> new Keyword(NULL_STRING));
    }

    @Test
    public void constructor_invalidKeyword_throwsIllegalArgumentException() {
        String[] invalidKeywords = getInvalidStringsForKeyword();
        assertAllThrows(IllegalArgumentException.class, Keyword::new, invalidKeywords);
    }

    @Test
    public void isValidDeadline() {

        // null case
        assertAllFalse(Keyword::isValidKeyword, NULL_STRING);

        // invalid keywords
        assertAllFalse(Keyword::isValidKeyword, getInvalidStringsForKeyword());

        // valid keywords
        assertAllTrue(Keyword::isValidKeyword, getValidStringsForKeyword());
    }

    @Test
    public void validateDeadline() {

        // null case
        assertThrows(NullPointerException.class, () -> new Keyword(NULL_STRING));

        // invalid keywords
        assertFalse(Keyword.isValidKeyword(EMPTY_STRING));
        String multipleKeywords = "HELLO HI";
        assertFalse(Keyword.isValidKeyword(multipleKeywords));

        // valid keywords
        assertTrue(Keyword.isValidKeyword(LONG_STRING_50_CHAR));
        assertTrue(Keyword.isValidKeyword(DIGITS));

    }

    @Test
    public void containsKeywordIgnorePunctuationAndCase() {

        // null case
        Keyword firstKeyword = new Keyword(LONG_STRING_50_CHAR);
        assertThrows(NullPointerException.class, () ->
                Keyword.containsKeywordIgnorePunctuationAndCase(null, firstKeyword));

        // Positive cases
        Keyword positiveKeyword = new Keyword("abc");
        String firstPositiveSentence = "ABc def";
        String secondPositiveSentence = "ABc, def";
        String thirdPositiveSentence = "ABc,def";
        assertTrue(Keyword.containsKeywordIgnorePunctuationAndCase(firstPositiveSentence, positiveKeyword));
        assertTrue(Keyword.containsKeywordIgnorePunctuationAndCase(secondPositiveSentence, positiveKeyword));
        assertTrue(Keyword.containsKeywordIgnorePunctuationAndCase(thirdPositiveSentence, positiveKeyword));

        // Negative cases
        Keyword negativeKeyword = new Keyword("abcdef");
        String firstNegativeSentence = "ABc-def";
        String secondNegativeSentence = "ABc def";
        assertFalse(Keyword.containsKeywordIgnorePunctuationAndCase(firstNegativeSentence, negativeKeyword));
        assertFalse(Keyword.containsKeywordIgnorePunctuationAndCase(secondNegativeSentence, negativeKeyword));
    }

    @Test
    public void equals() {

        Keyword firstKeyword = new Keyword(LONG_STRING_63_CHAR);
        Keyword secondKeyword = new Keyword(LONG_STRING_50_CHAR);

        // same object -> returns true
        assertTrue(firstKeyword.equals(firstKeyword));

        // same values -> returns true
        Keyword firstKeywordCopy = new Keyword(LONG_STRING_63_CHAR);
        assertTrue(firstKeyword.equals(firstKeywordCopy));

        // different types -> returns false
        assertFalse(firstKeyword.equals(1));

        // null -> returns false
        assertFalse(firstKeyword.equals(null));

        // different keyword -> returns false
        assertFalse(firstKeyword.equals(secondKeyword));
    }

}
