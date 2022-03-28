package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidCourse_throwsIllegalArgumentException() {
        String invalidCourse = "Math@";
        assertThrows(IllegalArgumentException.class, () -> new Course(invalidCourse));
    }

    @Test
    public void isValidCourse() {
        // null course
        assertThrows(NullPointerException.class, () -> Course.isValidCourse(null));

        // invalid course
        assertFalse(Course.isValidCourse("@")); // symbols
        assertFalse(Course.isValidCourse("123")); // spaces only

        // valid course
        assertTrue(Course.isValidCourse("")); // blank spaces (after trimmed)
        assertTrue(Course.isValidCourse("Math"));
        assertTrue(Course.isValidCourse("a")); // one character
        assertTrue(Course.isValidCourse("Computer Science and Mathematics")); // long course
    }
}
