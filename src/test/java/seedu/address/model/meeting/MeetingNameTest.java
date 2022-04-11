package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingName(null));
    }

    @Test
    public void constructor_invalidMeetingName_throwsIllegalArgumentException() {
        String invalidMeetingName = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingName(invalidMeetingName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> MeetingName.isValidName(null));

        // invalid name
        assertFalse(MeetingName.isValidName("")); // empty string
        assertFalse(MeetingName.isValidName(" ")); // spaces only
        assertFalse(MeetingName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(MeetingName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(MeetingName.isValidName("peter jack")); // alphabets only
        assertTrue(MeetingName.isValidName("12345")); // numbers only
        assertTrue(MeetingName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(MeetingName.isValidName("Capital Tan")); // with capital letters
        assertTrue(MeetingName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
