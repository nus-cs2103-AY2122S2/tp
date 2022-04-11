package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAM = "+651234";
    private static final String INVALID_GITHUB = " ";
    private static final String INVALID_EMAIL = "u.nus.edu";
    private static final String INVALID_TUTORIAL_GROUP = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_TELEGRAM = "@123456";
    private static final String VALID_GITHUB = "Walk-Rachel";
    private static final String VALID_EMAIL = "rachel@u.nus.edu";
    private static final String VALID_TUTORIAL_GROUP_1 = "ST2334 T01";
    private static final String VALID_TUTORIAL_GROUP_2 = "ES2660 G04";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTelegram_null_returnsEmptyTelegram() throws ParseException {
        Telegram telegram = new Telegram(null);
        assertEquals(telegram, ParserUtil.parseTelegram(null));
    }

    @Test
    public void parseGitHub_null_returnsEmptyGitHub() throws ParseException {
        GitHub github = new GitHub(null);
        assertEquals(github, ParserUtil.parseGitHub(null));
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
    public void parseTelegram_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram(INVALID_TELEGRAM));
    }

    @Test
    public void parseTelegram_validValueWithoutWhitespace_returnsTelegram() throws Exception {
        Telegram expectedTelegram = new Telegram(VALID_TELEGRAM);
        assertEquals(expectedTelegram, ParserUtil.parseTelegram(VALID_TELEGRAM));
    }

    @Test
    public void parseTelegram_validValueWithWhitespace_returnsTrimmedTelegram() throws Exception {
        String telegramWithWhitespace = WHITESPACE + VALID_TELEGRAM + WHITESPACE;
        Telegram expectedTelegram = new Telegram(VALID_TELEGRAM);
        assertEquals(expectedTelegram, ParserUtil.parseTelegram(telegramWithWhitespace));
    }

    @Test
    public void parseGitHub_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGitHub(INVALID_GITHUB));
    }

    @Test
    public void parseGitHub_validValueWithoutWhitespace_returnsGitHub() throws Exception {
        GitHub expectedGitHub = new GitHub(VALID_GITHUB);
        assertEquals(expectedGitHub, ParserUtil.parseGitHub(VALID_GITHUB));
    }

    @Test
    public void parseGitHub_validValueWithWhitespace_returnsTrimmedGitHub() throws Exception {
        String gitHubWithWhitespace = WHITESPACE + VALID_GITHUB + WHITESPACE;
        GitHub expectedGitHub = new GitHub(VALID_GITHUB);
        assertEquals(expectedGitHub, ParserUtil.parseGitHub(gitHubWithWhitespace));
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
    public void parseTutorialGroup_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTutorialGroup(null));
    }

    @Test
    public void parseTutorialGroup_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTutorialGroup(INVALID_TUTORIAL_GROUP));
    }

    @Test
    public void parseTutorialGroup_validValueWithoutWhitespace_returnsTutorialGroup() throws Exception {
        TutorialGroup expectedTutorialGroup = new TutorialGroup(VALID_TUTORIAL_GROUP_1);
        assertEquals(expectedTutorialGroup, ParserUtil.parseTutorialGroup(VALID_TUTORIAL_GROUP_1));
    }

    @Test
    public void parseTutorialGroup_validValueWithWhitespace_returnsTrimmedTutorialGroup() throws Exception {
        String tutorialGroupWithWhitespace = WHITESPACE + VALID_TUTORIAL_GROUP_1 + WHITESPACE;
        TutorialGroup expectedTutorialGroup = new TutorialGroup(VALID_TUTORIAL_GROUP_1);
        assertEquals(expectedTutorialGroup, ParserUtil.parseTutorialGroup(tutorialGroupWithWhitespace));
    }

    @Test
    public void parseTutorialGroup_validTutorialInLowercase_returnsTutorialGroupInUppercase() throws Exception {
        String validTutorialGroupInLowercase = VALID_TUTORIAL_GROUP_1.toLowerCase();
        TutorialGroup expectedTutorialGroup = new TutorialGroup(VALID_TUTORIAL_GROUP_1.toUpperCase());
        assertEquals(expectedTutorialGroup, ParserUtil.parseTutorialGroup(validTutorialGroupInLowercase));
    }

    @Test
    public void parseTutorialGroups_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTutorialGroups(null));
    }

    @Test
    public void parseTutorialGroups_collectionWithInvalidTutorialGroups_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTutorialGroups(
            Arrays.asList(VALID_TUTORIAL_GROUP_1, INVALID_TUTORIAL_GROUP)));
    }

    @Test
    public void parseTutorialGroups_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTutorialGroups(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTutorialGroups_collectionWithValidTutorialGroups_returnsTutorialGroupSet() throws Exception {
        Set<TutorialGroup> actualTutorialGroupSet = ParserUtil.parseTutorialGroups(
            Arrays.asList(VALID_TUTORIAL_GROUP_1, VALID_TUTORIAL_GROUP_2));
        Set<TutorialGroup> expectedTutorialGroupSet = new HashSet<TutorialGroup>(
            Arrays.asList(new TutorialGroup(VALID_TUTORIAL_GROUP_1), new TutorialGroup(VALID_TUTORIAL_GROUP_2)));

        assertEquals(expectedTutorialGroupSet, actualTutorialGroupSet);
    }
}
