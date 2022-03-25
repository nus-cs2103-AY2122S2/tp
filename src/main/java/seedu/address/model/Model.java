package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Consultation> PREDICATE_SHOW_ALL_CONSULTATIONS = unused -> true;
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Prescription> PREDICATE_SHOW_ALL_PRESCRIPTIONS = unused -> true;
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;
    Predicate<TestResult> PREDICATE_SHOW_ALL_TEST_RESULTS = unused -> true;
    Predicate<Medical> PREDICATE_SHOW_ALL_MEDICALS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

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
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Patient patient);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Predicate<Patient> predicate);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Patient target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Patient patient);

    void addPrescription(Prescription prescription);

    void deletePrescription(Prescription target);

    boolean hasPrescription(Prescription prescription);

    void setPrescription(Prescription target, Prescription editedPrescription);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Patient> getFilteredPersonList();

    ObservableList<Medical> getFilteredMedicalList();

    ObservableList<Prescription> getFilteredPrescriptionList();

    /** Returns the person list **/
    ObservableList<Patient> getPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Patient> predicate);

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given contact.
     * The contact must exist in the address book.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the address book.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not be the same
     * as another existing contact in the address book.
     */
    void setContact(Contact target, Contact editedContact);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);


    // Consultation

    void updateFilteredPrescriptionList(Predicate<Prescription> predicate);

    // Consultation

    /**
     * Returns true if a consultation with the same identity as {@code consultation} exists in the address book.
     */
    boolean hasConsultation(Consultation consultation);

    /**
     * Deletes the given consultation.
     * The consultation must exist in the address book.
     */
    void deleteConsultation(Consultation target);

    /**
     * Adds the given consultation.
     * {@code consultation} must not already exist in the address book.
     */
    void addConsultation(Consultation consultation);

    /**
     * Replaces the given consultation {@code target} with {@code editedConsultation}.
     * {@code target} must exist in the address book.
     * The consultation identity of {@code editedConsultation} must not be the same
     * as another existing consultation in the address book.
     */
    void setConsultation(Consultation target, Consultation editedConsultation);

    /** Returns an unmodifiable view of the filtered consultation list */
    ObservableList<Consultation> getFilteredConsultationList();

    /**
     * Updates the filter of the filtered consultation list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredConsultationList(Predicate<Consultation> predicate);

    /**
     * Returns true if a test result with the same identity as {@code testResult} exists in the address book.
     */
    boolean hasTestResult(TestResult testResult);

    /**
     * Deletes the given test result.
     * The test result must exist in the address book.
     */
    void deleteTestResult(TestResult testResult);

    /**
     * Adds the given test result.
     * {@code testResult} must not already exist in the address book.
     */
    void addTestResult(TestResult testResult);

    /**
     * Replaces the given testResult {@code target} with {@code editedTestResult}.
     * {@code target} must exist in the address book.
     * The test result identity of {@code editedTestResult} must not be the same
     * as another existing test result in the address book.
     */
    void setTestResult(TestResult target, TestResult editedTestResult);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<TestResult> getFilteredTestResultList();

    /**
     * Updates the filter of the filtered test result list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTestResultList(Predicate<TestResult> predicate);

    /**
     * Adds the given medical information.
     * {@code medical} must not already exist in the address book.
     */
    void addMedical(Medical medical);

    boolean hasMedical(Medical medical);

    void deleteMedical(Medical medical);

    void setMedical(Medical target, Medical editedMedical);

    /**
     * Updates the filter of the filtered medical list to filter by the given {@code predicate}. s
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMedicalList(Predicate<Medical> predicate);
}
