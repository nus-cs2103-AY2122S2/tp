package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalPersons.ALICE;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.ReadOnlyUserPrefs;
import seedu.contax.model.Schedule;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.TypicalIndexes;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new AddAppointmentCommand(null, null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment, null).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_appointmentWithPersonAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder(APPOINTMENT_ALICE).build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment, Index.fromZeroBased(0))
                .execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_overlappingAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        Appointment appointmentToAdd = new AppointmentBuilder()
                .withStartDateTime(validAppointment.getStartDateTime().plusMinutes(1)).build();
        AddAppointmentCommand addCommand = new AddAppointmentCommand(appointmentToAdd, null);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class, AddAppointmentCommand.MESSAGE_OVERLAPPING_APPOINTMENT, ()
            -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_appointmentWithInvalidPersonIndex_throwsCommandException() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder(APPOINTMENT_ALICE).build();

        AddAppointmentCommand addCommand = new AddAppointmentCommand(validAppointment,
                Index.fromZeroBased(100));

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
            -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment appt1 = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        Appointment appt2 = new AppointmentBuilder(APPOINTMENT_ALONE).build();
        AddAppointmentCommand addAppt1Command = new AddAppointmentCommand(appt1, null);
        AddAppointmentCommand addAppt2Command = new AddAppointmentCommand(appt2, null);
        AddAppointmentCommand addAppt3Command = new AddAppointmentCommand(appt1,
                TypicalIndexes.INDEX_FIRST_PERSON);

        // same object -> returns true
        assertTrue(addAppt1Command.equals(addAppt1Command));

        // same values -> returns true
        AddAppointmentCommand addAppt1CommandCopy = new AddAppointmentCommand(appt1, null);
        assertTrue(addAppt1Command.equals(addAppt1CommandCopy));

        // different types -> returns false
        assertFalse(addAppt1Command.equals(1));

        // null -> returns false
        assertFalse(addAppt1Command.equals(null));

        // different person -> returns false
        assertFalse(addAppt1Command.equals(addAppt2Command));

        // different index -> returns false
        assertFalse(addAppt1Command.equals(addAppt3Command));
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
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment target) {
            requireNonNull(target);
            this.appointment = target;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(List.of(ALICE));
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {

        }

        @Override
        public boolean hasOverlappingAppointment(Appointment target) {
            requireNonNull(target);
            return target.isOverlapping(this.appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasOverlappingAppointment(Appointment target) {
            requireNonNull(target);
            return appointmentsAdded.stream().anyMatch(x -> x.isOverlapping(target));
        }

        @Override
        public void addAppointment(Appointment target) {
            requireNonNull(target);
            appointmentsAdded.add(target);
        }

        @Override
        public ReadOnlySchedule getSchedule() {
            return new Schedule();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(List.of(ALICE));
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return getTypicalAddressBook();
        }
    }

}
