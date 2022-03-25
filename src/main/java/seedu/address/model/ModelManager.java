package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;

    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Consultation> filteredConsultations;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Prescription> filteredPrescription;
    private final FilteredList<Medical> filteredMedicals;
    private final FilteredList<TestResult> filteredTestResults;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredConsultations = new FilteredList<>(this.addressBook.getConsultationList());
        filteredPatients = new FilteredList<>(this.addressBook.getPersonList());
        filteredPrescription = new FilteredList<>(this.addressBook.getPrescriptionList());
        filteredContacts = new FilteredList<>(this.addressBook.getContactList());
        filteredTestResults = new FilteredList<>(this.addressBook.getTestResultList());
        filteredMedicals = new FilteredList<>(this.addressBook.getMedicalList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }


    //=========== Person ================================================================================

    @Override
    public boolean hasPerson(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPerson(patient);
    }

    @Override
    public boolean hasPerson(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        return !filteredPatients.filtered(predicate).isEmpty();
    }

    @Override
    public void deletePerson(Patient target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Patient patient) {
        addressBook.addPerson(patient);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPrescription(Prescription prescription) {
        addressBook.addPrescription(prescription);
        updateFilteredPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);
    }
    @Override
    public void deletePrescription(Prescription prescription) {
        addressBook.removePrescription(prescription);
    }

    @Override
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return addressBook.hasPrescription(prescription);
    }
    @Override
    public void setPrescription(Prescription target, Prescription editedPrescription) {
        requireAllNonNull(target, editedPrescription);
        addressBook.setPrescription(target, editedPrescription);
    }

    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return filteredPrescription;
    }

    @Override
    public void addMedical(Medical medical) {
        addressBook.addMedical(medical);
        updateFilteredMedicalList(PREDICATE_SHOW_ALL_MEDICALS);
    }

    @Override
    public boolean hasMedical(Medical medical) {
        requireNonNull(medical);
        return addressBook.hasMedical(medical);
    }


    @Override
    public void deleteMedical(Medical target) {
        addressBook.removeMedical(target);
    }

    @Override
    public void setMedical(Medical target, Medical editedMedical) {
        requireAllNonNull(target, editedMedical);

        addressBook.setMedical(target, editedMedical);
    }

    @Override
    public ObservableList<Medical> getFilteredMedicalList() {
        return filteredMedicals;
    }

    @Override
    public void setPerson(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPerson(target, editedPatient);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPersonList() {
        return filteredPatients;
    }

    @Override
    public ObservableList<Patient> getPersonList() {
        return addressBook.getPersonList();
    }

    @Override
    public void updateFilteredPersonList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Contact ================================================================================

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return addressBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        addressBook.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        addressBook.setContact(target, editedContact);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    //=========== TestResult ================================================================================

    @Override
    public boolean hasTestResult(TestResult testResult) {
        requireNonNull(testResult);
        return addressBook.hasTestResult(testResult);
    }

    @Override
    public void deleteTestResult(TestResult target) {
        addressBook.removeTestResult(target);
    }

    @Override
    public void addTestResult(TestResult testResult) {
        addressBook.addTestResult(testResult);
        updateFilteredTestResultList(PREDICATE_SHOW_ALL_TEST_RESULTS);
    }

    @Override
    public void setTestResult(TestResult target, TestResult editedTestResult) {
        requireAllNonNull(target, editedTestResult);

        addressBook.setTestResults(target, editedTestResult);
    }


    //=========== Filtered Test Result List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<TestResult> getFilteredTestResultList() {
        return filteredTestResults;
    }

    @Override
    public void updateFilteredTestResultList(Predicate<TestResult> predicate) {
        requireNonNull(predicate);
        filteredTestResults.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPrescriptionList(Predicate<Prescription> predicate) {
        requireNonNull(predicate);
        filteredPrescription.setPredicate(predicate);
    }

    //=========== Consultation ================================================================================

    @Override
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return addressBook.hasConsultation(consultation);
    }

    @Override
    public void deleteConsultation(Consultation target) {
        addressBook.removeConsultation(target);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        addressBook.addConsultation(consultation);
        updateFilteredConsultationList(PREDICATE_SHOW_ALL_CONSULTATIONS);
    }

    @Override
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        addressBook.setConsultation(target, editedConsultation);
    }


    //=========== Filtered Consultation List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Consultation} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Consultation> getFilteredConsultationList() {
        return filteredConsultations;
    }

    @Override
    public void updateFilteredConsultationList(Predicate<Consultation> predicate) {
        requireNonNull(predicate);
        filteredConsultations.setPredicate(predicate);
    }

    //=========== Other Accessors ==============================================================================

    @Override
    public void updateFilteredMedicalList(Predicate<Medical> predicate) {
        requireNonNull(predicate);
        filteredMedicals.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts)
                && filteredConsultations.equals(other.filteredConsultations)
                && filteredPatients.equals(other.filteredPatients)
                && filteredMedicals.equals(other.filteredMedicals)
                && filteredPrescription.equals(other.filteredPrescription)
                && filteredTestResults.equals(other.filteredTestResults);
    }

}
