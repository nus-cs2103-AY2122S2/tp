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
        assertTrue(LessonName.isValidName("9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWX")); // 50 letters
        assertTrue(LessonName.isValidName("99999999999999999999991239999999999999999999999999")); // 50 numbers

    }

    @Test
    public void isInvalidLessonName() {
        assertThrows(NullPointerException.class, () -> LessonName.isValidName(null)); // null lesson name
        assertFalse(LessonName.isValidName("")); // empty lesson name
        assertFalse(LessonName.isValidName(" ")); // whitespaces only
        assertFalse(LessonName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(LessonName.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(LessonName.isValidName("999999999999999999999912399999999999999999999999991")); // 51 numbers
        assertFalse(LessonName.isValidName("9abcdefghijklmnopqrstuvwxyzA1CDEFGH"
                + "IJKLMNO3QRSTUVWX")); // 51 alphanumeric
        assertFalse(LessonName.isValidName("9abcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWX"
                + "9abcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWX")); // 102 alphanumeric



    }
}
