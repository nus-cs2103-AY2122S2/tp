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
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Telegram;
import seedu.address.model.person.lab.Lab;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_GITHUB = "--example";
    private static final String INVALID_TELEGRAM = "R@chel";
    private static final String INVALID_STUDENTID = "A01234567";
    private static final String INVALID_LABNUMBER = "a";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GITHUB = BENSON.getGithubUsername().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();
    private static final String VALID_STUDENTID = BENSON.getStudentId().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLabNumber> VALID_LABNUMBERS = BENSON.getLabs().asUnmodifiableObservableList()
            .stream()
            .map(JsonAdaptedLabNumber::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLabStatus> VALID_LABSTATUSES = BENSON.getLabs().asUnmodifiableObservableList()
            .stream()
            .map(JsonAdaptedLabStatus::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, invalidTags, VALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, INVALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = GithubUsername.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, null,
                VALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GithubUsername.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                INVALID_TELEGRAM, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                null, VALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, INVALID_STUDENTID, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, null, VALID_LABNUMBERS, VALID_LABSTATUSES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidLabNumber_throwsIllegalValueException() {
        JsonAdaptedLabNumber invalid = new JsonAdaptedLabNumber(INVALID_LABNUMBER);
        List<JsonAdaptedLabNumber> invalidList = new ArrayList<>();
        invalidList.add(invalid);

        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_GITHUB,
                VALID_TELEGRAM, VALID_STUDENTID, invalidList, VALID_LABSTATUSES);
        String expectedMessage = Lab.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
