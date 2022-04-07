package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EthnicityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ethnicity(null));
    }

    @Test
    public void constructor_invalidEthnicity_throwsIllegalArgumentException() {
        String invalidEthnicity = "";
        assertThrows(IllegalArgumentException.class, () -> new Ethnicity(invalidEthnicity));
    }

    @Test
    public void isValidEthnicity() {
        // null ethnicity
        assertThrows(NullPointerException.class, () -> Ethnicity.isValidEthnicity(null));

        // invalid ethnicity
        assertFalse(Ethnicity.isValidEthnicity("")); // empty string
        assertFalse(Ethnicity.isValidEthnicity(" ")); // space only

        // valid ethnicity
        assertTrue(Ethnicity.isValidEthnicity("Chinese"));
    }
}
