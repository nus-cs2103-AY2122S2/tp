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
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_USERNAME = "rach_el";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TEAM = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_USERNAME = BENSON.getGithubUsername().toString();
    private static final List<JsonAdaptedTeam> VALID_TEAMS = BENSON.getTeams().stream()
            .map(JsonAdaptedTeam::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedSkill> VALID_SKILLSET = BENSON.getSkillSet().getSkillSetInStream()
            .map(JsonAdaptedSkill::new)
            .collect(Collectors.toList());

    private static final boolean PLACEHOLDER_IS_TEAMMATE = false;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_USERNAME, PLACEHOLDER_IS_TEAMMATE,
                        VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_USERNAME,
            PLACEHOLDER_IS_TEAMMATE, VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_USERNAME, PLACEHOLDER_IS_TEAMMATE,
                        VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_USERNAME,
                PLACEHOLDER_IS_TEAMMATE, VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_USERNAME,
                        PLACEHOLDER_IS_TEAMMATE, VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_USERNAME,
                PLACEHOLDER_IS_TEAMMATE, VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithubUsername_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_USERNAME, PLACEHOLDER_IS_TEAMMATE,
                    VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = GithubUsername.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGithubUsername() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                PLACEHOLDER_IS_TEAMMATE, VALID_TEAMS, VALID_SKILLSET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GithubUsername.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTeams_throwsIllegalValueException() {
        List<JsonAdaptedTeam> invalidTeams = new ArrayList<>(VALID_TEAMS);
        invalidTeams.add(new JsonAdaptedTeam(INVALID_TEAM));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_USERNAME, PLACEHOLDER_IS_TEAMMATE, invalidTeams, VALID_SKILLSET);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
