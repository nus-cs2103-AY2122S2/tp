package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static unibook.storage.adaptedmodeltypes.JsonAdaptedPerson.MODULE_DOES_NOT_EXIST_MESSAGE;
import static unibook.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.storage.adaptedmodeltypes.JsonAdaptedGroupCode;
import unibook.storage.adaptedmodeltypes.JsonAdaptedModuleCode;
import unibook.storage.adaptedmodeltypes.JsonAdaptedStudent;
import unibook.storage.adaptedmodeltypes.JsonAdaptedTag;
import unibook.testutil.Assert;
import unibook.testutil.typicalclasses.TypicalStudents;
import unibook.testutil.typicalclasses.TypicalUniBook;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalStudents.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalStudents.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalStudents.BENSON.getEmail().toString();
    private static final Set<JsonAdaptedTag> VALID_TAGS = TypicalStudents.BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toSet());
    private static final Set<JsonAdaptedModuleCode> VALID_MODULES = new HashSet<>();
    private static final Set<JsonAdaptedGroupCode> VALID_GROUPS = new HashSet<>();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(TypicalStudents.BENSON);
        assertEquals(TypicalStudents.BENSON, person.toModelType(TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
            new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES, VALID_GROUPS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS,
            VALID_MODULES, VALID_GROUPS);
        String expectedMessage =
            String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person =
            new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES, VALID_GROUPS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_TAGS,
            VALID_MODULES, VALID_GROUPS);
        String expectedMessage =
            String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person =
            new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS, VALID_MODULES, VALID_GROUPS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_TAGS,
            VALID_MODULES, VALID_GROUPS);
        String expectedMessage =
            String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        Set<JsonAdaptedTag> invalidTags = new HashSet<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent person =
            new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags, VALID_MODULES, VALID_GROUPS);
        assertThrows(IllegalValueException.class, () -> person.toModelType(
            TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_duplicateGroups_throwsIllegalValueException() {
        Set<JsonAdaptedGroupCode> duplicateGroups = new HashSet<>();
        //add 2 identical group codes
        duplicateGroups.add(new JsonAdaptedGroupCode(new JsonAdaptedModuleCode("CS2103"), "T12"));
        duplicateGroups.add(new JsonAdaptedGroupCode(new JsonAdaptedModuleCode("CS2103"), "T12"));

        JsonAdaptedStudent person =
            new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS, VALID_MODULES, duplicateGroups);

        assertThrows(IllegalValueException.class, () -> person.toModelType(TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_nonExistentModule_throwsIllegalValueException() {
        JsonAdaptedModuleCode nonExistentModule = new JsonAdaptedModuleCode("XXXX");
        Set<JsonAdaptedModuleCode> modules = new HashSet<>();
        modules.add(nonExistentModule);
        JsonAdaptedStudent student =
            new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS, modules, VALID_GROUPS);
        Assert.assertThrows(IllegalValueException.class,
            String.format(MODULE_DOES_NOT_EXIST_MESSAGE, nonExistentModule.getModuleCode()), () ->
                student.toModelType(TypicalUniBook.getTypicalUniBook()));
    }
}
