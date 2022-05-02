package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StatusTest {
    private Status statusPaid;
    @BeforeEach
    public void setUp() {
        statusPaid = new Status("true");
    }

    @Test
    public void constructor_null_success() throws Exception {
        try {
            new Status(null);
        } catch (Exception e) {
            throw(new Exception("This should not be thrown"));
        }
    }

    @Test
    public void constructor_validArguments_success() {
        // True
        assertDoesNotThrow(() -> new Status("True"));

        // False
        assertDoesNotThrow(() -> new Status("False"));
    }

    @Test
    public void isPaidTest() {
        assertTrue(statusPaid.isPaid());
    }

    @Test
    public void getValueTest() {
        assertEquals("Paid", statusPaid.getValue());
    }
}
