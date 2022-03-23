package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static unibook.storage.adaptedmodeltypes.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.storage.adaptedmodeltypes.JsonAdaptedModuleCode;
import unibook.storage.adaptedmodeltypes.JsonAdaptedPerson;
import unibook.storage.adaptedmodeltypes.JsonAdaptedTag;
import unibook.testutil.Assert;
import unibook.testutil.TypicalPersons;
import unibook.testutil.UniBookBuilder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalPersons.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalPersons.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalPersons.BENSON.getEmail().toString();
    private static final Set<JsonAdaptedTag> VALID_TAGS = TypicalPersons.BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toSet());
    //TODO enhance this test, may need to change UniBookBuilder
    private static final Set<JsonAdaptedModuleCode> VALID_MODULES = new HashSet<>();

    private UniBookBuilder uniBookBuilder = new UniBookBuilder();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TypicalPersons.BENSON);
        assertEquals(TypicalPersons.BENSON, person.toModelType(uniBookBuilder.build()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_TAGS,
            VALID_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS, VALID_MODULES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_TAGS, VALID_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        Set<JsonAdaptedTag> invalidTags = new HashSet<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags, VALID_MODULES);
        Assert.assertThrows(IllegalValueException.class, () -> person.toModelType(
            uniBookBuilder.build()));
    }

    //TODO invalid modules test

}
