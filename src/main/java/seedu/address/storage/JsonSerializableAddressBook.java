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
import seedu.address.model.person.Person;
import seedu.address.model.testresult.TestResult;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TEST_RESULT = "Test result list contains duplicate test(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedTestResult> testResults = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, contacts and test results.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                       @JsonProperty("testResults") List<JsonAdaptedTestResult> testResults) {
        this.persons.addAll(persons);
        if (!contacts.isEmpty()) {
            this.contacts.addAll(contacts);
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
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        testResults.addAll(source.getTestResultList().stream().map(JsonAdaptedTestResult::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (addressBook.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            addressBook.addContact(contact);
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
