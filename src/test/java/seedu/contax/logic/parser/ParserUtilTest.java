package seedu.contax.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Phone;
import seedu.contax.model.tag.Tag;
import seedu.contax.model.util.SearchType;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+sg42344";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_CSV_FILEPATH = "./src/test/data/ImportCsvTest/ValidContaXFormat.csv";
    private static final String INVALID_BAD_EXTENSION_CSV_FILEPATH = "wrongFile.txt";
    private static final String INVALID_NO_EXTENSION_CSV_FILEPATH = "./src/test/data/ImportCsvTest/ValidContaXFormat";

    private static final String INVALID_APPOINTMENT_NAME = "MEETING?";
    private static final String INVALID_DATE_SYNTAX = "2022-10-20";
    private static final String INVALID_DATE_VALUE = "32-01-2022";
    private static final String INVALID_TIME_SYNTAX = "12-22";
    private static final String INVALID_TIME_VALUE = "25:20";
    private static final String INVALID_DURATION_NEGATIVE = "-1";
    private static final String INVALID_DURATION_NON_INTEGER = "two";

    private static final String VALID_APPOINTMENT_NAME = "This is a meeting";
    private static final String VALID_DATE = "31-01-2022";
    private static final String VALID_TIME = "12:22";
    private static final String VALID_DATETIME_ISO_STRING = "2022-01-31T12:22:00";
    private static final String VALID_DURATION = "30";

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
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseImportCsvFilePath_validFilePathWithoutWhitespace_returnsFile() throws Exception {
        File expectedFile = new File(VALID_CSV_FILEPATH);
        assertEquals(expectedFile, ParserUtil.parseCsvFilePath(VALID_CSV_FILEPATH));
    }

    @Test
    public void parseImportCsvFilePath_validFilePathWithWhitespace_returnsFile() throws Exception {
        String filePathWithWhitespace = WHITESPACE + VALID_CSV_FILEPATH + WHITESPACE;
        File expectedFile = new File(VALID_CSV_FILEPATH);
        assertEquals(expectedFile, ParserUtil.parseCsvFilePath(VALID_CSV_FILEPATH));
    }

    @Test
    public void parseImportCsvFilePath_invalidExtension_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCsvFilePath(INVALID_BAD_EXTENSION_CSV_FILEPATH));
    }

    @Test
    public void parseImportCsvFilePath_noExtension_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCsvFilePath(INVALID_NO_EXTENSION_CSV_FILEPATH));
    }

    @Test
    public void parseImportCsvPositions_validPosition_returnsInt() throws Exception {
        assertEquals(1, ParserUtil.parseCsvPositions("1"));
    }

    @Test
    public void parseImportCsvPositions_negativePosition_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCsvPositions("-1"));
    }

    @Test
    public void parseImportCsvPositions_lettersInPosition_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCsvPositions("test"));
    }

    @Test
    public void parseAppointmentName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointmentName((String) null));
    }

    @Test
    public void parseAppointmentName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentName(INVALID_APPOINTMENT_NAME));
    }

    @Test
    public void parseAppointmentName_validValue_returnsName() throws Exception {
        seedu.contax.model.appointment.Name expectedName =
                new seedu.contax.model.appointment.Name(VALID_APPOINTMENT_NAME);
        assertEquals(expectedName, ParserUtil.parseAppointmentName(VALID_APPOINTMENT_NAME));
    }

    @Test
    public void parseAppointmentName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_APPOINTMENT_NAME + WHITESPACE;
        seedu.contax.model.appointment.Name expectedName =
                new seedu.contax.model.appointment.Name(VALID_APPOINTMENT_NAME);
        assertEquals(expectedName, ParserUtil.parseAppointmentName(nameWithWhitespace));
    }

    @Test
    public void parseStartDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStartDateTime((String) null, ""));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStartDateTime("", (String) null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStartDateTime(null, (String) null));
    }

    @Test
    public void parseStartDateTime_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStartDateTime(INVALID_DATE_SYNTAX,
                INVALID_TIME_SYNTAX));
        assertThrows(ParseException.class, () -> ParserUtil.parseStartDateTime(INVALID_DATE_VALUE,
                INVALID_TIME_VALUE));
        assertThrows(ParseException.class, () -> ParserUtil.parseStartDateTime(VALID_DATE,
                INVALID_TIME_VALUE));
        assertThrows(ParseException.class, () -> ParserUtil.parseStartDateTime(INVALID_DATE_VALUE,
                VALID_TIME));
        assertThrows(ParseException.class, () -> ParserUtil.parseStartDateTime(VALID_TIME, VALID_DATE));
    }

    @Test
    public void parseStartDateTime_validValue_returnsStartDateTime() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse(VALID_DATETIME_ISO_STRING);
        StartDateTime expectedStartDateTime = new StartDateTime(dateTime);
        assertEquals(expectedStartDateTime, ParserUtil.parseStartDateTime(VALID_DATE, VALID_TIME));
    }

    @Test
    public void parseDuration_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDuration((String) null));
    }

    @Test
    public void parseDuration_invalidDuration_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_NON_INTEGER));
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_NEGATIVE));
    }

    @Test
    public void parseDuration_validValue_returnsDuration() throws Exception {
        Duration expectedDuration = new Duration(Integer.parseInt(VALID_DURATION));
        assertEquals(expectedDuration, ParserUtil.parseDuration(VALID_DURATION));
    }

    @Test
    public void parseSearchType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSearchType((String) null));
    }

    @Test
    public void parseSearchType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSearchType(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseSearchType("Not a valid type"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSearchType("123 Not Type"));
    }

    @Test
    public void parseSearchType_validValue_returnsSearchType() throws ParseException {
        assertEquals(new SearchType(SearchType.TYPE_PHONE), ParserUtil.parseSearchType(SearchType.TYPE_PHONE));
        assertEquals(new SearchType(SearchType.TYPE_ADDRESS), ParserUtil.parseSearchType(SearchType.TYPE_ADDRESS));
        assertEquals(new SearchType(SearchType.TYPE_EMAIL), ParserUtil.parseSearchType(SearchType.TYPE_EMAIL));
        assertEquals(new SearchType(SearchType.TYPE_NAME), ParserUtil.parseSearchType(SearchType.TYPE_NAME));

        assertEquals(new SearchType(SearchType.TYPE_PHONE),
                ParserUtil.parseSearchType(SearchType.TYPE_PHONE.toUpperCase()));
        assertEquals(new SearchType(SearchType.TYPE_ADDRESS),
                ParserUtil.parseSearchType(SearchType.TYPE_ADDRESS.toUpperCase()));
        assertEquals(new SearchType(SearchType.TYPE_EMAIL),
                ParserUtil.parseSearchType(SearchType.TYPE_EMAIL.toUpperCase()));
        assertEquals(new SearchType(SearchType.TYPE_NAME),
                ParserUtil.parseSearchType(SearchType.TYPE_NAME.toUpperCase()));
    }

    @Test
    public void parseAndCreateNewCommand_returnsCommand() throws ParseException {
        assertEquals("test 1 command",
                ParserUtil.parseAndCreateNewCommand("test command", "1"));
    }
    @Test
    public void parseAndCreateNewCommand_invalidValue_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () -> ParserUtil.parseAndCreateNewCommand("", "1"));
    }
}
