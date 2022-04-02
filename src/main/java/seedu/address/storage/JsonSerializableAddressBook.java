package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MEDICAL =
            "Medical information list contains duplicate medical details.";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation list contains duplicate consultation(s).";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "Prescription list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TEST_RESULT = "Test result list contains duplicate test(s).";
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedMedical> medicals = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consultations = new ArrayList<>();
    private final List<JsonAdaptedTestResult> testResults = new ArrayList<>();
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, contacts and test results.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
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
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPatientList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
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
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Patient patient = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPatient(patient);
        }
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (addressBook.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            addressBook.addContact(contact);
        }
        for (JsonAdaptedMedical jsonAdaptedMedical: medicals) {
            Medical medical = jsonAdaptedMedical.toModelType();
            if (addressBook.hasMedical(medical)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEDICAL);
            }
            addressBook.addMedical(medical);
        }
        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (addressBook.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            addressBook.addPrescription(prescription);
        }
        for (JsonAdaptedConsultation jsonAdaptedConsultation : consultations) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (addressBook.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            addressBook.addConsultation(consultation);
        }
        for (JsonAdaptedTestResult jsonAdaptedTestResult : testResults) {
            TestResult testResult = jsonAdaptedTestResult.toModelType();
            if (addressBook.hasTestResult(testResult)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEST_RESULT);
            }
            addressBook.addTestResult(testResult);
        }
        return addressBook;
    }

}
