package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {

    public static final String INT_MAX_STRING = String.format("%d", (long) Integer.MAX_VALUE);
    public static final String INT_MIN_STRING = String.format("%d", (long) Integer.MIN_VALUE);

    public static final String INT_MAX_PLUS_ONE_STRING = String.format("%d", (long) Integer.MAX_VALUE + 1);
    public static final String INT_MIN_MINUS_ONE_STRING = String.format("%d", (long) Integer.MIN_VALUE - 1);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(""));
        assertThrows(IllegalArgumentException.class, () -> new Grade(INT_MAX_PLUS_ONE_STRING));
        assertThrows(IllegalArgumentException.class, () -> new Grade(INT_MIN_MINUS_ONE_STRING));
        assertThrows(IllegalArgumentException.class, () -> new Grade("1.0"));
    }

    @Test
    public void constructor_defaultGrade_returnsGradeWithValueOne() {
        assertEquals(new Grade(1), new Grade());
    }

    @Test
    public void isValidGrade() {
        // null grade
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid grades
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only
        assertFalse(Grade.isValidGrade(INT_MAX_PLUS_ONE_STRING)); //MAX_INT + 1
        assertFalse(Grade.isValidGrade(INT_MIN_MINUS_ONE_STRING)); //MIN_INT - 1
        assertFalse(Grade.isValidGrade("0.0"));
        assertFalse(Grade.isValidGrade("1.0"));

        // valid grades
        assertTrue(Grade.isValidGrade("0"));
        assertTrue(Grade.isValidGrade("-1"));
        assertTrue(Grade.isValidGrade("1"));
        assertTrue(Grade.isValidGrade(INT_MAX_STRING));
        assertTrue(Grade.isValidGrade(INT_MIN_STRING));
    }

    @Test
    public void increment() {
        assertThrows(ArithmeticException.class, () -> new Grade(INT_MAX_STRING).increment());

        assertEquals(new Grade().increment(), new Grade(2));
        assertEquals(new Grade(100).increment(), new Grade(101));
        assertEquals(new Grade(0).increment(), new Grade(1));
        assertEquals(new Grade(-1).increment(), new Grade(0));
        assertEquals(new Grade(-1000).increment(), new Grade(-999));
    }
}
