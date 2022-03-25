package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static unibook.storage.adaptedmodeltypes.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.storage.adaptedmodeltypes.JsonAdaptedModuleCode;
import unibook.storage.adaptedmodeltypes.JsonAdaptedProfessor;
import unibook.storage.adaptedmodeltypes.JsonAdaptedTag;
import unibook.testutil.Assert;
import unibook.testutil.typicalclasses.TypicalProfessors;
import unibook.testutil.typicalclasses.TypicalUniBook;

public class JsonAdaptedProfessorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalProfessors.BENJAMIN.getName().toString();
    private static final String VALID_PHONE = TypicalProfessors.BENJAMIN.getPhone().toString();
    private static final String VALID_EMAIL = TypicalProfessors.BENJAMIN.getEmail().toString();
    private static final Set<JsonAdaptedTag> VALID_TAGS = TypicalProfessors.BENJAMIN.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toSet());
    private static final Set<JsonAdaptedModuleCode> VALID_MODULES = new HashSet<>();
    private static final String VALID_OFFICE = TypicalProfessors.BENJAMIN.getOffice().toString();

    @Test
    public void toModelType_validPersonDetails_returnsStudent() throws Exception {
        JsonAdaptedProfessor prof = new JsonAdaptedProfessor(TypicalProfessors.BENJAMIN);
        assertEquals(TypicalProfessors.BENJAMIN, prof.toModelType(TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProfessor prof =
            new JsonAdaptedProfessor(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES, VALID_OFFICE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProfessor prof = new JsonAdaptedProfessor(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS,
            VALID_MODULES, VALID_OFFICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedProfessor prof =
            new JsonAdaptedProfessor(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES,
                VALID_OFFICE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedProfessor prof = new JsonAdaptedProfessor(VALID_NAME, null, VALID_EMAIL, VALID_TAGS,
            VALID_MODULES, VALID_OFFICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedProfessor prof =
            new JsonAdaptedProfessor(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS, VALID_MODULES, VALID_OFFICE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedProfessor prof = new JsonAdaptedProfessor(VALID_NAME, VALID_PHONE, null, VALID_TAGS,
            VALID_MODULES, VALID_OFFICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidOffice_throwsIllegalValueException() {
        JsonAdaptedProfessor prof = new JsonAdaptedProfessor(VALID_NAME, VALID_PHONE, null, VALID_TAGS,
            VALID_MODULES, VALID_OFFICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        Set<JsonAdaptedTag> invalidTags = new HashSet<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProfessor prof =
            new JsonAdaptedProfessor(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags, VALID_MODULES, VALID_OFFICE);
        Assert.assertThrows(IllegalValueException.class, () -> prof.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    //TODO invalid modules test

}
