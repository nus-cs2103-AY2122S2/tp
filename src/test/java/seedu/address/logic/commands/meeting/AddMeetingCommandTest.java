package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalMeetings.WITH_ALICE;
import static seedu.address.testutil.TypicalMeetings.WITH_BENSON;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

public class AddMeetingCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null,
                WITH_ALICE.getStartDateTime(),
                WITH_ALICE.getEndDateTime(),
                WITH_ALICE.getLabel()));
    }

    @Test
    public void constructor_nullStartDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(Index.fromZeroBased(VALID_INDEX),
                null,
                WITH_ALICE.getEndDateTime(),
                WITH_ALICE.getLabel()));
    }

    @Test
    public void constructor_nullEndDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(Index.fromZeroBased(VALID_INDEX),
                WITH_ALICE.getStartDateTime(),
                null,
                WITH_ALICE.getLabel()));
    }

    @Test
    public void constructor_nullLabel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(Index.fromZeroBased(VALID_INDEX),
                WITH_ALICE.getStartDateTime(),
                WITH_ALICE.getEndDateTime(),
                null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMeetingAdded modelStub = new ModelStubAcceptingMeetingAdded();
        Index validIndex = Index.fromZeroBased(VALID_INDEX);
        LocalDateTime validStartDateTime = WITH_ALICE.getStartDateTime();
        LocalDateTime validEndDateTime = WITH_ALICE.getEndDateTime();
        String validLabel = WITH_ALICE.getLabel();

        CommandResult commandResult = new AddMeetingCommand(
                validIndex, validStartDateTime, validEndDateTime, validLabel)
                .execute(modelStub);

        Meeting validMeeting = new Meeting(validStartDateTime, validEndDateTime, ALICE, validLabel);

        Assertions.assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS_PAST, validMeeting),
                commandResult.getFeedbackToUser());
        Assertions.assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_overlappingMeeting_throwsCommandException() {
        Index validIndex = Index.fromZeroBased(VALID_INDEX);
        LocalDateTime validStartDateTime = WITH_ALICE.getStartDateTime();
        LocalDateTime validEndDateTime = WITH_ALICE.getEndDateTime();
        String validLabel = WITH_ALICE.getLabel();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(validIndex,
                validStartDateTime, validEndDateTime, validLabel);
        ModelStub modelStub = new ModelStubWithClient(WITH_ALICE);

        assertThrows(CommandException.class, AddMeetingCommand.MESSAGE_OVERLAPPING_MEETING, () -> addMeetingCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Index validIndexAlice = Index.fromZeroBased(VALID_INDEX);
        LocalDateTime validStartDateTimeAlice = WITH_ALICE.getStartDateTime();
        LocalDateTime validEndDateTimeAlice = WITH_ALICE.getEndDateTime();
        String validLabelAlice = WITH_ALICE.getLabel();

        Index validIndexBenson = Index.fromZeroBased(VALID_INDEX + 1);
        LocalDateTime validStartDateTimeBenson = WITH_BENSON.getStartDateTime();
        LocalDateTime validEndDateTimeBenson = WITH_BENSON.getEndDateTime();
        String validLabelBenson = WITH_BENSON.getLabel();

        AddMeetingCommand addAliceMeetingCommand = new AddMeetingCommand(validIndexAlice,
                validStartDateTimeAlice, validEndDateTimeAlice, validLabelAlice);
        AddMeetingCommand addBensonMeetingCommand = new AddMeetingCommand(validIndexBenson,
                validStartDateTimeBenson, validEndDateTimeBenson, validLabelBenson);

        // same object -> returns true
        Assertions.assertTrue(addAliceMeetingCommand.equals(addAliceMeetingCommand));

        // different types -> returns false
        Assertions.assertFalse(addAliceMeetingCommand.equals(1));

        // null -> returns false
        Assertions.assertFalse(addAliceMeetingCommand.equals(null));

        // different command -> returns false
        Assertions.assertFalse(addAliceMeetingCommand.equals(addBensonMeetingCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNoClients() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedClientList(Comparator<Client> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Client getDisplayedClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDisplayedClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void closeMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortMeetings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isOverlapping(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isOverlappingExcept(Meeting meeting, Meeting exceptedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate, boolean isShowAll) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isSorted() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIsSorted(boolean isSorted) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isShowAllMeetings() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single client.
     */
    private class ModelStubWithClient extends ModelStub {
        private final Meeting meeting;

        ModelStubWithClient(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean isOverlapping(Meeting otherMeeting) {
            requireNonNull(otherMeeting);
            return meeting.isOverlapping(otherMeeting);
        }

        @Override
        public ObservableList<Client> getClientList() {
            ObservableList<Client> list = FXCollections.observableArrayList();
            list.add(ALICE);
            return list;
        }

        @Override
        public void sortMeetings() {}

        @Override
        public void closeMeeting(Meeting target) {

        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingMeetingAdded extends ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean isOverlapping(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isOverlapping);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ObservableList<Client> getClientList() {
            ObservableList<Client> list = FXCollections.observableArrayList();
            list.add(ALICE);
            return list;
        }

        @Override
        public void sortMeetings() {}

        @Override
        public void closeMeeting(Meeting target) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
