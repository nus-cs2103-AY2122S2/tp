package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyAppointmentHistoryBook;
import seedu.address.model.ReadOnlyExpiredRecordBook;
import seedu.address.model.ReadOnlyInsuranceBook;
import seedu.address.model.ReadOnlyRecordBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.history.AppointmentHistory;
import seedu.address.model.history.ExpiredRecord;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        String expected = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment);
        assertEquals(expected, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment alice = new AppointmentBuilder().withDescription("Meet Alice at Found cafe").build();
        Appointment bob = new AppointmentBuilder().withDescription("Meet Bob at HVALA cafe").build();
        AddAppointmentCommand addAliceCommand = new AddAppointmentCommand(alice);
        AddAppointmentCommand addBobCommand = new AddAppointmentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddAppointmentCommand addAliceCommandCopy = new AddAppointmentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different appointment -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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

        // Methods from new components
        @Override
        public ReadOnlyInsuranceBook getInsuranceBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInsuranceBook(ReadOnlyInsuranceBook insuranceBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInsurance(Insurance insurance) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInsurance(Insurance target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInsurance(Insurance insurance) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInsurance(Insurance target, Insurance editedInsurance) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Insurance> getFilteredInsuranceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInsuranceList(Predicate<Insurance> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAppointmentBook getAppointmentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortAppointmentBook(Comparator<Appointment> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedInsurance) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRecordBook getRecordBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecordBook(ReadOnlyRecordBook recordBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRecord(Record record) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecord(Record record) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecord(Record record) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortRecordBook(Comparator<Record> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecord(Record target, Record editedRecord) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Record> getFilteredRecordList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRecordList(Predicate<Record> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ReadOnlyAppointmentHistoryBook getAppointmentHistoryBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasHistory(AppointmentHistory history) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentHistoryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentHistoryList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExpiredRecordBook getExpiredRecordBook() {
            throw new AssertionError("This method should not be called.");
        }

        //        @Override
        //        public boolean hasRecord(ExpiredRecord record) {
        //            throw new AssertionError("This method should not be called.");
        //        }

        @Override
        public ObservableList<Record> getFilteredExpiredRecordList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpiredRecordList(Predicate<ExpiredRecord> predicate) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
