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
import seedu.address.testutil.InsuranceBuilder;

public class AddInsuranceCommandTest {

    @Test
    public void constructor_nullInsurance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInsuranceCommand(null));
    }

    @Test
    public void execute_insuranceAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInsuranceAdded modelStub = new ModelStubAcceptingInsuranceAdded();
        Insurance validInsurance = new InsuranceBuilder().build();

        CommandResult commandResult = new AddInsuranceCommand(validInsurance).execute(modelStub);

        String expected = String.format(AddInsuranceCommand.MESSAGE_SUCCESS, validInsurance);
        assertEquals(expected, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInsurance), modelStub.insurancesAdded);
    }

    @Test
    public void execute_duplicateInsurance_throwsCommandException() {
        Insurance validInsurance = new InsuranceBuilder().build();
        AddInsuranceCommand addInsuranceCommand = new AddInsuranceCommand(validInsurance);
        ModelStub modelStub = new ModelStubWithInsurance(validInsurance);

        assertThrows(CommandException.class,
                AddInsuranceCommand.MESSAGE_DUPLICATE_INSURANCE, () -> addInsuranceCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Insurance heart = new InsuranceBuilder().withTitle("Heart").build();
        Insurance life = new InsuranceBuilder().withTitle("Life").build();
        AddInsuranceCommand addHeartCommand = new AddInsuranceCommand(heart);
        AddInsuranceCommand addLifeCommand = new AddInsuranceCommand(life);

        // same object -> returns true
        assertTrue(addHeartCommand.equals(addHeartCommand));

        // same values -> returns true
        AddInsuranceCommand addHeartCommandCopy = new AddInsuranceCommand(heart);
        assertTrue(addHeartCommand.equals(addHeartCommandCopy));

        // different types -> returns false
        assertFalse(addHeartCommand.equals(1));

        // null -> returns false
        assertFalse(addHeartCommand.equals(null));

        // different insurance -> returns false
        assertFalse(addHeartCommand.equals(addLifeCommand));
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
     * A Model stub that contains a single insurance.
     */
    private class ModelStubWithInsurance extends ModelStub {
        private final Insurance insurance;

        ModelStubWithInsurance(Insurance insurance) {
            requireNonNull(insurance);
            this.insurance = insurance;
        }

        @Override
        public boolean hasInsurance(Insurance insurance) {
            requireNonNull(insurance);
            return this.insurance.isSameInsurance(insurance);
        }
    }

    /**
     * A Model stub that always accept the insurance being added.
     */
    private class ModelStubAcceptingInsuranceAdded extends ModelStub {
        final ArrayList<Insurance> insurancesAdded = new ArrayList<>();

        @Override
        public boolean hasInsurance(Insurance insurance) {
            requireNonNull(insurance);
            return insurancesAdded.stream().anyMatch(insurance::isSameInsurance);
        }

        @Override
        public void addInsurance(Insurance insurance) {
            requireNonNull(insurance);
            insurancesAdded.add(insurance);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
