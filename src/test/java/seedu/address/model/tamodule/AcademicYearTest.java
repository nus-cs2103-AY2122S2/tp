package seedu.address.model.tamodule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AcademicYearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AcademicYear(null));
    }

    @Test
    public void constructor_invalidAcademicYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AcademicYear(""));
        assertThrows(IllegalArgumentException.class, () -> new AcademicYear("321S1"));
        assertThrows(IllegalArgumentException.class, () -> new AcademicYear("21S0"));
        assertThrows(IllegalArgumentException.class, () -> new AcademicYear("21S9"));
    }

    @Test
    public void isValidAcademicYear() {
        // null academic year
        assertThrows(NullPointerException.class, () -> AcademicYear.isValidAcademicYear(null));

        // invalid academic years
        assertFalse(AcademicYear.isValidAcademicYear("")); // empty string
        assertFalse(AcademicYear.isValidAcademicYear(" ")); // spaces only
        assertFalse(AcademicYear.isValidAcademicYear("21S0"));
        assertFalse(AcademicYear.isValidAcademicYear("21S9"));
        assertFalse(AcademicYear.isValidAcademicYear("321S1"));

        // valid academic years
        assertTrue(AcademicYear.isValidAcademicYear("21S1"));
        assertTrue(AcademicYear.isValidAcademicYear("21S2"));
        assertTrue(AcademicYear.isValidAcademicYear("21S3"));
        assertTrue(AcademicYear.isValidAcademicYear("21S4"));
        assertTrue(AcademicYear.isValidAcademicYear("21S5"));
        assertTrue(AcademicYear.isValidAcademicYear("21S6"));
        assertTrue(AcademicYear.isValidAcademicYear("21S7"));
        assertTrue(AcademicYear.isValidAcademicYear("21S8"));
        assertTrue(AcademicYear.isValidAcademicYear("00S1"));
        assertTrue(AcademicYear.isValidAcademicYear("99S1"));
    }
}
