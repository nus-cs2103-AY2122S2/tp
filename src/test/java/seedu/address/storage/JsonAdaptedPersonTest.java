package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_ID = "12345";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_COMPANY = " ";
    private static final String INVALID_JOBTITLE = "#1 Employee";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PRONOUN = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_ID = "9ddd72fa-e32e-4ed7-b475-32d4b19f6b72";
    private static final String VALID_NAME = "Benson";
    private static final String VALID_COMPANY = "Apple";
    private static final String VALID_JOBTITLE = "Accountant";
    private static final String VALID_PHONE = "98765432";
    private static final String VALID_EMAIL = "benson.murphy@example.com";
    private static final String VALID_ADDRESS = "123 Monty Carl, 326459";
    private static final List<JsonAdaptedPronoun> VALID_PRONOUNS = BENSON.getPronouns().stream()
            .map(JsonAdaptedPronoun::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final HashMap<String, JsonAdaptedAddress> validAddresses = new HashMap<>();
    private static final HashMap<String, JsonAdaptedPhone> validNumbers = new HashMap<>();
    private static final HashMap<String, JsonAdaptedEmail> validEmails = new HashMap<>();

    private static final HashMap<String, JsonAdaptedPhone> invalidNumbers = new HashMap<>();
    private static final HashMap<String, JsonAdaptedEmail> invalidEmails = new HashMap<>();
    private static final HashMap<String, JsonAdaptedAddress> invalidAddresses = new HashMap<>();
    private static final List<JsonAdaptedPronoun> invalidPronouns = new ArrayList<>(VALID_PRONOUNS);
    private static final List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);

    static {
        validAddresses.put("address#1", new JsonAdaptedAddress(VALID_ADDRESS));
        validNumbers.put("phone#1", new JsonAdaptedPhone(VALID_PHONE));
        validEmails.put("email#1", new JsonAdaptedEmail(VALID_EMAIL));

        invalidNumbers.put("phone#1", new JsonAdaptedPhone(INVALID_PHONE));
        invalidEmails.put("email#1", new JsonAdaptedEmail(INVALID_EMAIL));
        invalidAddresses.put("address#1", new JsonAdaptedAddress(INVALID_ADDRESS));
        invalidPronouns.add(new JsonAdaptedPronoun(INVALID_PRONOUN));
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    //@@author muraddurrani
    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_ID, VALID_NAME, VALID_COMPANY, VALID_JOBTITLE, validNumbers,
                        validEmails, validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    //@@author ckcherry23
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_COMPANY, VALID_JOBTITLE, validNumbers,
                        validEmails, validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, null, VALID_COMPANY, VALID_JOBTITLE,
                validNumbers, validEmails,
                validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_COMPANY, VALID_JOBTITLE, validNumbers,
                        validEmails, validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_COMPANY, INVALID_JOBTITLE, validNumbers,
                        validEmails, validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_COMPANY, VALID_JOBTITLE,
                invalidNumbers, validEmails, validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_COMPANY, VALID_JOBTITLE,
                validNumbers, invalidEmails, validAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_COMPANY, VALID_JOBTITLE,
                validNumbers, validEmails, invalidAddresses, VALID_PRONOUNS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPronouns_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_COMPANY, VALID_JOBTITLE,
                        validNumbers, validEmails, validAddresses, invalidPronouns, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_COMPANY, VALID_JOBTITLE,
                        validNumbers, validEmails, validAddresses, VALID_PRONOUNS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
