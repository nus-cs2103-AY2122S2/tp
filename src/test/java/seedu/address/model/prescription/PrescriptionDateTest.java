package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.contact.Phone;

public class PrescriptionDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrescriptionDate(null));
    }

    @Test
    public void constructor_invalidPrescriptionDate_throwsIllegalArgumentException() {
        String invalidPrescriptionDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPrescriptionDate));
    }

    @Test
    public void isValidPrescriptionDate() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PrescriptionDate.isValidDate(null));

        // invalid phone numbers
        assertFalse(PrescriptionDate.isValidDate("")); // empty string
        assertFalse(PrescriptionDate.isValidDate(" ")); // space
        assertFalse(PrescriptionDate.isValidDate("hey")); // string
        assertFalse(PrescriptionDate.isValidDate("29-02-2021")); // non leap year
        assertFalse(PrescriptionDate.isValidDate("23456")); // number

        // valid phone numbers
        assertTrue(PrescriptionDate.isValidDate("2012-12-12")); // valid date
        assertTrue(PrescriptionDate.isValidDate("2022-12-12")); // valid date
    }
}
