package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_RANGE_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_STORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_STORE_FUTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_STORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_STORE_FUTURE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalMeetings.WITH_ALICE;
import static seedu.address.testutil.TypicalMeetings.WITH_BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    //    @Test
    //    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //        Client client = new ClientBuilder().build();
    //        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    //    }

    @Test
    public void isOverlapping_nonOverlappingMeeting_returnsFalse() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).build();
        Meeting withBenson = new MeetingBuilder(WITH_BENSON).build();
        assertFalse(withAlice.isOverlapping(withBenson));
        assertFalse(withBenson.isOverlapping(withAlice));
    }

    @Test
    public void isOverlapping_overlappingMeeting_returnsTrue() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE)
                .withStartDateTime(VALID_START_DATETIME_STORE)
                .withEndDateTime(VALID_END_DATETIME_STORE)
                .build();
        Meeting withBenson = new MeetingBuilder(WITH_BENSON)
                .withStartDateTime(VALID_START_DATETIME_STORE)
                .withEndDateTime(VALID_END_DATETIME_STORE)
                .build();
        assertTrue(withAlice.isOverlapping(withBenson));
        assertTrue(withBenson.isOverlapping(withAlice));
    }

    @Test
    public void isOverlapping_overlappingItself_returnsTrue() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).build();
        assertTrue(withAlice.isOverlapping(withAlice));
    }

    @Test
    public void isUpcoming_futureMeeting_returnsTrue() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE)
                .withStartDateTime(VALID_START_DATETIME_STORE_FUTURE)
                .withEndDateTime(VALID_END_DATETIME_STORE_FUTURE)
                .build();
        assertTrue(withAlice.isUpcoming());
    }

    @Test
    public void isUpcoming_pastMeeting_returnsFalse() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).build();
        assertFalse(withAlice.isUpcoming());
    }

    @Test
    public void isValidDate_validDate_returnsTrue() {
        assertTrue(Meeting.isValidDate(VALID_START_DATETIME_INPUT));
    }

    @Test
    public void isValidDate_validDateButInvalidDateRange_returnsTrue() {
        assertTrue(Meeting.isValidDate(INVALID_DATETIME_RANGE_INPUT));
    }

    @Test
    public void isValidDate_invalidDate_returnsFalse() {
        assertFalse(Meeting.isValidDate(INVALID_DATETIME_INPUT));
    }

    @Test
    public void isValidDateRange_validDateRange_returnsTrue() {
        assertTrue(Meeting.isValidDateRange(VALID_START_DATETIME_INPUT));
    }

    @Test
    public void isValidDateRange_invalidDate_returnsFalse() {
        assertFalse(Meeting.isValidDateRange(INVALID_DATETIME_INPUT));
    }

    @Test
    public void isValidDateRange_invalidDateRange_returnsFalse() {
        assertFalse(Meeting.isValidDateRange(INVALID_DATETIME_RANGE_INPUT));
    }

    @Test
    public void isValidMeeting_validMeeting_returnsTrue() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).build();
        assertTrue(Meeting.isValidMeeting(withAlice.getStartDateTime(), withAlice.getEndDateTime()));
    }

    @Test
    public void isValidMeeting_invalidMeeting_returnsFalse() {
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).withStartDateTime(VALID_START_DATETIME_STORE_FUTURE).build();
        assertFalse(Meeting.isValidMeeting(withAlice.getStartDateTime(), withAlice.getEndDateTime()));
    }

    @Test
    public void updateClient_success() {
        Client benson = new ClientBuilder(BENSON).build();
        Meeting withBenson = new MeetingBuilder(WITH_ALICE).withClient(benson).build();
        Meeting withAlice = new MeetingBuilder(WITH_ALICE).build();
        Meeting withUpdatedBenson = withAlice.updateClient(benson);
        assertTrue(withBenson.equals(withUpdatedBenson));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting withAliceCopy = new MeetingBuilder(WITH_ALICE).build();
        assertTrue(WITH_ALICE.equals(withAliceCopy));

        // same object -> returns true
        assertTrue(WITH_ALICE.equals(WITH_ALICE));

        // null -> returns false
        assertFalse(WITH_ALICE.equals(null));

        // different type -> returns false
        assertFalse(WITH_ALICE.equals(5));

        // different meeting -> returns false
        assertFalse(WITH_ALICE.equals(WITH_BENSON));

        // different start datetime -> returns false
        Meeting editedWithAlice = new MeetingBuilder(WITH_ALICE).withStartDateTime(VALID_START_DATETIME_STORE).build();
        assertFalse(WITH_ALICE.equals(editedWithAlice));

        // different end datetime -> returns false
        editedWithAlice = new MeetingBuilder(WITH_ALICE).withEndDateTime(VALID_END_DATETIME_STORE).build();
        assertFalse(WITH_ALICE.equals(editedWithAlice));

        // different label -> returns false
        editedWithAlice = new MeetingBuilder(WITH_ALICE).withLabel(VALID_LABEL).build();
        assertFalse(WITH_ALICE.equals(editedWithAlice));

    }
}
