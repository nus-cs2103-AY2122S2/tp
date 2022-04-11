package seedu.address.model.testresult;

import org.junit.jupiter.api.Test;
import seedu.address.model.prescription.PrescriptionDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class TestDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TestDate(null));
    }

    @Test
    public void constructor_invalidTestDate_throwsIllegalArgumentException() {
        String invalidTestDate = "";
        assertThrows(IllegalArgumentException.class, () -> new TestDate(invalidTestDate));
    }

    @Test
    public void isValidTestDate() {
        // null date
        assertThrows(NullPointerException.class, () -> TestDate.isValidTestDate(null));

        // invalid date
        assertFalse(TestDate.isValidTestDate("")); // empty string
        assertFalse(TestDate.isValidTestDate(" ")); // space
        assertFalse(TestDate.isValidTestDate("hey")); // string
        assertFalse(TestDate.isValidTestDate("29-02-2021")); // non leap year
        assertFalse(TestDate.isValidTestDate("23456")); // number

        // valid date
        assertTrue(TestDate.isValidTestDate("2012-12-12")); // valid date
        assertTrue(TestDate.isValidTestDate("2022-12-12")); // valid date
    }
}
