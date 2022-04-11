package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConsultationDiagnosisTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConsultationDiagnosis(null));
    }

    @Test
    public void constructor_invalidConsultationDiagnosis_throwsIllegalArgumentException() {
        String invalidConsultationDiagnosis = "";
        assertThrows(IllegalArgumentException.class, () -> new ConsultationDiagnosis(invalidConsultationDiagnosis));
    }

    @Test
    public void isValidFamilyHistory() {
        // null family history
        assertThrows(NullPointerException.class, () -> ConsultationDiagnosis.isValidDiagnosis(null));

        // invalid family history
        assertFalse(ConsultationDiagnosis.isValidDiagnosis("")); // empty string
        assertFalse(ConsultationDiagnosis.isValidDiagnosis(" ")); // space only

        // valid family history
        assertTrue(ConsultationDiagnosis.isValidDiagnosis("Has Upper Respiratory infection. To be prescribed antibiotics, to be completed."));
    }
}
