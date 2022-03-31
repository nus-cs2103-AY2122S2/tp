package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for Faculty class and its methods.
 */
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
        //null faculty
        assertFalse(Faculty.isValidFaculty(null));

        //invalid faculty
        assertFalse(Faculty.isValidFaculty("")); // empty string
        assertFalse(Faculty.isValidFaculty(" ")); // spaces only
        assertFalse(Faculty.isValidFaculty("SOOC")); // incorrect faculty

        //Valid faculty
        assertTrue(Faculty.isValidFaculty("soc")); // all lowercase, correct faculty
        assertTrue(Faculty.isValidFaculty("SoC")); // correct faculty
        assertTrue(Faculty.isValidFaculty("SOC")); // all uppercase, correct faculty
    }

    @Test
    public void getFacultyEnumAsString() {
        String listOfFaculties = "FASS BIZ SOC SCALE FOD CDE DUKE FOL YLLSOM YSTCOM SOPP LKYSPP SPH FOS ";
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(Faculty.Nus.values()).forEach(faculty -> stringBuilder.append(faculty + " "));
        assertEquals(listOfFaculties, stringBuilder.toString());
    }
}
