package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidEventName_throwsIllegalArgumentException() {
        String invalidEventName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidEventName));
    }

    @Test
    public void isValidEventName() {
        assertThrows(NullPointerException.class, () -> EventName.isValidEventName(null));

        assertFalse(EventName.isValidEventName(""));
        assertFalse(EventName.isValidEventName("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssss"));

        assertTrue(EventName.isValidEventName("lunch"));
        assertTrue(EventName.isValidEventName("climbing 101"));
        assertTrue(EventName.isValidEventName("climbing-101"));
    }
}
