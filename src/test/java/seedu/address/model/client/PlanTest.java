package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PlanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Plan(null));
    }

    @Test
    public void constructor_invalidPlan_throwsIllegalArgumentException() {
        String invalidPlan = "";
        assertThrows(IllegalArgumentException.class, () -> new Plan(invalidPlan));
    }

    @Test
    public void isValidPlan() {
        // null address
        assertThrows(NullPointerException.class, () -> Plan.isValidPlan(null));

        // invalid plans
        assertFalse(Plan.isValidPlan("")); // empty string
        assertFalse(Plan.isValidPlan(" ")); // spaces only

        // valid plans
        assertTrue(Plan.isValidPlan("NORMAL 100MBps"));
        assertTrue(Plan.isValidPlan("-")); // one character
        assertTrue(Plan.isValidPlan("NORMAL plan with speed of 100 Megabytes per second")); // long plan
    }
}
