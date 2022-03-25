package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.UniqueConsultationList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.UniqueMedicalList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePersonList;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.UniquePrescriptionList;
import seedu.address.model.testresult.TestResult;
import seedu.address.model.testresult.UniqueTestResultList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueContactList contacts;
    private final UniqueConsultationList consultations;
    private final UniquePrescriptionList prescriptions;
    private final UniqueTestResultList testResults;
    private final UniqueMedicalList medicals;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        contacts = new UniqueContactList();
        consultations = new UniqueConsultationList();
        prescriptions = new UniquePrescriptionList();
        testResults = new UniqueTestResultList();
        medicals = new UniqueMedicalList();

    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Patient> patients) {
        this.persons.setPersons(patients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setContacts(newData.getContactList());
        setMedicals(newData.getMedicalList());
        setConsultations(newData.getConsultationList());
        setPrescriptions(newData.getPrescriptionList());
        setTestResults(newData.getTestResultList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Patient patient) {
        requireNonNull(patient);
        return persons.contains(patient);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Patient p) {
        persons.add(p);
    }

    public void addPrescription(Prescription p) {
        prescriptions.add(p);
    }
    public void removePrescription(Prescription key) {
        prescriptions.remove(key);
    }

    /**
     * Returns true if a prescription with the same identity as {@code prescription} exists in the address book.
     */
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return prescriptions.contains(prescription);
    }
    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions.setPrescriptions(prescriptions);
    }
    public void setPrescription(Prescription target, Prescription editedPrescription) {
        requireNonNull(editedPrescription);
        prescriptions.setPrescription(target, editedPrescription);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        persons.setPerson(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Patient key) {
        persons.remove(key);
    }

    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    //// consultation-level operations

    /**
     * Returns true if a consultation with the same identity as {@code consultation} exists in the address book.
     */
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return consultations.contains(consultation);
    }

    /**
     * Adds a consultation to the address book.
     * The consultation must not already exist in the address book.
     */
    public void addConsultation(Consultation p) {
        consultations.add(p);
    }

    /**
     * Replaces the given consultation {@code target} in the list with {@code editedConsultation}.
     * {@code target} must exist in the address book.
     * The consultation identity of {@code editedConsultation} must not be the same
     * as another existing consultation in the address book.
     */
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireNonNull(editedConsultation);

        consultations.setConsultation(target, editedConsultation);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeConsultation(Consultation key) {
        consultations.remove(key);
    }

    /**
     * Replaces the contents of the consultations list with {@code consultations}.
     * {@code consultations} must not contain duplicate consultations.
     */
    public void setConsultations(List<Consultation> consultations) {
        this.consultations.setConsultations(consultations);
    }

    //// contact-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a contact to the address book.
     * The contact must not already exist in the address book.
     */
    public void addContact(Contact p) {
        contacts.add(p);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not be the same
     * as another existing contact in the address book.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        contacts.setContact(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    //// util methods

    /**
     * Replaces the contents of the test results list with {@code testResults}.
     * {@code testResults} must not contain duplicate test results.
     */
    public void setTestResults(List<TestResult> testResults) {
        this.testResults.setTestResults(testResults);
    }

    /**
     * Replaces the given test result {@code target} in the list with {@code editedTestResults}.
     * {@code target} must exist in the address book.
     * The test result identity of {@code editedTestResults} must not be the same
     * as another existing test result in the address book.
     */
    public void setTestResults(TestResult target, TestResult editedTestResults) {
        requireNonNull(editedTestResults);
        testResults.setTestResult(target, editedTestResults);
    }

    //// contact-level operations

    /**
     * Returns true if a test result with the same identity as {@code testResult} exists in the address book.
     */
    public boolean hasTestResult(TestResult testResult) {
        requireNonNull(testResult);
        return testResults.contains(testResult);
    }

    /**
     * Adds a test result to the address book.
     * The test result must not already exist in the address book.
     */
    public void addTestResult(TestResult testResult) {
        testResults.add(testResult);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTestResult(TestResult key) {
        testResults.remove(key);
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + contacts.asUnmodifiableObservableList().size() + " contacts"
                + consultations.asUnmodifiableObservableList().size() + " consultations"
                + testResults.asUnmodifiableObservableList().size() + " test results";
        // TODO: refine later
    }

    @Override
    public ObservableList<Patient> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Medical> getMedicalList() {
        return medicals.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Consultation> getConsultationList() {
        return consultations.asUnmodifiableObservableList();
    }

    public ObservableList<Prescription> getPrescriptionList() {
        return prescriptions.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TestResult> getTestResultList() {
        return testResults.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons))
                && contacts.equals(((AddressBook) other).contacts)
                && consultations.equals(((AddressBook) other).consultations)
                && testResults.equals(((AddressBook) other).testResults);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }


    /**
     * Adds medical information to the address book.
     */
    public void addMedical(Medical m) {
        medicals.add(m);
    }

    /**
     * Removes {@code key} from this {@code MedBook}.
     * {@code key} must exist in MedBook.
     */
    public void removeMedical(Medical key) {
        medicals.remove(key);
    }


    public void setMedical(Medical target, Medical editedMedical) {
        requireNonNull(editedMedical);
        medicals.setMedical(target, editedMedical);
    }

    public void setMedicals(List<Medical> medicals) {
        this.medicals.setMedicals(medicals);
    }

    /**
     * Checks if medical information (for patient with same NRIC) already exists.
     */
    public boolean hasMedical(Medical m) {
        requireNonNull(m);
        return medicals.contains(m);
    }
}
