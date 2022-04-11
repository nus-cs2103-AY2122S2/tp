package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PROJECT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.GREENDALE;
import static seedu.address.testutil.TypicalMeetings.PROJECT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;

public class MeetingListTest {
    private final MeetingsList meetingList = new MeetingsList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(meetingList.contains(GREENDALE));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        meetingList.add(GREENDALE);
        assertTrue(meetingList.contains(GREENDALE));
    }

    @Test
    public void contains_meetingWithSameIdentityFieldsInList_returnsFalse() {
        meetingList.add(GREENDALE);
        Meeting editedMeeting = new MeetingBuilder(GREENDALE).withAgenda(VALID_AGENDA_PROJECT)
                .build();
        assertFalse(meetingList.contains(editedMeeting));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        meetingList.add(GREENDALE);
        assertThrows(DuplicateMeetingException.class, () -> meetingList.add(GREENDALE));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.setMeeting(null, GREENDALE));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.setMeeting(GREENDALE, null));
    }

    @Test
    public void setMeeting_targetMeetingnNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> meetingList.setMeeting(GREENDALE, GREENDALE));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        meetingList.add(GREENDALE);
        meetingList.setMeeting(GREENDALE, GREENDALE);
        MeetingsList expectedMeetingList = new MeetingsList();
        expectedMeetingList.add(GREENDALE);
        assertEquals(expectedMeetingList, meetingList);
    }

    @Test
    public void setMeeting_editedPersonHasSameIdentity_success() {
        meetingList.add(GREENDALE);
        Meeting editedMeeting = new MeetingBuilder(GREENDALE).withAgenda(VALID_AGENDA_PROJECT)
                .withTime(VALID_TIME_PROJECT)
                .build();
        meetingList.setMeeting(GREENDALE, editedMeeting);
        MeetingsList expectedMeetingList = new MeetingsList();
        expectedMeetingList.add(editedMeeting);
        assertEquals(expectedMeetingList, meetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        meetingList.add(GREENDALE);
        meetingList.setMeeting(GREENDALE, PROJECT);
        MeetingsList expectedMeetingList = new MeetingsList();
        expectedMeetingList.add(PROJECT);
        assertEquals(expectedMeetingList, meetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        meetingList.add(GREENDALE);
        meetingList.add(PROJECT);
        assertThrows(DuplicateMeetingException.class, () -> meetingList.setMeeting(GREENDALE, PROJECT));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> meetingList.remove(GREENDALE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        meetingList.add(GREENDALE);
        meetingList.remove(GREENDALE);
        MeetingsList expectedMeetingsList = new MeetingsList();
        assertEquals(expectedMeetingsList, meetingList);
    }

    @Test
    public void setMeetings_nullMeetingsList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.setMeetings((MeetingsList) null));
    }

    @Test
    public void setMeetings_meetingsList_replacesOwnListWithProvidedMeetingsList() {
        meetingList.add(GREENDALE);
        MeetingsList expectedMeetingsList = new MeetingsList();
        expectedMeetingsList.add(PROJECT);
        meetingList.setMeetings(expectedMeetingsList);
        assertEquals(expectedMeetingsList, meetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        meetingList.add(GREENDALE);
        List<Meeting> list = Collections.singletonList(PROJECT);
        meetingList.setMeetings(list);
        MeetingsList expectedMeetingsList = new MeetingsList();
        expectedMeetingsList.add(PROJECT);
        assertEquals(expectedMeetingsList, meetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(GREENDALE, GREENDALE);
        assertThrows(DuplicateMeetingException.class, () -> meetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> meetingList.asUnmodifiableObservableList().remove(0));
    }
}
