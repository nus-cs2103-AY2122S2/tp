package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.meeting.MeetingDateTime.INPUT_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalMeetings.CS2030;
import static seedu.address.testutil.typical.TypicalMeetings.CS2040;
import static seedu.address.testutil.typical.TypicalMeetings.CS2105;
import static seedu.address.testutil.typical.TypicalMeetings.CS2106;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.meeting.MeetingBuilder;

public class UniqueMeetingTest {
    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(CS2105));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(CS2105);
        assertTrue(uniqueMeetingList.contains(CS2105));

        final Meeting cs2105Clone = new MeetingBuilder(CS2105).build();
        assertTrue(uniqueMeetingList.contains(cs2105Clone));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(CS2105);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(CS2105));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, CS2105));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(CS2105, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(CS2105, CS2105));
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(CS2105);
        uniqueMeetingList.add(CS2106);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(CS2105, CS2106));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(CS2105);
        uniqueMeetingList.setMeeting(CS2105, CS2105);
        final UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(CS2105);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        uniqueMeetingList.add(CS2105);
        final Meeting editedCs2105 = new MeetingBuilder(CS2105).build();
        uniqueMeetingList.setMeeting(CS2105, editedCs2105);
        final UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedCs2105);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        uniqueMeetingList.add(CS2105);
        uniqueMeetingList.setMeeting(CS2105, CS2106);
        final UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(CS2106);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void sortMeetings_meetingsSorted() {
        final LocalDateTime dateTime = LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        final Meeting firstMeeting = new MeetingBuilder(CS2040).withDateTime(dateTime).build();
        final Meeting secondMeeting = new MeetingBuilder(CS2030).withDateTime(dateTime.plusHours(1)).build();
        final Meeting thirdMeeting = new MeetingBuilder(CS2106).withDateTime(dateTime.plusHours(2)).build();
        final Meeting fourthMeeting = new MeetingBuilder(CS2105).withDateTime(dateTime.plusHours(3)).build();

        uniqueMeetingList.add(fourthMeeting);
        uniqueMeetingList.add(firstMeeting);
        uniqueMeetingList.add(thirdMeeting);
        uniqueMeetingList.add(secondMeeting);
        uniqueMeetingList.sortMeetings();

        final List<Meeting> expectedList = new ArrayList<>();
        expectedList.add(firstMeeting);
        expectedList.add(secondMeeting);
        expectedList.add(thirdMeeting);
        expectedList.add(fourthMeeting);
        Collections.sort(expectedList);

        assertEquals(expectedList, uniqueMeetingList.asUnmodifiableObservableList());
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(CS2105));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        uniqueMeetingList.add(CS2105);
        uniqueMeetingList.remove(CS2105);
        final UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        // non-empty replacement, initially non-empty
        uniqueMeetingList.add(CS2105);
        final UniqueMeetingList firstExpectedUniqueMeetingList = new UniqueMeetingList();
        firstExpectedUniqueMeetingList.add(CS2030);
        uniqueMeetingList.setMeetings(firstExpectedUniqueMeetingList);
        assertEquals(firstExpectedUniqueMeetingList, uniqueMeetingList);

        // empty replacement, initially non-empty (now has CS2030)
        final UniqueMeetingList secondExpectedUniqueMeetingList = new UniqueMeetingList();
        uniqueMeetingList.setMeetings(secondExpectedUniqueMeetingList);
        assertEquals(secondExpectedUniqueMeetingList, uniqueMeetingList);

        // non-empty replacement, initially empty
        final UniqueMeetingList thirdExpectedUniqueMeetingList = new UniqueMeetingList();
        thirdExpectedUniqueMeetingList.add(CS2040);
        uniqueMeetingList.setMeetings(thirdExpectedUniqueMeetingList);
        assertEquals(thirdExpectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(CS2105);
        List<Meeting> meetingList = Collections.singletonList(CS2106);
        uniqueMeetingList.setMeetings(meetingList);
        final UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(CS2106);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(CS2105, CS2105, CS2106);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueMeetingList.asUnmodifiableObservableList().remove(0));
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueMeetingList.asUnmodifiableObservableList().add(new MeetingBuilder().build()));
    }
}
