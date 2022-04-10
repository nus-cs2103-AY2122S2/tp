package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.history.AppointmentHistory;
import seedu.address.model.history.ExpiredRecord;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Insurance> PREDICATE_SHOW_ALL_INSURANCES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENT_HISTORY = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<ExpiredRecord> PREDICATE_SHOW_ALL_EXPIRED_RECORD = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Record> PREDICATE_SHOW_ALL_RECORDS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns the InsuranceBook
     */
    ReadOnlyInsuranceBook getInsuranceBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setInsuranceBook(ReadOnlyInsuranceBook insuranceBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasInsurance(Insurance insurance);

    /**
     * Deletes the given insurance.
     * The insurance must exist in the address book.
     */
    void deleteInsurance(Insurance target);

    /**
     * Adds the given insurance.
     * {@code insurance} must not already exist in the address book.
     */
    void addInsurance(Insurance insurance);

    /**
     * Replaces the given person {@code target} with {@code editedInsurance}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedInsurance} must not be the same as another
     * existing person in the address book.
     */
    void setInsurance(Insurance target, Insurance editedInsurance);


    /**
     * Returns an unmodifiable view of the filtered insurance list
     */
    ObservableList<Insurance> getFilteredInsuranceList();

    /**
     * Updates the filter of the filtered insurance list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInsuranceList(Predicate<Insurance> predicate);

    //=========== AppointmentHistoryBook ============================================================================
    /**
     * Returns the HistoryBook
     */
    ReadOnlyAppointmentHistoryBook getAppointmentHistoryBook();

    /**
     * Returns true if history exists in the book.
     */
    boolean hasHistory(AppointmentHistory history);

    /**
     * Returns an unmodifiable view of the filtered appointment history list
     * @return
     */
    ObservableList<Appointment> getFilteredAppointmentHistoryList();

    /**
     * Updates the filter of the filtered history list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentHistoryList(Predicate<Appointment> predicate);

    //=========== ExpiredRecordBook ============================================================================
    /**
     * Returns the ExpiredRecord
     */
    ReadOnlyExpiredRecordBook getExpiredRecordBook();

    //    /**
    //     * Returns true if record exists in the book.
    //     */
    //    boolean hasRecord(ExpiredRecord record);

    /**
     * Returns an unmodifiable view of the filtered appointment record list
     * @return
     */
    ObservableList<Record> getFilteredExpiredRecordList();

    /**
     * Updates the filter of the filtered record list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpiredRecordList(Predicate<ExpiredRecord> predicate);

    //=========== AppointmentBook ============================================================================
    /**
     * Returns the AppointmentBook
     */
    ReadOnlyAppointmentBook getAppointmentBook();

    /**
     * Replaces address book data with the data in {@code AppointmentBook}.
     */
    void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook);

    /**
     * Sorts appointment book data.
     */
    void sortAppointmentBook(Comparator<Appointment> comparator);

    /**
     * Returns true if an appointment with the same identity as {@code person} exists in the book.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Adds the given appointment.
     */
    void addAppointment(Appointment appointment);

    void deleteAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The appointment identity of {@code editedAppointment} must not be the same as another
     * existing appointment in the appointment book.
     */
    void setAppointment(Appointment target, Appointment editedInsurance);

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);


    //=========== RecordBook ============================================================================
    /**
     * Returns the RecordBook
     */
    ReadOnlyRecordBook getRecordBook();

    /**
     * Replaces address book data with the data in {@code RecorBook}.
     */
    void setRecordBook(ReadOnlyRecordBook recordBook);

    /**
     * Returns true if an aRecord with the same identity as {@code person} exists in the book.
     */
    boolean hasRecord(Record record);

    /**
     * Adds the given record.
     */
    void addRecord(Record record);

    void deleteRecord(Record record);

    /**
     * Sorts record book data.
     */
    void sortRecordBook(Comparator<Record> comparator);

    /**
     * Replaces the given record {@code target} with {@code editedRecord}.
     * {@code target} must exist in the record book.
     * The record identity of {@code editedRecord} must not be the same as another
     * existing record in the record book.
     */
    void setRecord(Record target, Record editedRecord);

    /**
     * Returns an unmodifiable view of the filtered record list
     */
    ObservableList<Record> getFilteredRecordList();

    /**
     * Updates the filter of the filtered record list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecordList(Predicate<Record> predicate);
}
