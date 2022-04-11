package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Priority;

public class CsvAdaptedPersonTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_INSURANCE_PACKAGE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_INSURANCE_PACKAGE = BENSON.getInsurancePackage().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<CsvAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(CsvAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        CsvAdaptedPerson person = new CsvAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidInsurancePackage_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_INSURANCE_PACKAGE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = InsurancePackage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullInsurancePackage_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InsurancePackage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<CsvAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new CsvAdaptedTag(INVALID_TAG, Priority.PRIORITY_1));
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_INSURANCE_PACKAGE, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toCsvStringAndBack_noExceptionThrown() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(BENSON);
        String personString = person.toCsvString();
        assertEquals(person, new CsvAdaptedPerson(personString));
    }

    @Test
    public void tagsToStringTests() {
        ArrayList<CsvAdaptedTag> tags = new ArrayList<>();
        tags.add(new CsvAdaptedTag("Family", null));
        tags.add(new CsvAdaptedTag("Brother", Priority.PRIORITY_2));

        String tagsString = CsvAdaptedPerson.getTagsAsString(tags);
        assertEquals("Family|Brother[P2]", tagsString);

        tags.add(new CsvAdaptedTag("Owe Money", Priority.PRIORITY_3));
        tagsString = CsvAdaptedPerson.getTagsAsString(tags);
        assertEquals("Family|Brother[P2]|Owe Money[P3]", tagsString);
    }

    @Test
    public void cleanUpTests() {
        assertEquals("", CsvAdaptedPerson.cleanup("")); // empty string
        assertEquals("", CsvAdaptedPerson.cleanup("\"\"")); // string with just quotes
        assertEquals("John Doe", CsvAdaptedPerson.cleanup("\"John Doe\""));
    }
}
