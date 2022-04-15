package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null));
    }

    @Test
    public void constructor_invalidMedication_throwsIllegalArgumentException() {
        String invalidMedication = "";
        assertThrows(IllegalArgumentException.class, () -> new Medication(invalidMedication));
    }

    @Test
    public void isValidMedication() {
        // null medication
        assertThrows(NullPointerException.class, () -> Medication.isValidMedication(null));

        // invalid medication
        assertFalse(Medication.isValidMedication("")); // empty string
        assertFalse(Medication.isValidMedication(" ")); // space only

        // valid medication
        assertTrue(Medication.isValidMedication("Paracetamol"));
    }
}
