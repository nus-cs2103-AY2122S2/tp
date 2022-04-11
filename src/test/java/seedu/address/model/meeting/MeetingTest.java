package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.model.meeting.MeetingDateTime.INPUT_FORMAT;
import static seedu.address.testutil.typical.TypicalMeetings.CS2103;
import static seedu.address.testutil.typical.TypicalMeetings.CS2105;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.meeting.MeetingBuilder;

public class MeetingTest {
    /*
     * Note: getNextRecurrence is only called for recurring meetings
     */
    @Test
    public void getNextRecurrence_ongoingMeeting_returnCurrentRecurrence() {
        final LocalDateTime current =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        final LocalDateTime startDateTime =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT)
                        .minusHours(1);
        final Meeting meeting = new MeetingBuilder().withIsRecurring("Y")
                .withDuration("2").withDateTime(startDateTime).build();
        assertEquals(meeting.getNextRecurrence(current), startDateTime);
    }

    @Test
    public void getNextRecurrence_newUpcomingMeeting_returnStartDateTime() {
        final LocalDateTime current =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        final LocalDateTime startDateTime =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT).plusDays(2);
        final Meeting meeting = new MeetingBuilder().withIsRecurring("Y")
                .withDateTime(startDateTime).build();
        assertEquals(meeting.getNextRecurrence(current), startDateTime);
    }

    @Test
    public void getNextRecurrence_elapsedMeeting_returnNextRecurrence() {
        final LocalDateTime current =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        final LocalDateTime startDateTime =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT).minusDays(2);
        final LocalDateTime nextRecurrence = startDateTime.plusWeeks(1);
        final Meeting meeting = new MeetingBuilder().withIsRecurring("Y")
                .withDateTime(startDateTime).build();
        assertEquals(meeting.getNextRecurrence(current), nextRecurrence);
    }

    @Test
    public void getNextRecurrence_justElapsedMeeting_returnNextRecurrence() {
        final LocalDateTime current =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        final LocalDateTime startDateTime =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT)
                        .minusHours(2).minusNanos(1);
        final LocalDateTime nextRecurrence = startDateTime.plusWeeks(1);
        final Meeting meeting = new MeetingBuilder().withIsRecurring("Y")
                .withDuration("2").withDateTime(startDateTime).build();
        assertEquals(meeting.getNextRecurrence(current), nextRecurrence);
    }

    /*
     * Note: getStartDateTime and getEndDateTime both rely on getNextRecurrence
     */
    @Test
    public void getStartDateTime_nonRecurringMeeting_returnStartDateTime() {
        final Meeting firstMeeting = new MeetingBuilder().withIsRecurring("N").build();
        assertEquals(firstMeeting.getStartDateTime(), new MeetingDateTime(MeetingBuilder.DEFAULT_DATETIME));

        final Meeting minMeeting = new MeetingBuilder().withIsRecurring("N").withDateTime(LocalDateTime.MIN).build();
        final Meeting maxMeeting = new MeetingBuilder().withIsRecurring("N").withDateTime(LocalDateTime.MAX).build();
        assertEquals(minMeeting.getStartDateTime(), new MeetingDateTime(LocalDateTime.MIN));
        assertEquals(maxMeeting.getStartDateTime(), new MeetingDateTime(LocalDateTime.MAX));
    }

    @Test
    public void getEndDateTime_nonRecurringMeeting_returnEndDateTimeFromStartDateTime() {
        final Meeting meeting = new MeetingBuilder().withIsRecurring("N").build();
        final LocalDateTime startDateTime = LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        final MeetingDuration duration = new MeetingDuration(MeetingBuilder.DEFAULT_DURATION);
        final LocalDateTime firstExpectedEnd = startDateTime.plusMinutes((int) duration.duration * 60);
        assertEquals(meeting.getEndDateTime(), new MeetingDateTime(firstExpectedEnd));
    }

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        final Meeting meeting = new MeetingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> meeting.getTags().add(new Tag("tag")));
    }

    @Test
    public void equals() {
        // same values -> returns true
        final Meeting cs2105Copy = new MeetingBuilder(CS2105).build();
        assertTrue(CS2105.equals(cs2105Copy));

        // same object -> returns true
        assertTrue(CS2105.equals(CS2105));

        // null -> returns false
        assertFalse(CS2105.equals(null));

        // different type -> returns false
        assertFalse(CS2105.equals(5));

        // different meeting -> returns false
        assertFalse(CS2105.equals(CS2103));

        // different name -> returns false
        Meeting editedCs2105 = new MeetingBuilder(CS2105).withName(VALID_NAME_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different url -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withUrl(VALID_URL_LECTURE).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different start datetime -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withDateTime(VALID_DATETIME_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different duration -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withDuration(VALID_DURATION_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different module -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withModule(VALID_MODULE_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different recurrence -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withIsRecurring(VALID_RECURRING_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different tags -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withTags(VALID_TAG_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));
    }

    @Test
    public void compareTo_differentStartDateTime_earlierThenLater() {
        final Meeting earlierMeeting = new MeetingBuilder().withIsRecurring("N")
                .withDateTime(VALID_DATETIME_TUTORIAL).build();
        final Meeting laterMeeting = new MeetingBuilder().withIsRecurring("N")
                .withDateTime(VALID_DATETIME_LECTURE).build();
        assertTrue(earlierMeeting.compareTo(laterMeeting) < 0);
    }

    @Test
    public void compareTo_sameStartDifferentDuration_shorterThenLonger() {
        final Meeting shorterMeeting = new MeetingBuilder().withIsRecurring("N")
                .withDuration("1").build();
        final Meeting longerMeeting = new MeetingBuilder().withIsRecurring("N")
                .withDuration("2").build();
        assertTrue(shorterMeeting.compareTo(longerMeeting) < 0);
    }
}
