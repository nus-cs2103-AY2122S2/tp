package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MeetingTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingTime(null));
    }

    @Test
    public void constructor_invalidMeetingTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingTime(invalidTime));
    }

    @Test
    void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> MeetingTime.isValidTime(null));

        // blank time
        assertFalse(MeetingTime.isValidTime("")); // empty string
        assertFalse(MeetingTime.isValidTime(" ")); // spaces only

        // missing data
        assertFalse(MeetingTime.isValidTime("100")); // 3 digits
        assertFalse(MeetingTime.isValidTime("2 030")); // space in between

        // invalid format
        assertFalse(MeetingTime.isValidTime("10:00")); // space in between
        assertFalse(MeetingTime.isValidTime("14.30")); // space in between
        assertFalse(MeetingTime.isValidTime("1030am")); // space in between

        // valid format
        assertTrue(MeetingTime.isValidTime("1000")); // AM timing
        assertTrue(MeetingTime.isValidTime("2000")); // PM timing
        assertTrue(MeetingTime.isValidTime("2359")); // Just before Midnight
        assertTrue(MeetingTime.isValidTime("0000")); // Midnight
    }

    @Test
    void compare() {
        MeetingTime morning = new MeetingTime("1000");
        MeetingTime night = new MeetingTime("2000");
        MeetingTime beforeMidnight = new MeetingTime("2359");
        MeetingTime midnight = new MeetingTime("0000");

        assert (night.compare(morning) == 1); // night is after morning
        assert (beforeMidnight.compare(morning) == 1); // night is after morning

        // midnight is the earliest time possible
        assert (midnight.compare(morning) == -1);
        assert (midnight.compare(night) == -1);
        assert (midnight.compare(beforeMidnight) == -1);

        // before midnight is the latest time possible
        assert (beforeMidnight.compare(morning) == 1);
        assert (beforeMidnight.compare(night) == 1);
        assert (beforeMidnight.compare(midnight) == 1);

        // equals check
        assert (morning.compare(morning) == 0);
        assert (night.compare(night) == 0);
        assert (beforeMidnight.compare(beforeMidnight) == 0);
        assert (midnight.compare(midnight) == 0);
    }
}
