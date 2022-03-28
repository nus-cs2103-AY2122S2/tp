package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Team;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_USERNAME = "rach_el";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TEAM = "#friend";
    private static final String INVALID_SKILL_PROFICIENCY_TYPE = "good";
    private static final String INVALID_SKILL_PROFICIENCY_RANGE = "500";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_USERNAME = "rachel-walker";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TEAM_1 = "friend";
    private static final String VALID_TEAM_2 = "neighbour";
    private static final String VALID_SKILL = "Python";
    private static final String VALID_SKILL_PREFIX = "_";
    private static final String VALID_SKILL_PROFICIENCY = "5";


    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseGithubUsername_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGithubUsername((String) null));
    }

    @Test
    public void parseGithubUsername_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGithubUsername(INVALID_USERNAME));
    }

    @Test
    public void parseGithubUsername_validValueWithoutWhitespace_returnsAddress() throws Exception {
        GithubUsername expectedGithubUsername = new GithubUsername(VALID_USERNAME);
        assertEquals(expectedGithubUsername, ParserUtil.parseGithubUsername(VALID_USERNAME));
    }

    @Test
    public void parseGithubUsername_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_USERNAME + WHITESPACE;
        GithubUsername expectedGithubUsername = new GithubUsername(VALID_USERNAME);
        assertEquals(expectedGithubUsername, ParserUtil.parseGithubUsername(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTeam_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTeam(null));
    }

    @Test
    public void parseTeam_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTeam(INVALID_TEAM));
    }

    @Test
    public void parseTeam_validValueWithoutWhitespace_returnsTeam() throws Exception {
        Team expectedTeam = new Team(VALID_TEAM_1);
        assertEquals(expectedTeam, ParserUtil.parseTeam(VALID_TEAM_1));
    }

    @Test
    public void parseTeam_validValueWithWhitespace_returnsTrimmedTeam() throws Exception {
        String teamWithWhitespace = WHITESPACE + VALID_TEAM_1 + WHITESPACE;
        Team expectedTeam = new Team(VALID_TEAM_1);
        assertEquals(expectedTeam, ParserUtil.parseTeam(teamWithWhitespace));
    }

    @Test
    public void parseTeams_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTeams(null));
    }

    @Test
    public void parseTeams_collectionWithInvalidTeams_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTeams(Arrays.asList(VALID_TEAM_1, INVALID_TEAM)));
    }

    @Test
    public void parseTeams_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTeams(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTeams_collectionWithValidTeams_returnsTeamSet() throws Exception {
        Set<Team> actualTeamSet = ParserUtil.parseTeams(Arrays.asList(VALID_TEAM_1, VALID_TEAM_2));
        Set<Team> expectedTeamSet = new HashSet<Team>(Arrays.asList(new Team(VALID_TEAM_1), new Team(VALID_TEAM_2)));

        assertEquals(expectedTeamSet, actualTeamSet);
    }

    @Test
    public void parseSkill_throwsParseException() throws Exception {
        String invalidProficiencyRange = VALID_SKILL + VALID_SKILL_PREFIX + INVALID_SKILL_PROFICIENCY_RANGE;
        String invalidProficiencyType = VALID_SKILL + VALID_SKILL_PREFIX + INVALID_SKILL_PROFICIENCY_TYPE;
        String invalidSkillInput1 = VALID_SKILL + VALID_SKILL_PREFIX + VALID_SKILL_PROFICIENCY
                + VALID_SKILL_PREFIX + VALID_SKILL;
        String invalidSkillInput2 = VALID_SKILL + VALID_SKILL_PREFIX;
        String invalidSkillInput3 = VALID_SKILL;

        assertThrows(ParseException.class, ()-> ParserUtil.parseSkill(invalidProficiencyRange));
        assertThrows(ParseException.class, ()-> ParserUtil.parseSkill(invalidProficiencyType));
        assertThrows(ParseException.class, ()-> ParserUtil.parseSkill(invalidSkillInput1));
        assertThrows(ParseException.class, ()-> ParserUtil.parseSkill(invalidSkillInput2));
        assertThrows(ParseException.class, ()-> ParserUtil.parseSkill(invalidSkillInput3));
    }
}
