package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.parser.ArgumentMultimap;
import seedu.contax.logic.parser.Prefix;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.ReadOnlyUserPrefs;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RangeCommand.
 */
public class RangeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void equals() {
        String sampleCommand = "range edit from/1 to/3 p/12345678";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        RangeCommand rangeCommand =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), sampleCommand);
        RangeCommand rangeCommand2 =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), "sampleCommand");

        // same object -> returns true
        assertTrue(rangeCommand.equals(rangeCommand));

        // same values -> returns true
        RangeCommand rangeCommandCopy =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), sampleCommand);
        assertTrue(rangeCommand.equals(rangeCommandCopy));

        // different types -> returns false
        assertFalse(rangeCommand.equals(1));

        // null -> returns false
        assertFalse(rangeCommand.equals(null));

        // different person -> returns false
        assertFalse(rangeCommand.equals(rangeCommand2));
    }

    @Test
    public void execute_personAcceptedByModel_rangeEditSuccessful() throws Exception {
        RangeCommandTest.ModelStubRanged modelStub = new RangeCommandTest.ModelStubRanged();
        String sampleCommand = "range edit p/12345678 from/1 to/1 ";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();

        RangeCommand rangeCommandCopy =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), sampleCommand);
        CommandResult commandResult = new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3),
                sampleCommand).execute(modelStub);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new Schedule(), new UserPrefs());
        assertCommandSuccess(rangeCommandCopy, model, commandResult, expectedModel);
    }

    @Test
    public void execute_personAcceptedByModel_rangeDeleteSuccessful() throws Exception {
        RangeCommandTest.ModelStubRanged modelStub = new RangeCommandTest.ModelStubRanged();
        String sampleCommand = "range delete from/1 to/1 ";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix(""), "delete");
        RangeCommand rangeCommandCopy =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), sampleCommand);
        CommandResult commandResult = new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3),
                sampleCommand).execute(modelStub);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new Schedule(), new UserPrefs());
        assertCommandSuccess(rangeCommandCopy, model, commandResult, expectedModel);
    }

    @Test
    public void execute_personAcceptedByModel_invalidIndex() throws Exception {
        RangeCommandTest.ModelStubRanged modelStub = new RangeCommandTest.ModelStubRanged();
        String sampleCommand = "delete from/2 to/1";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix(""), "delete");
        argumentMultimap.put(new Prefix("/from"), "2");
        argumentMultimap.put(new Prefix("/to"), "1");
        RangeCommand rangeCommand =
                new RangeCommand(Index.fromZeroBased(2), Index.fromZeroBased(1), sampleCommand);
        assertCommandFailure(rangeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * A default model stub that have all the methods failing.
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
        public void addPerson(Person person) {
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
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tagToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getScheduleFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScheduleFilePath(Path scheduleFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(ReadOnlySchedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<AppointmentSlot> getDisplayedAppointmentSlots() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDisplayedAppointmentSlots(List<AppointmentSlot> items) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearDisplayedAppointmentSlots() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ScheduleItem> getScheduleItemList() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubRanged extends RangeCommandTest.ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
