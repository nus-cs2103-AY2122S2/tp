package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalMeetings.WITH_ALICE;
import static seedu.address.testutil.TypicalMeetings.WITH_BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.meeting.exceptions.OverlappingMeetingsException;
import seedu.address.testutil.MeetingBuilder;

public class NonOverlappingMeetingTest {

    private final NonOverlappingMeetingList nonOverlappingMeetingList = new NonOverlappingMeetingList();

    @Test
    public void overlaps_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.overlaps(null));
    }

    @Test
    public void overlaps_noMeetings_returnsFalse() {
        assertFalse(nonOverlappingMeetingList.overlaps(WITH_ALICE));
    }

    @Test
    public void overlaps_meetingInList_returnsTrue() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        assertTrue(nonOverlappingMeetingList.overlaps(WITH_ALICE));
    }

    @Test
    public void overlapsExcept_nullToCheckMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList
                .overlapsExcept(null, WITH_ALICE));
    }

    @Test
    public void overlapsExcept_nullExceptedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList
                .overlapsExcept(WITH_ALICE, null));
    }

    @Test
    public void overlapsExcept_exceptSameMeeting_returnsFalse() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        assertFalse(nonOverlappingMeetingList.overlapsExcept(WITH_ALICE, WITH_ALICE));
    }

    @Test
    public void overlapsExcept_exceptDifferentMeeting_returnsTrue() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.add(WITH_BENSON);
        assertTrue(nonOverlappingMeetingList.overlapsExcept(WITH_BENSON, WITH_ALICE));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsOverlappingMeetingsException() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        assertThrows(OverlappingMeetingsException.class, () -> nonOverlappingMeetingList.add(WITH_ALICE));
    }

    @Test
    public void setClient_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.setClient(null, ALICE));
    }

    @Test
    public void setClient_nullEditedClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.setClient(ALICE, null));
    }

    @Test
    public void setClient_targetClientDoesNotExist_noChange() {
        nonOverlappingMeetingList.add(WITH_BENSON);
        nonOverlappingMeetingList.setClient(ALICE, BOB);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_BENSON);
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setClient_targetClientExists_success() {
        Meeting withBob = new MeetingBuilder(WITH_ALICE).withClient(BOB).build();
        nonOverlappingMeetingList.add(withBob);
        nonOverlappingMeetingList.setClient(BOB, ALICE);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).withClient(ALICE).build();
        expectedNonOverlappingMeetingList.add(withAlice);
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.setMeeting(null, WITH_ALICE));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList
                .setMeeting(WITH_ALICE, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> nonOverlappingMeetingList
                .setMeeting(WITH_ALICE, WITH_ALICE));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.setMeeting(WITH_ALICE, WITH_ALICE);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_ALICE);
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingIsDifferentMeeting_success() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.setMeeting(WITH_ALICE, WITH_BENSON);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_BENSON);
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingIsOverlapping_throwsOverlappingMeetingsExceptions() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.add(WITH_BENSON);
        assertThrows(OverlappingMeetingsException.class, () -> nonOverlappingMeetingList
                .setMeeting(WITH_ALICE, WITH_BENSON));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> nonOverlappingMeetingList.remove(WITH_ALICE));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.remove(WITH_ALICE);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setMeetings_nullNonOverlappingMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList
                .setMeetings((NonOverlappingMeetingList) null));
    }

    @Test
    public void setMeetings_nonOverlappingMeetingList_replacesOwnListWithProvidedNonOverlappingMeetingList() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_BENSON);
        nonOverlappingMeetingList.setMeetings(expectedNonOverlappingMeetingList);
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        List<Meeting> meetingList = Collections.singletonList(WITH_BENSON);
        nonOverlappingMeetingList.setMeetings(meetingList);
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_BENSON);
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void setMeetings_listWithOverlappingMeetings_throwsOverlappingMeetingsException() {
        List<Meeting> listWithOverlappingMeetings = Arrays.asList(WITH_ALICE, WITH_ALICE);
        assertThrows(OverlappingMeetingsException.class, () -> nonOverlappingMeetingList
                .setMeetings(listWithOverlappingMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> nonOverlappingMeetingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals_sameList_returnsTrue() {
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        assertTrue(nonOverlappingMeetingList.equals(expectedNonOverlappingMeetingList));
    }

    @Test
    public void equals_differentList_returnsFalse() {
        nonOverlappingMeetingList.add(WITH_ALICE);
        NonOverlappingMeetingList otherNonOverlappingMeetingList = new NonOverlappingMeetingList();
        otherNonOverlappingMeetingList.add(WITH_BENSON);
        assertFalse(nonOverlappingMeetingList.equals(otherNonOverlappingMeetingList));
    }

    @Test
    public void sortAscending_noMeetings() {
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        nonOverlappingMeetingList.sortAscending();
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void sortAscending_oneMeeting() {
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.sortAscending();
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }

    @Test
    public void sortAscending_twoMeetings_sortsSuccessfully() {
        NonOverlappingMeetingList expectedNonOverlappingMeetingList = new NonOverlappingMeetingList();
        expectedNonOverlappingMeetingList.add(WITH_ALICE);
        expectedNonOverlappingMeetingList.add(WITH_BENSON);
        nonOverlappingMeetingList.add(WITH_BENSON);
        nonOverlappingMeetingList.add(WITH_ALICE);
        nonOverlappingMeetingList.sortAscending();
        assertEquals(expectedNonOverlappingMeetingList, nonOverlappingMeetingList);
    }
}
