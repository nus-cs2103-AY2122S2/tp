package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_QUARTERLY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting GREENDALE = new MeetingBuilder().withAgenda("Paper supply for Greendale College")
            .withPlace("David's Office").withTime("02-04-2023 13:30").build();
    public static final Meeting QUARTERLY = new MeetingBuilder().withAgenda(VALID_AGENDA_QUARTERLY)
            .withTime(VALID_TIME_QUARTERLY).withPlace(VALID_PLACE_QUARTERLY).build();
    public static final Meeting PROJECT = new MeetingBuilder().withAgenda(VALID_AGENDA_PROJECT)
            .withTime(VALID_TIME_PROJECT).withPlace(VALID_PLACE_PROJECT).build();

    private TypicalMeetings() {
    } // prevents instantiation

    /**
     * Returns an {@code MeetingBook} with all the typical meetings.
     */
    public static MeetingBook getTypicalMeetingBook() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetings()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(GREENDALE, QUARTERLY, PROJECT));
    }
}
