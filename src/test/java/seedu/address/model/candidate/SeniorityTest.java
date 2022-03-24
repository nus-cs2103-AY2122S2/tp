package seedu.address.model.candidate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SeniorityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Integer invalidSeniority = null;
        assertThrows(NullPointerException.class, () -> new Seniority(invalidSeniority));
    }

    @Test
    public void constructor_invalidSeniority_throwsIllegalArgumentException() {
        int invalidSeniority = 0;
        assertThrows(IllegalArgumentException.class, () -> new Seniority(invalidSeniority));
    }

    @Test
    public void isValidSeniority() {
        // Invalid seniority
        assertFalse(Seniority.isValidSeniority(0));
        assertFalse(Seniority.isValidSeniority(5));

        // Invalid seniority (extreme values)
        assertFalse(Seniority.isValidSeniority(Integer.MIN_VALUE));
        assertFalse(Seniority.isValidSeniority(Integer.MAX_VALUE));

        // Valid seniority
        assertTrue(Seniority.isValidSeniority(1));
        assertTrue(Seniority.isValidSeniority(2));
        assertTrue(Seniority.isValidSeniority(3));
        assertTrue(Seniority.isValidSeniority(4));
    }
}
