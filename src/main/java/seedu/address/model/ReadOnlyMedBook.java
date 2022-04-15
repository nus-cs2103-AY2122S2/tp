package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyMedBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPatientList();
    ObservableList<Prescription> getPrescriptionList();
    ObservableList<Medical> getMedicalList();

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate persons.
     * @return
     */
    ObservableList<Contact> getContactList();

    /**

     * Returns an unmodifiable view of the consultations list.
     * This list will not contain any duplicate consultations.
     * @return
     */
    ObservableList<Consultation> getConsultationList();

    /**
     *  Returns an unmodifiable view of the test results list.
     * This list will not contain any duplicate test results.
     * @return
     */
    ObservableList<TestResult> getTestResultList();
}
