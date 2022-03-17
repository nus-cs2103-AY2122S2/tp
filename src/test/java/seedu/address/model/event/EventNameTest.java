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
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidEventName(null));

        // invalid name
        assertFalse(EventName.isValidEventName("")); // empty string
        assertFalse(EventName.isValidEventName(" ")); // spaces only
        assertFalse(EventName.isValidEventName("^")); // only one special character
        assertFalse(EventName.isValidEventName("&peter*")); // starts with special character
        assertFalse(EventName.isValidEventName("Correct but wrong\n")); // starts with special character


        // valid name
        assertTrue(EventName.isValidEventName("12345")); // numbers only
        assertTrue(EventName.isValidEventName("peter's birthday")); // apostrophe
        assertTrue(EventName.isValidEventName("The first \"Shakespeare\" play")); // double quotes
        assertTrue(EventName.isValidEventName("Capital 'Tan'")); // single quotes
        assertTrue(EventName.isValidEventName("Going to a #Awesome Festival")); // long names
    }
}
