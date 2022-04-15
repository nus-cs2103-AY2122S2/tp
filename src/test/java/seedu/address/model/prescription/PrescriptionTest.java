package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPrescriptions.PRESCRIPTION_A;
import static seedu.address.testutil.TypicalPrescriptions.PRESCRIPTION_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PrescriptionBuilder;

public class PrescriptionTest {

    @Test
    public void equals() {
        Prescription prescription = new PrescriptionBuilder(PRESCRIPTION_A).build();

        assertTrue(PRESCRIPTION_A.equals(prescription));
        assertTrue(PRESCRIPTION_A.equals(PRESCRIPTION_A));

        assertFalse(PRESCRIPTION_A.equals(null));
        assertFalse(PRESCRIPTION_A.equals(5));

        assertFalse(PRESCRIPTION_A.equals(PRESCRIPTION_B));

        Prescription editedPrescription = new PrescriptionBuilder(PRESCRIPTION_A).withNric("G1234565L").build();
        assertFalse(PRESCRIPTION_A.equals(editedPrescription));

        editedPrescription = new PrescriptionBuilder(PRESCRIPTION_A).withDrugName("Axillin").build();
        assertFalse(PRESCRIPTION_A.equals(editedPrescription));
    }
}
