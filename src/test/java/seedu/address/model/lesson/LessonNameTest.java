package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonNameTest {

    @Test
    public void isValidLessonName() {
        assertTrue(LessonName.isValidName("BIOLOGY")); // upper-case letters
        assertTrue(LessonName.isValidName("biology")); // lower-case letters
        assertTrue(LessonName.isValidName("1301")); // all numbers
        assertTrue(LessonName.isValidName("Biology1301")); // alphanumeric
        assertTrue(LessonName.isValidName("Biology 1301")); // alphanumeric w space
        assertTrue(LessonName.isValidName("9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWX")); // 50 characters
        assertTrue(LessonName.isValidName("99999999999999999999999999999999999999999999999999")); // 50 numbers
        assertTrue(LessonName.isValidName("999999999999999999999ABC9999999999999999999"
                + "9999999")); // 50 alphanumeric
        assertTrue(LessonName.isValidName("999999999999999999999ABC9"
                + " 999999999999999999999999")); // 50 alphanumeric w space

    }

    @Test
    public void isInvalidLessonName() {
        assertThrows(NullPointerException.class, () -> LessonName.isValidName(null)); // null lesson name
        assertFalse(LessonName.isValidName("")); // empty lesson name
        assertFalse(LessonName.isValidName(" ")); // whitespaces only
        assertFalse(LessonName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(LessonName.isValidName("peter*")); // contains non-alphanumeric characters

        assertFalse(LessonName.isValidName("ABCDEFGHIJKLMNOPQRSTUVWYXZabcdefghijklmnopqrstuvwxy")); // 51 chars
        assertFalse(LessonName.isValidName("ABCDEFGHIJKLMNOPQRSTUVWY "
                + "XZabcdefghijklmnopqrstuvwx")); // 51 chars inc. whitespace
        assertFalse(LessonName.isValidName("ABCDEFGHIJKLMNOPQRSTUVWYXZabcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWYXZabcdefghijklmnopqrstuvwxyz")); // 104 chars
    }
}
