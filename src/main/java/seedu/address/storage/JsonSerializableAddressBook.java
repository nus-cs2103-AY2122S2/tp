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
import seedu.address.model.contact.Contact;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "Prescription list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and contacts.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                       @JsonProperty("prescriptions") List<JsonAdaptedPrescription> prescriptions) {
        this.persons.addAll(persons);
        if (!contacts.isEmpty()) {
            this.contacts.addAll(contacts);
        }
        if (!prescriptions.isEmpty()) {
            this.prescriptions.addAll(prescriptions);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        prescriptions.addAll(source.getPrescriptionList().stream().map(JsonAdaptedPrescription::new)
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
            if (addressBook.hasPerson(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(patient);
        }
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (addressBook.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            addressBook.addContact(contact);
        }
        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (addressBook.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            addressBook.addPrescription(prescription);
        }
        return addressBook;
    }

}
