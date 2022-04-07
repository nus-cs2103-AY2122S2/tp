package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FamilyHistoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FamilyHistory(null));
    }

    @Test
    public void constructor_invalidFamilyHistory_throwsIllegalArgumentException() {
        String invalidFamilyHistory = "";
        assertThrows(IllegalArgumentException.class, () -> new FamilyHistory(invalidFamilyHistory));
    }

    @Test
    public void isValidFamilyHistory() {
        // null family history
        assertThrows(NullPointerException.class, () -> FamilyHistory.isValidFamilyHistory(null));

        // invalid family history
        assertFalse(FamilyHistory.isValidFamilyHistory("")); // empty string
        assertFalse(FamilyHistory.isValidFamilyHistory(" ")); // space only

        // valid family history
        assertTrue(FamilyHistory.isValidFamilyHistory("Has family history of high blood pressure"));
    }
}
