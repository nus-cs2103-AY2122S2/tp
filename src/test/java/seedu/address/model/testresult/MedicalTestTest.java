package seedu.address.model.testresult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalTestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalTest(null));
    }

    @Test
    public void constructor_invalidMedicalTest_throwsIllegalArgumentException() {
        String invalidMedicalTest = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalTest(invalidMedicalTest));
    }

    @Test
    public void isValidMedicalTest() {
        // null medical test
        assertThrows(NullPointerException.class, () -> MedicalTest.isValidMedicalTest(null));

        // invalid medical test
        assertFalse(MedicalTest.isValidMedicalTest("")); // empty string
        assertFalse(MedicalTest.isValidMedicalTest(" ")); // spaces only

        // valid medical test
        assertTrue(MedicalTest.isValidMedicalTest("X-Ray"));
        assertTrue(MedicalTest.isValidMedicalTest("-")); // one character
        assertTrue(MedicalTest.isValidMedicalTest("Antineutrophil Cytoplasmic Antibodies (ANCA) Test")); // long test
    }
}
