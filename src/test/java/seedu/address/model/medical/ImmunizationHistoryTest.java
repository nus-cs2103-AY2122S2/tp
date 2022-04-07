package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImmunizationHistoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImmunizationHistory(null));
    }

    @Test
    public void constructor_invalidImmunizationHistory_throwsIllegalArgumentException() {
        String invalidImmunizationHistory = "";
        assertThrows(IllegalArgumentException.class, () -> new ImmunizationHistory(invalidImmunizationHistory));
    }

    @Test
    public void isValidImmunizationHistory() {
        // null immunization history
        assertThrows(NullPointerException.class, () -> ImmunizationHistory.isValidImmunizationHistory(null));

        // invalid immunization history
        assertFalse(ImmunizationHistory.isValidImmunizationHistory("")); // empty string
        assertFalse(ImmunizationHistory.isValidImmunizationHistory(" ")); // space only

        // valid immunization history
        assertTrue(ImmunizationHistory.isValidImmunizationHistory("MMR; 6 in 1"));
    }
}
