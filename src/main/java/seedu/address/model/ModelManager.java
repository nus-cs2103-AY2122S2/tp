package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.history.AppointmentHistory;
import seedu.address.model.history.ExpiredRecord;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;

/**
 * Represents the in-memory model of the Mr. Agent data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final InsuranceBook insuranceBook;
    private final AppointmentBook appointmentBook;
    private final AppointmentHistoryBook appointmentHistoryBook;
    private final ExpiredRecordBook expiredRecordBook;
    private final RecordBook recordBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Insurance> filteredInsurances;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Appointment> filteredAppointmentHistory;
    private final FilteredList<Record> filteredRecords;
    private final FilteredList<Record> filteredExpiredRecord;


    /**
     * Initializes a ModelManager with the given storage book and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyInsuranceBook insuranceBook,
                        ReadOnlyAppointmentBook appointmentBook, ReadOnlyRecordBook recordBook,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, insuranceBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + "and insurance book" + insuranceBook + "and record book" + recordBook
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.insuranceBook = new InsuranceBook(insuranceBook);
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.appointmentHistoryBook = new AppointmentHistoryBook(appointmentBook);
        this.recordBook = new RecordBook(recordBook);
        this.expiredRecordBook = new ExpiredRecordBook(recordBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredInsurances = new FilteredList<>(this.insuranceBook.getInsuranceList());
        this.filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList())
                        .filtered(a -> a.getLocalDateTime().isAfter(LocalDateTime.now()));
        this.filteredAppointmentHistory = new FilteredList<>(this.appointmentHistoryBook.getAppointmentHistoryList());
        this.filteredRecords = new FilteredList<>(this.recordBook.getRecordList())
                .filtered(r -> r.getEndLocalDate().isAfter(LocalDate.now()));
        this.filteredExpiredRecord = new FilteredList<>(this.expiredRecordBook.getExpiredRecordList());

    }

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
        this.addressBook = new AddressBook(addressBook);
        this.insuranceBook = null;
        this.appointmentBook = null;
        this.appointmentHistoryBook = null;
        this.recordBook = null;
        this.expiredRecordBook = null;
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredInsurances = null;
        this.filteredAppointments = null;
        this.filteredAppointmentHistory = null;
        this.filteredRecords = null;
        this.filteredExpiredRecord = null;
    }

    /**
     * Initializes a ModelManager with the given insuranceBook and userPrefs.
     */
    public ModelManager(ReadOnlyInsuranceBook insuranceBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(insuranceBook, userPrefs);
        this.addressBook = null;
        this.insuranceBook = new InsuranceBook(insuranceBook);
        this.appointmentBook = null;
        this.appointmentHistoryBook = null;
        this.recordBook = null;
        this.expiredRecordBook = null;
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = null;
        this.filteredInsurances = new FilteredList<>(this.insuranceBook.getInsuranceList());
        this.filteredAppointments = null;
        this.filteredAppointmentHistory = null;
        this.filteredRecords = null;
        this.filteredExpiredRecord = null;
    }

    /**
     * Initializes a ModelManager with the given insuranceBook and userPrefs.
     */
    public ModelManager(ReadOnlyRecordBook recordBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(recordBook, userPrefs);
        this.addressBook = null;
        this.insuranceBook = null;
        this.appointmentBook = null;
        this.appointmentHistoryBook = null;
        this.recordBook = new RecordBook(recordBook);
        this.expiredRecordBook = null;
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = null;
        this.filteredInsurances = null;
        this.filteredAppointments = null;
        this.filteredAppointmentHistory = null;
        this.filteredRecords = new FilteredList<>(this.recordBook.getRecordList());
        this.filteredExpiredRecord = null;
    }

    /**
     * Initializes a ModelManager with the given insuranceBook and userPrefs.
     */
    public ModelManager(ReadOnlyAppointmentBook appointmentBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(appointmentBook, userPrefs);
        this.addressBook = null;
        this.insuranceBook = null;
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.appointmentHistoryBook = null;
        this.recordBook = null;
        this.expiredRecordBook = null;
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = null;
        this.filteredInsurances = null;
        this.filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());
        this.filteredAppointmentHistory = null;
        this.filteredRecords = null;
        this.filteredExpiredRecord = null;
    }

    public ModelManager() {
        this(new AddressBook(), new InsuranceBook(), new AppointmentBook(), new RecordBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return this.userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return this.userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return this.userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return this.addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        if (this.recordBook != null) {
            List<Record> records = this.getRecordBook().getRecordList();
            List<Record> toBeDeleted = new ArrayList<>();
            for (Record record : records) {
                if (target.getName().toString().equals(record.getClientID().toString())) {
                    toBeDeleted.add(record);
                }
            }

            for (Record record : toBeDeleted) {
                this.deleteRecord(record);
            }
        }


        this.addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        this.addressBook.addPerson(person);
        this.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        this.addressBook.setPerson(target, editedPerson);
    }

    //=========== InsuranceBook ================================================================================

    @Override
    public ReadOnlyInsuranceBook getInsuranceBook() {
        return this.insuranceBook;
    }

    @Override
    public void setInsuranceBook(ReadOnlyInsuranceBook insuranceBook) {
        this.insuranceBook.resetData(insuranceBook);
    }

    @Override
    public boolean hasInsurance(Insurance insurance) {
        requireNonNull(insurance);
        return this.insuranceBook.hasInsurance(insurance);
    }

    @Override
    public void deleteInsurance(Insurance target) {
        if (this.recordBook != null) {
            List<Record> records = this.getRecordBook().getRecordList();
            List<Record> toBeDeleted = new ArrayList<>();
            for (Record record : records) {
                if (target.getTitle().toString().equals(record.getInsuranceID().toString())) {
                    toBeDeleted.add(record);
                }
            }

            for (Record record : toBeDeleted) {
                this.deleteRecord(record);
            }
        }

        this.insuranceBook.removeInsurance(target);
    }

    @Override
    public void addInsurance(Insurance insurance) {
        this.insuranceBook.addInsurance(insurance);
        this.updateFilteredInsuranceList(PREDICATE_SHOW_ALL_INSURANCES);
    }

    @Override
    public void setInsurance(Insurance target, Insurance editedInsurance) {
        requireAllNonNull(target, editedInsurance);

        this.insuranceBook.setInsurance(target, editedInsurance);
    }

    //=========== AppointmentBook ============================================================================
    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return this.appointmentBook;
    }

    @Override
    public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.appointmentBook.resetData(appointmentBook);
    }

    @Override
    public void sortAppointmentBook(Comparator<Appointment> comparator) {
        this.appointmentBook.sortAppointment(comparator);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return this.appointmentBook.hasAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        this.appointmentBook.addAppointment(appointment);
        Predicate<Appointment> predicate = a -> a.getLocalDateTime().isAfter(LocalDateTime.now());
        this.updateFilteredAppointmentList(predicate);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        this.appointmentBook.removeAppointment(appointment);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        this.appointmentBook.setAppointment(target, editedAppointment);
    }

    //=========== RecordBook ============================================================================
    @Override
    public ReadOnlyRecordBook getRecordBook() {
        return this.recordBook;
    }

    @Override
    public void setRecordBook(ReadOnlyRecordBook recordBook) {
        this.recordBook.resetData(recordBook);
    }

    @Override
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return this.recordBook.hasRecord(record);
    }

    @Override
    public void addRecord(Record record) {
        this.recordBook.addRecord(record);
        Predicate<Record> predicate = r -> r.getEndLocalDate().isAfter(LocalDate.now());
        this.updateFilteredRecordList(predicate);
    }

    @Override
    public void deleteRecord(Record record) {
        this.recordBook.removeRecord(record);
    }

    @Override
    public void setRecord(Record target, Record editedRecord) {
        requireAllNonNull(target, editedRecord);
        this.recordBook.setRecord(target, editedRecord);
    }

    @Override
    public void sortRecordBook(Comparator<Record> comparator) {
        this.recordBook.sortRecord(comparator);
    }


    //=========== Filtered Appointment List Accessors ========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return this.filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        this.filteredAppointments.setPredicate(predicate);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return this.filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return this.addressBook.equals(other.addressBook)
                && this.userPrefs.equals(other.userPrefs)
                && this.filteredPersons.equals(other.filteredPersons);
    }


    //=========== Filtered Insurance List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Insurance} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Insurance> getFilteredInsuranceList() {
        return this.filteredInsurances;
    }

    @Override
    public void updateFilteredInsuranceList(Predicate<Insurance> predicate) {
        requireNonNull(predicate);
        this.filteredInsurances.setPredicate(predicate);
    }

    //=========== Filtered Appointment History List Accessors ========================================================

    /**
     * Returns an unmodifiable view of the list of {@code AppointmentHistory} backed by the internal list of
     * {@code versionedAddressBook}
     */

    @Override
    public ReadOnlyAppointmentHistoryBook getAppointmentHistoryBook() {
        return this.appointmentHistoryBook;
    }

    @Override
    public boolean hasHistory(AppointmentHistory history) {
        return false;
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentHistoryList() {
        return this.filteredAppointmentHistory;
    }

    @Override
    public void updateFilteredAppointmentHistoryList(Predicate<Appointment> predicate) {

    }


    //=========== Filtered Record List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Record} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Record> getFilteredRecordList() {
        return this.filteredRecords;
    }

    @Override
    public void updateFilteredRecordList(Predicate<Record> predicate) {
        requireNonNull(predicate);
        this.filteredRecords.setPredicate(predicate);
    }

    //=========== Filtered Expired Record List Accessors ========================================================

    /**
     * Returns an unmodifiable view of the list of {@code ExpiredRecord} backed by the internal list of
     * {@code versionedAddressBook}
     */

    @Override
    public ReadOnlyExpiredRecordBook getExpiredRecordBook() {
        return this.expiredRecordBook;
    }

    @Override
    public ObservableList<Record> getFilteredExpiredRecordList() {
        return this.filteredExpiredRecord;
    }

    @Override
    public void updateFilteredExpiredRecordList(Predicate<ExpiredRecord> predicate) {
    }


}
