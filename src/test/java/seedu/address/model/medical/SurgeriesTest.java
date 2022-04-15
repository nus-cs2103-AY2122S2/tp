package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SurgeriesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Surgeries(null));
    }

    @Test
    public void constructor_invalidSurgeries_throwsIllegalArgumentException() {
        String invalidSurgeries = "";
        assertThrows(IllegalArgumentException.class, () -> new Surgeries(invalidSurgeries));
    }

    @Test
    public void isValidSurgeries() {
        // null surgeries
        assertThrows(NullPointerException.class, () -> Surgeries.isValidSurgeries(null));

        // invalid surgeries
        assertFalse(Surgeries.isValidSurgeries("")); // empty string
        assertFalse(Surgeries.isValidSurgeries(" ")); // space only

        // valid surgeries
        assertTrue(Surgeries.isValidSurgeries("Appendectomy"));
    }
}
