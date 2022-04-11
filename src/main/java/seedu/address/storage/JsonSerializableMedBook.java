package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MedBook;
import seedu.address.model.ReadOnlyMedBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableMedBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MEDICAL =
            "Medical information list contains duplicate medical details.";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation list contains duplicate consultation(s).";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "Prescription list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TEST_RESULT = "Test result list contains duplicate test(s).";
    private final List<JsonAdaptedPatient> persons = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedMedical> medicals = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();
    private final List<JsonAdaptedTestResult> testResults = new ArrayList<>();
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, contacts and test results.
     */
    @JsonCreator
    public JsonSerializableMedBook(@JsonProperty("persons") List<JsonAdaptedPatient> persons,
                                   @JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                   @JsonProperty("medicals") List<JsonAdaptedMedical> medicals,
                                   @JsonProperty("consultations") List<JsonAdaptedConsultation> consultations,
                                   @JsonProperty("prescriptions") List<JsonAdaptedPrescription> prescriptions,
                                   @JsonProperty("testResults") List<JsonAdaptedTestResult> testResults) {
        this.persons.addAll(persons);
        if (!contacts.isEmpty()) {
            this.contacts.addAll(contacts);
        }
        if (!medicals.isEmpty()) {
            this.medicals.addAll(medicals);
        }
        if (!consultations.isEmpty()) {
            this.consultations.addAll(consultations);
        }
        if (!prescriptions.isEmpty()) {
            this.prescriptions.addAll(prescriptions);
        }
        if (!testResults.isEmpty()) {
            this.testResults.addAll(testResults);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableMedBook(ReadOnlyMedBook source) {
        persons.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        medicals.addAll(source.getMedicalList().stream().map(JsonAdaptedMedical::new).collect(Collectors.toList()));
        prescriptions.addAll(source.getPrescriptionList().stream().map(JsonAdaptedPrescription::new)
                .collect(Collectors.toList()));
        consultations.addAll(source.getConsultationList().stream().map(
                                JsonAdaptedConsultation::new).collect(Collectors.toList()));
        testResults.addAll(source.getTestResultList().stream().map(JsonAdaptedTestResult::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MedBook toModelType() throws IllegalValueException {
        MedBook medBook = new MedBook();
        for (JsonAdaptedPatient jsonAdaptedPatient : persons) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (medBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            medBook.addPatient(patient);
        }
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (medBook.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            medBook.addContact(contact);
        }
        for (JsonAdaptedMedical jsonAdaptedMedical: medicals) {
            Medical medical = jsonAdaptedMedical.toModelType();
            if (medBook.hasMedical(medical)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEDICAL);
            }
            medBook.addMedical(medical);
        }
        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (medBook.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            medBook.addPrescription(prescription);
        }
        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (medBook.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            medBook.addConsultation(consultation);
        }
        for (JsonAdaptedTestResult jsonAdaptedTestResult : testResults) {
            TestResult testResult = jsonAdaptedTestResult.toModelType();
            if (medBook.hasTestResult(testResult)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEST_RESULT);
            }
            medBook.addTestResult(testResult);
        }
        return medBook;
    }

}
