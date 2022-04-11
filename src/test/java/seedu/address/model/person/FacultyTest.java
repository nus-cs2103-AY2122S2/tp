package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FacultyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Faculty(null));
    }

    @Test
    public void constructor_invalidFaculty_throwsIllegalArgumentException() {
        String invalidFaculty = "";
        assertThrows(IllegalArgumentException.class, () -> new Faculty(invalidFaculty));
    }

    @Test
    public void isValidFaculty() {
        // null address
        assertThrows(NullPointerException.class, () -> Faculty.isValidFaculty(null));

        // invalid faculties
        assertFalse(Faculty.isValidFaculty("")); // empty string
        assertFalse(Faculty.isValidFaculty(" ")); // spaces only
        assertFalse(Faculty.isValidFaculty("Sociology")); // invalid faculty
        assertFalse(Faculty.isValidFaculty(" Computing")); // whitespace before faculty string
        assertFalse(Faculty.isValidFaculty("C HS")); // whitespace between characters in string
        assertFalse(Faculty.isValidFaculty("Computing Business")); // two separate valid faculties
        assertFalse(Faculty.isValidFaculty("TA")); // role instead of faculty


        // valid faculties
        assertTrue(Faculty.isValidFaculty("Computing"));
        assertTrue(Faculty.isValidFaculty("cOMputing")); // different casing
        assertTrue(Faculty.isValidFaculty("CHS")); // short faculty
        assertTrue(Faculty.isValidFaculty("Others")); // test for others as a valid faculty

    }

    @Test
    public void testEqualsSymmetric() {
        Faculty x = new Faculty("Computing"); // equals and hashCode check name field value
        Faculty y = new Faculty("Computing");
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }
}
