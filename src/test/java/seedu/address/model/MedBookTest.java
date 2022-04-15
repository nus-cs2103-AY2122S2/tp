package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalMedBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePersonException;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;
import seedu.address.testutil.PatientBuilder;

public class MedBookTest {

    private final MedBook medBook = new MedBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), medBook.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        MedBook newData = getTypicalMedBook();
        medBook.resetData(newData);
        assertEquals(newData, medBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        MedBookStub newData = new MedBookStub(newPatients);

        assertThrows(DuplicatePersonException.class, () -> medBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medBook.hasPatient(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(medBook.hasPatient(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        medBook.addPatient(ALICE);
        assertTrue(medBook.hasPatient(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        medBook.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(medBook.hasPatient(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> medBook.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class MedBookStub implements ReadOnlyMedBook {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<TestResult> testResults = FXCollections.observableArrayList();
        private final ObservableList<Medical> medicals = FXCollections.observableArrayList();
        private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
        private final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

        MedBookStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<Prescription> getPrescriptionList() {
            return prescriptions;
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Consultation> getConsultationList() {
            return consultations;
        }

        @Override
        public ObservableList<TestResult> getTestResultList() {
            return testResults;
        }

        @Override
        public ObservableList<Medical> getMedicalList() {
            return medicals;

        }
    }

}
