package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ScheduledMeetingTest {

    MeetingDate validDate = new MeetingDate("2022-03-15");
    MeetingTime validTime = new MeetingTime("1400");

    ScheduledMeeting noMeeting = new ScheduledMeeting();
    ScheduledMeeting anotherNoMeeting = new ScheduledMeeting();

    ScheduledMeeting meeting = new ScheduledMeeting(validDate, validTime);
    ScheduledMeeting laterMeeting = new ScheduledMeeting(new MeetingDate("2022-12-12"), validTime);
    ScheduledMeeting meetingLaterTime = new ScheduledMeeting(validDate, new MeetingTime("1430"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduledMeeting(null, null));
    }

    @Test
    void hasMeetingScheduled() {
        ScheduledMeeting noMeeting = new ScheduledMeeting();
        assertFalse(noMeeting.hasMeetingScheduled());

        ScheduledMeeting meeting = new ScheduledMeeting(validDate, validTime);
        assertTrue(meeting.hasMeetingScheduled());
    }

    @Test
    void hasSameMeeting() {
        assertFalse(meeting.hasSameMeeting(laterMeeting));
        assertFalse(meeting.hasSameMeeting(meetingLaterTime));
        assertFalse(meeting.hasSameMeeting(noMeeting)); // when compared to no meeting, should return false
        assertFalse(noMeeting.hasSameMeeting(anotherNoMeeting));

        assertTrue(meeting.hasSameMeeting(meeting));
        assertTrue(laterMeeting.hasSameMeeting(laterMeeting));
        assertTrue(meetingLaterTime.hasSameMeeting(meetingLaterTime));
    }

    @Test
    void compare() {
        assert (meeting.compare(laterMeeting) == -1);
        assert (meeting.compare(meetingLaterTime) == -1);
        assert (meeting.compare(meeting) == 0);
        assert (laterMeeting.compare(meetingLaterTime) == 1);
    }
}