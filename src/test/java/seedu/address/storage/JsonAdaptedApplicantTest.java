package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.HiredStatus;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;

public class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_AGE = "twenty";
    private static final String LONG_INVALID_AGE = "100";
    private static final String SHORT_INVALID_AGE = "0";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_GENDER = "O";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_HIRED_STATUS = BENSON.getStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AGE, VALID_ADDRESS, VALID_GENDER,
                        VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(null, VALID_PHONE, VALID_EMAIL, VALID_AGE, VALID_ADDRESS,
                VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_AGE, VALID_ADDRESS, VALID_GENDER,
                        VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, null, VALID_EMAIL, VALID_AGE, VALID_ADDRESS,
                VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_AGE, VALID_ADDRESS, VALID_GENDER,
                        VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, null, VALID_AGE, VALID_ADDRESS,
                VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        JsonAdaptedApplicant personWithNonIntAge =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_AGE, VALID_ADDRESS, VALID_GENDER,
                        VALID_TAGS, VALID_HIRED_STATUS);
        assertThrows(IllegalValueException.class, expectedMessage, personWithNonIntAge::toModelType);
        JsonAdaptedApplicant personWithLongAge =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, LONG_INVALID_AGE, VALID_ADDRESS,
                        VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        assertThrows(IllegalValueException.class, expectedMessage, personWithLongAge::toModelType);
        JsonAdaptedApplicant personWithShortAge =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, SHORT_INVALID_AGE, VALID_ADDRESS,
                        VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        assertThrows(IllegalValueException.class, expectedMessage, personWithShortAge::toModelType);
    }

    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_ADDRESS, VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AGE, INVALID_ADDRESS, VALID_GENDER,
                        VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AGE, null,
                VALID_GENDER, VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AGE, VALID_ADDRESS, INVALID_GENDER,
                        VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AGE,
                VALID_ADDRESS, null, VALID_TAGS, VALID_HIRED_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_AGE,
                VALID_ADDRESS, VALID_GENDER, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, HiredStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}

