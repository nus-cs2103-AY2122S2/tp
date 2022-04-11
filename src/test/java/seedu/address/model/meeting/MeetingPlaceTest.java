package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingPlaceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingPlace(null));
    }

    @Test
    public void constructor_invalidAgenda_throwsIllegalArgumentException() {
        String invalidPlace = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingPlace(invalidPlace));
    }

    @Test
    public void isValidPlace() {
        // null agenda
        assertThrows(NullPointerException.class, () -> MeetingPlace.isValidMeetingPlace(null));

        // invalid agenda
        assertFalse(MeetingPlace.isValidMeetingPlace("")); // empty string
        assertFalse(MeetingPlace.isValidMeetingPlace("  ")); // space

        // valid agenda
        assertTrue(MeetingPlace.isValidMeetingPlace("conference room")); // alphabets only
        assertTrue(MeetingPlace.isValidMeetingPlace("conference, room!!!")); // alphabets with symbols
        assertTrue(MeetingPlace.isValidMeetingPlace("3 conference rooms")); // alphanumeric characters
        assertTrue(MeetingPlace.isValidMeetingPlace("Conference Room")); // with capital letters
    }
}
