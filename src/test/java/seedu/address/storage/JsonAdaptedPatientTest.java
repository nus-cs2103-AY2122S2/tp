package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NRIC = "L1231237L";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private final String validNric = BENSON.getNric().toString();
    private final String validName = BENSON.getName().toString();
    private final String validPhone = BENSON.getPhone().toString();
    private final String validEmail = BENSON.getEmail().toString();
    private final String validAddress = BENSON.getAddress().toString();
    private final List<JsonAdaptedTag> validTags = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient person = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(validNric, INVALID_NAME, validPhone, validEmail,
                        validAddress, validTags);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(validNric, null, validPhone, validEmail,
                validAddress, validTags);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(INVALID_NRIC, validName, validPhone, validEmail,
                        validAddress, validTags);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(null, validName, validPhone, validEmail,
                validAddress, validTags);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(validNric, validName, INVALID_PHONE, validEmail,
                        validAddress, validTags);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(validNric, validName, null, validEmail,
                validAddress, validTags);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(validNric, validName, validPhone, INVALID_EMAIL,
                        validAddress, validTags);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(validNric, validName, validPhone, null,
                validAddress, validTags);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(validNric, validName, validPhone, validEmail,
                        INVALID_ADDRESS, validTags);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(validNric, validName, validPhone,
                validEmail, null, validTags);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(validTags);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(validNric, validName, validPhone, validEmail,
                        validAddress, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
