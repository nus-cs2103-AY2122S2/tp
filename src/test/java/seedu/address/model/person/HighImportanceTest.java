package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class HighImportanceTest {

    @Test
    void valueOf_invalidBooleanString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> HighImportance.valueOf("NOT_BOOLEAN"));
    }

    @Test
    void isValidHighImportance() {
        // null high importance value
        assertThrows(NullPointerException.class, () -> HighImportance.isValidHighImportance(null));

        // invalid high importance values
        assertFalse(HighImportance.isValidHighImportance("")); // empty string
        assertFalse(HighImportance.isValidHighImportance(" ")); // whitespaces only
        assertFalse(HighImportance.isValidHighImportance("no")); // non-boolean
        assertFalse(HighImportance.isValidHighImportance("yes")); // non-boolean
        assertFalse(HighImportance.isValidHighImportance("0")); // binary digit
        assertFalse(HighImportance.isValidHighImportance("1")); // binary digit
        assertFalse(HighImportance.isValidHighImportance("False")); // capitalized boolean string
        assertFalse(HighImportance.isValidHighImportance("True")); // capitalized boolean string
        assertFalse(HighImportance.isValidHighImportance("FALSE")); // capitalized boolean string
        assertFalse(HighImportance.isValidHighImportance("TRUE")); // capitalized boolean string

        // valid high importance values
        assertTrue(HighImportance.isValidHighImportance("false"));
        assertTrue(HighImportance.isValidHighImportance("true"));
    }

    @Test
    void hasHighImportance() {
        // not high importance
        assertFalse(HighImportance.valueOf("false").hasHighImportance());

        // not high importance
        assertTrue(HighImportance.valueOf("true").hasHighImportance());
    }
}
