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
import seedu.address.model.person.Email;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentNumber;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_NUMBER = "+651234";
    private static final String INVALID_MOD = "example.com";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_GROUP = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENT_NUMBER = BENSON.getStudentNumber().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GROUP = BENSON.getGroup().toString();
    private static final String VALID_MOD = BENSON.getMod().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_STUDENT_NUMBER, VALID_EMAIL, VALID_MOD, VALID_GROUP,
                        VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_STUDENT_NUMBER, VALID_EMAIL, VALID_MOD,
                VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_STUDENT_NUMBER, VALID_EMAIL,
                        VALID_MOD, VALID_GROUP, VALID_TAGS);
        String expectedMessage = StudentNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_MOD,
                VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_STUDENT_NUMBER, INVALID_EMAIL, VALID_MOD,
                VALID_GROUP, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_STUDENT_NUMBER, null, VALID_MOD,
                VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMod_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_STUDENT_NUMBER, VALID_EMAIL, INVALID_MOD,
                        VALID_GROUP, VALID_TAGS);
        String expectedMessage = Mod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_STUDENT_NUMBER, VALID_EMAIL, null,
                VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Mod.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_STUDENT_NUMBER,
                        VALID_EMAIL, VALID_MOD, VALID_GROUP, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
