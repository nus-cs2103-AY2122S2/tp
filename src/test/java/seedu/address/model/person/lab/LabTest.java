package seedu.address.model.person.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateLabException;

public class LabTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lab(null));
    }

    @Test
    public void constructor_invalidLab_throwsIllegalArgumentException() {
        String invalidLabNumber = "a";
        assertThrows(IllegalArgumentException.class, () -> new Lab(invalidLabNumber));
    }

    @Test
    public void isValidLab() {
        // null lab
        assertThrows(NullPointerException.class, () -> Lab.isValidLab(null));

        // blank lab
        assertFalse(Lab.isValidLab("")); // empty string
        assertFalse(Lab.isValidLab(" ")); // spaces only

        // invalid lab
        assertFalse(Lab.isValidLab("1a")); // invalid lab number

        // valid lab
        assertTrue(Lab.isValidLab("1")); // valid integer
        assertTrue(Lab.isValidLab("2")); // valid integer
        assertTrue(Lab.isValidLab("123456789")); // valid integer
    }

    @Test
    public void editLabStatus_changeStatus_success() {
        Lab stub = new Lab("1");

        assertEquals(new Lab("1").of(LabStatus.GRADED), stub.editLabStatus(LabStatus.GRADED));
        assertNotEquals(new Lab("1").of(LabStatus.SUBMITTED), stub);
    }

    @Test
    public void editLabStatus_sameStatus_throwsDuplicateLabException() {
        Lab stub = new Lab("1");
        assertThrows(DuplicateLabException.class, () -> stub.editLabStatus(LabStatus.UNSUBMITTED));
    }
}
