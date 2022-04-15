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
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactWithNricPredicate;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.MedicalWithNricPredicate;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionWithNricPredicate;
import seedu.address.model.testresult.TestResult;
import seedu.address.model.testresult.TestResultWithNricPredicate;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MedBook medBook;
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
    public ModelManager(ReadOnlyMedBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);
        this.medBook = new MedBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredConsultations = new FilteredList<>(this.medBook.getConsultationList());
        filteredPatients = new FilteredList<>(this.medBook.getPatientList());
        filteredPrescription = new FilteredList<>(this.medBook.getPrescriptionList());
        filteredContacts = new FilteredList<>(this.medBook.getContactList());
        filteredTestResults = new FilteredList<>(this.medBook.getTestResultList());
        filteredMedicals = new FilteredList<>(this.medBook.getMedicalList());
    }

    public ModelManager() {
        this(new MedBook(), new UserPrefs());
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
    public Path getMedBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setMedBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setMedBook(ReadOnlyMedBook medBook) {
        this.medBook.resetData(medBook);
    }

    @Override
    public ReadOnlyMedBook getMedBook() {
        return medBook;
    }


    //=========== Patient ================================================================================

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return medBook.hasPatient(patient);
    }

    @Override
    public boolean hasNric(Nric nric) {
        requireNonNull(nric);
        return medBook.hasNric(nric);
    }

    @Override
    public void deletePatient(Patient target) {
        medBook.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        medBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    //=========== Filtered Patient List Accessors =============================================================
    //@@author
    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return medBook.getPatientList();
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        medBook.setPatient(target, editedPatient);
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Contact ================================================================================
    //@@author clement0010
    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return medBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        medBook.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        medBook.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        medBook.setContact(target, editedContact);
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

    //=========== Medical ================================================================================
    //@@author sibinhho99-nus
    @Override
    public void addMedical(Medical medical) {
        medBook.addMedical(medical);
        updateFilteredMedicalList(PREDICATE_SHOW_ALL_MEDICALS);
    }

    @Override
    public boolean hasMedical(Medical medical) {
        requireNonNull(medical);
        return medBook.hasMedical(medical);
    }

    @Override
    public void deleteMedical(Medical target) {
        medBook.removeMedical(target);
    }

    @Override
    public void setMedical(Medical target, Medical editedMedical) {
        requireAllNonNull(target, editedMedical);

        medBook.setMedical(target, editedMedical);
    }

    @Override
    public ObservableList<Medical> getFilteredMedicalList() {
        return filteredMedicals;
    }

    @Override
    public void updateFilteredMedicalList(Predicate<Medical> predicate) {
        requireNonNull(predicate);
        filteredMedicals.setPredicate(predicate);
    }

    //=========== Prescription ======================================================
    //@@author cheekean5848
    @Override
    public void addPrescription(Prescription prescription) {
        medBook.addPrescription(prescription);
        updateFilteredPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);
    }

    @Override
    public void deletePrescription(Prescription prescription) {
        medBook.removePrescription(prescription);
    }

    @Override
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return medBook.hasPrescription(prescription);
    }

    @Override
    public void setPrescription(Prescription target, Prescription editedPrescription) {
        requireAllNonNull(target, editedPrescription);
        medBook.setPrescription(target, editedPrescription);
    }

    //=========== Filtered Prescription List Accessors ======================================================

    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return filteredPrescription;
    }

    @Override
    public void updateFilteredPrescriptionList(Predicate<Prescription> predicate) {
        requireNonNull(predicate);
        filteredPrescription.setPredicate(predicate);
    }

    //=========== TestResult ================================================================================
    //@@author joey-chance
    @Override
    public boolean hasTestResult(TestResult testResult) {
        requireNonNull(testResult);
        return medBook.hasTestResult(testResult);
    }

    @Override
    public void deleteTestResult(TestResult target) {
        medBook.removeTestResult(target);
    }

    @Override
    public void addTestResult(TestResult testResult) {
        medBook.addTestResult(testResult);
        updateFilteredTestResultList(PREDICATE_SHOW_ALL_TEST_RESULTS);
    }

    @Override
    public void setTestResult(TestResult target, TestResult editedTestResult) {
        requireAllNonNull(target, editedTestResult);

        medBook.setTestResults(target, editedTestResult);
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

    //=========== Consultation ================================================================================
    //@@author juslam19
    @Override
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return medBook.hasConsultation(consultation);
    }

    @Override
    public void deleteConsultation(Consultation target) {
        medBook.removeConsultation(target);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        medBook.addConsultation(consultation);
        updateFilteredConsultationList(PREDICATE_SHOW_ALL_CONSULTATIONS);
    }

    @Override
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        medBook.setConsultation(target, editedConsultation);
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

    //=========== Summary ======================================================================================
    //@@author sibinhho99-nus
    @Override
    public void updateSummary(Nric nric) {
        // Update all internal lists with Nric predicates
        updateFilteredContactList(new ContactWithNricPredicate(nric));
        updateFilteredMedicalList(new MedicalWithNricPredicate(nric));
        updateFilteredPrescriptionList(new PrescriptionWithNricPredicate(nric));
        updateFilteredTestResultList(new TestResultWithNricPredicate(nric));
        updateFilteredConsultationList(new ConsultationWithPredicates(nric));
        updateFilteredPatientList(new NricPredicate(nric));
    }

    //=========== Other Accessors ==============================================================================
    //@@author
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
        return medBook.equals(other.medBook)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts)
                && filteredConsultations.equals(other.filteredConsultations)
                && filteredPatients.equals(other.filteredPatients)
                && filteredMedicals.equals(other.filteredMedicals)
                && filteredPrescription.equals(other.filteredPrescription)
                && filteredTestResults.equals(other.filteredTestResults);
    }

}
