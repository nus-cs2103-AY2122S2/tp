package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Priority.getFromDisplayName(null));
    }

    @Test
    public void testGetFromName() {
        assertEquals(Priority.LOW, Priority.getFromDisplayName("LOW"));
        assertEquals(Priority.LOW, Priority.getFromDisplayName("low"));
        assertEquals(Priority.MEDIUM, Priority.getFromDisplayName("medium"));
        assertEquals(Priority.HIGH, Priority.getFromDisplayName("high"));
        assertNull(Priority.getFromDisplayName("something"));
    }

    @Test
    public void testToString() {
        assertEquals("High", Priority.HIGH.toString());
        assertEquals("Medium", Priority.MEDIUM.toString());
        assertEquals("Low", Priority.LOW.toString());
    }
}
