package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;
import static seedu.address.testutil.typical.TypicalMeetings.CS2103;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.module.Module;

public class LinkyTimeTest {
    private final LinkyTime linkyTime = new LinkyTime();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), linkyTime.getMeetingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> linkyTime.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLinkyTime_replacesData() {
        final LinkyTime newData = getTypicalLinkyTime();
        linkyTime.resetData(newData);
        assertEquals(newData, linkyTime);
    }

    @Test
    public void resetData_withDuplicateMeetings_throwsDuplicateMeetingException() {
        // Two meetings with the same identity fields
        final List<Meeting> newMeetings = Arrays.asList(CS2103, CS2103);
        final LinkyTimeStub newData = new LinkyTimeStub(newMeetings);

        assertThrows(DuplicateMeetingException.class, () -> linkyTime.resetData(newData));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> linkyTime.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInLinkyTime_returnsFalse() {
        assertFalse(linkyTime.hasMeeting(CS2103));
    }

    @Test
    public void hasMeeting_meetingInLinkyTime_returnsTrue() {
        linkyTime.addMeeting(CS2103);
        assertTrue(linkyTime.hasMeeting(CS2103));
    }

    @Test
    public void getMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> linkyTime.getMeetingList().remove(0));
    }

    /**
     * A stub ReadOnlyLinkyTime whose meeting list can violate interface constraints.
     */
    private static class LinkyTimeStub implements ReadOnlyLinkyTime {
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        LinkyTimeStub(Collection<Meeting> meetings) {
            this.meetings.setAll(meetings);
        }
        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
