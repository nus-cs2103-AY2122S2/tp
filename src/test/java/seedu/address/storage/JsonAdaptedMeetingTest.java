package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.GREENDALE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.person.Id;
import seedu.address.storage.meeting.JsonAdaptedAttendee;
import seedu.address.storage.meeting.JsonAdaptedMeeting;


public class JsonAdaptedMeetingTest {
    private static final String INVALID_AGENDA = "";
    private static final String INVALID_PLACE = "";
    private static final String INVALID_TIME = "2022-22-05 23:59";

    private static final String VALID_AGENDA = "Meeting";
    private static final String VALID_PLACE = "Room";
    private static final String VALID_TIME = "25-05-2022 23:59";
    private static final Set<Id> VALID_ATTENDEES = new HashSet<Id>();

    private static final List<JsonAdaptedAttendee> validJsonAdaptedAttendee = new ArrayList<>();

    static {
        VALID_ATTENDEES.add(new Id("9ddd72fa-e32e-4ed7-b475-32d4b19f6b72"));
        VALID_ATTENDEES.add(new Id("123e4567-e89b-12d3-a456-556642440000"));
        validJsonAdaptedAttendee.add(new JsonAdaptedAttendee(new Id("9ddd72fa-e32e-4ed7-b475-32d4b19f6b72")));
        validJsonAdaptedAttendee.add(new JsonAdaptedAttendee(new Id("123e4567-e89b-12d3-a456-556642440000")));
    }

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {

        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(GREENDALE.setAttendees(VALID_ATTENDEES));
        assertEquals(GREENDALE.setAttendees(VALID_ATTENDEES), meeting.toModelType());
    }

    @Test
    public void toModelType_invalidAgenda_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(INVALID_AGENDA, VALID_PLACE, VALID_TIME, validJsonAdaptedAttendee);
        String expectedMessage = Agenda.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidPlace_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_AGENDA, INVALID_PLACE, VALID_TIME, validJsonAdaptedAttendee);
        String expectedMessage = MeetingPlace.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }


    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_AGENDA, VALID_PLACE, INVALID_TIME, validJsonAdaptedAttendee);
        String expectedMessage = MeetingTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }
}
